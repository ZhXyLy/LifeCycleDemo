package ai.hou.lifecycledemo.room.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * @author zhaoxl
 * @since 2017/11/1
 */
@Entity
public class User {
    @PrimaryKey
    public String id;

    public String userName;
}
