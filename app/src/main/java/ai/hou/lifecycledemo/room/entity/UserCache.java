package ai.hou.lifecycledemo.room.entity;

import android.arch.lifecycle.LiveData;
import android.util.LruCache;

/**
 * @author zhaoxl
 * @since 2017/11/1
 */

public class UserCache{

    private LruCache<String, LiveData<User>> cache;

    public void put(String key, LiveData<User> value) {
        cache.put(key, value);
    }

    public LiveData<User> get(String key) {
        return cache.get(key);
    }
}
