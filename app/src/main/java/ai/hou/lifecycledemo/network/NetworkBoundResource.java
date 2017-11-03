package ai.hou.lifecycledemo.network;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.arch.lifecycle.Observer;
import android.os.AsyncTask;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;

/**
 * @author zhaoxl
 * @since 2017/11/1
 */

public abstract class NetworkBoundResource<ResultType, RequestType> {

    private final MediatorLiveData<Resource<ResultType>> result = new MediatorLiveData<>();

    /**
     * 将API响应的结果保存到数据库中
     *
     * @param item
     */
    @WorkerThread
    protected abstract void saveCallResult(@NonNull RequestType item);

    /**
     * 调用数据库中的数据来决定是否应该从网络中获取它。
     *
     * @param data
     */
    @MainThread
    protected abstract boolean shouldFetch(@Nullable ResultType data);

    /**
     * 从数据库获取缓存的数据
     *
     * @return
     */
    @NonNull
    @MainThread
    protected abstract LiveData<ResultType> loadFromDb();

    /**
     * 创建API调用。
     *
     * @return
     */
    @NonNull
    @MainThread
    protected abstract LiveData<ApiResponse<RequestType>> createCall();

    @MainThread
    protected void onFetchFailed() {

    }

    public final LiveData<Resource<ResultType>> getAsLiveData() {
        return result;
    }

    @MainThread
    public NetworkBoundResource() {
        //1.初始化NetworkBoundResource
        result.setValue((Resource<ResultType>) Resource.loading(null));
        //2.从数据库加载本地数据
        final LiveData<ResultType> dbSource = loadFromDb();
        result.addSource(dbSource, new Observer<ResultType>() {
            @Override
            public void onChanged(@Nullable ResultType data) {
                //3.加载完成后判断是否需要从网上更新数据
                result.removeSource(dbSource);
                if (shouldFetch(data)) {
                    //4.从网上更新数据
                    fetchFromNetwork(dbSource);
                } else {
                    //直接用本地数据更新
                    result.addSource(dbSource, new Observer<ResultType>() {
                        @Override
                        public void onChanged(@Nullable ResultType newData) {
                            result.setValue(Resource.success(newData));
                        }
                    });
                }
            }
        });
    }

    private void fetchFromNetwork(final LiveData<ResultType> dbSource) {
        //5.进行网络请求
        final LiveData<ApiResponse<RequestType>> apiResponse = createCall();
        //我们将dbSource作为一个新的源，它将快速地发送其最新的值
        result.addSource(dbSource, new Observer<ResultType>() {
            @Override
            public void onChanged(@Nullable final ResultType newData) {
                result.setValue(Resource.loading(newData));
                result.addSource(apiResponse, new Observer<ApiResponse<RequestType>>() {
                    @Override
                    public void onChanged(@Nullable final ApiResponse<RequestType> response) {
                        result.removeSource(apiResponse);
                        result.removeSource(dbSource);

                        if (response.isSuccessful()) {
                            //6.请求数据成功，保存数据
                            saveResultAndReInit(response);
                        } else {
                            //请求失败使用，传递失败信息
                            onFetchFailed();
                            result.addSource(dbSource, new Observer<ResultType>() {
                                @Override
                                public void onChanged(@Nullable ResultType resultType) {
                                    result.setValue(Resource.error(response.errorMessage, newData));
                                }
                            });
                        }
                    }
                });
            }
        });
    }

    private void saveResultAndReInit(final ApiResponse<RequestType> response) {
        new AsyncTask<Void, Void, Void>() {

            @Override
            protected Void doInBackground(Void... params) {
                //7.保存请求到的数据
                saveCallResult(response.body);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                //8.再次加载数据库，使用数据库中的最新数据
                result.addSource(loadFromDb(), new Observer<ResultType>() {
                    @Override
                    public void onChanged(@Nullable ResultType newData) {
                        result.setValue(Resource.success(newData));
                    }
                });
            }
        }.execute();
    }
}
