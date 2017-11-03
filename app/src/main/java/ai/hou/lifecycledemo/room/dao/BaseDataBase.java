package ai.hou.lifecycledemo.room.dao;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import ai.hou.lifecycledemo.room.entity.User;

/**
 * @author zhaoxl
 * @since 2017/11/1
 */
@Database(entities = {User.class},version = 1)
public abstract class BaseDataBase extends RoomDatabase {
    /**
     * 获取UserDao
     * @return
     */
    public abstract UserDao userDao();
}
