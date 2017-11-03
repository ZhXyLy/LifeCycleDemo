package ai.hou.lifecycledemo.repository;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MediatorLiveData;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import javax.inject.Inject;

import ai.hou.lifecycledemo.LifeLog;
import ai.hou.lifecycledemo.dagger.ActivityScope;
import ai.hou.lifecycledemo.network.ApiResponse;
import ai.hou.lifecycledemo.network.NetworkBoundResource;
import ai.hou.lifecycledemo.network.Resource;
import ai.hou.lifecycledemo.room.dao.UserDao;
import ai.hou.lifecycledemo.room.entity.User;
import ai.hou.lifecycledemo.webservice.WebService;

/**
 * @author zhaoxl
 * @since 2017/11/1
 */
@ActivityScope
public class UserRepository {
    private WebService webService;
    private UserDao userDao;

    @Inject
    public UserRepository(WebService webService, UserDao userDao) {
        this.webService = webService;
        this.userDao = userDao;
    }

    public LiveData<Resource<User>> loadUser(final String userId) {
        LifeLog.e("UserRepository");
        return new NetworkBoundResource<User, User>() {

            @Override
            protected void saveCallResult(@NonNull User item) {
                LifeLog.e("saveCallResult");
                userDao.save(item);
            }

            @Override
            protected boolean shouldFetch(@Nullable User data) {
                LifeLog.e("shouldFetch");
                return data == null || !isFresh(data);
            }

            @NonNull
            @Override
            protected LiveData<User> loadFromDb() {
                LifeLog.e("shouldFetch");
                return userDao.load(userId);
            }

            @NonNull
            @Override
            protected LiveData<ApiResponse<User>> createCall() {
                User user = new User();
                user.id = "0362";
                user.userName = "赵晓雷";
                MediatorLiveData<ApiResponse<User>> result = new MediatorLiveData<>();
                ApiResponse<User> response = new ApiResponse<>();
                response.body=user;
                result.setValue(response);
                return result ;
//                return webService.getUser(userId);
            }
        }.getAsLiveData();
    }

    private boolean isFresh(User data) {
        return false;
    }
}
