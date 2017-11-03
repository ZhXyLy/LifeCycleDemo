package ai.hou.lifecycledemo.activity;

import android.arch.lifecycle.LiveData;

import ai.hou.lifecycledemo.dagger.ActivityScope;
import ai.hou.lifecycledemo.repository.UserRepository;
import ai.hou.lifecycledemo.room.dao.UserDao;
import ai.hou.lifecycledemo.room.entity.User;
import ai.hou.lifecycledemo.viewmodel.UserViewModel;
import dagger.Module;
import dagger.Provides;

/**
 * @author zhaoxl
 * @since 2017/11/1
 */
@Module
public class MainModule {

    @Provides
    UserDao provideUserDao() {
        return new UserDao() {
            @Override
            public void save(User user) {

            }

            @Override
            public LiveData<User> load(String userId) {
                return null;
            }
        };
    }

    @Provides
    @ActivityScope
    UserViewModel provideUserViewModel(UserRepository userRepository) {
        return new UserViewModel(userRepository);
    }
}
