package ai.hou.lifecycledemo.webservice;

import android.arch.lifecycle.LiveData;

import ai.hou.lifecycledemo.network.ApiResponse;
import ai.hou.lifecycledemo.room.entity.User;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author zhaoxl
 * @since 2017/11/1
 */

public interface WebService {

    /**
     * 登录
     *
     * @param userId
     * @return
     */
    @GET("/users/{user}")
    LiveData<ApiResponse<User>> getUser(@Path("user") String userId);
}
