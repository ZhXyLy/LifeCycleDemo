package ai.hou.lifecycledemo.network;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * @author zhaoxl
 * @since 2017/11/1
 */

public class Resource<T> {
    public static final int SUCCESS = 1;
    public static final int ERROR = 2;
    public static final int LOADING = 0;


    public final int status;
    @Nullable public final T data;
    @Nullable public final String message;

    public Resource(int status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@NonNull T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }
}
