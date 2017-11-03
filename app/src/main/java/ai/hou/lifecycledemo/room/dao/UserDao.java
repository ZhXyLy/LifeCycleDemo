package ai.hou.lifecycledemo.room.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import ai.hou.lifecycledemo.room.entity.User;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * @author zhaoxl
 * @since 2017/11/1
 */
@Dao
public interface UserDao {
    /**
     * 数据库中保存一条数据
     *
     * @param user
     */
    @Insert(onConflict = REPLACE)
    void save(User user);

    /**
     * 通过id查数据
     *
     * @param userId
     * @return 返回userId对应的User
     */
    @Query("SELECT * FROM user where id = :userId")
    LiveData<User> load(String userId);
}
