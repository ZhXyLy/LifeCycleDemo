package ai.hou.lifecycledemo.viewmodel;

import android.arch.lifecycle.LiveData;

import javax.inject.Inject;

import ai.hou.lifecycledemo.LifeLog;
import ai.hou.lifecycledemo.network.Resource;
import ai.hou.lifecycledemo.repository.UserRepository;
import ai.hou.lifecycledemo.room.entity.User;

/**
 * @author zhaoxl
 * @since 2017/11/1
 */

public class UserViewModel extends ViewModel {

    private UserRepository userRepository;

    @Inject
    public UserViewModel(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public LiveData<Resource<User>> getUser(String userId) {
        LifeLog.e("UserViewModel");
        return userRepository.loadUser(userId);
    }
}
