package ai.hou.lifecycledemo;

import android.util.Log;

/**
 * @author zhaoxl
 * @since 2017/11/2
 */

public class LifeLog {
    private static final String TAG = "LifeLog";

    public static void e(String s, Object... args) {
        Log.e(TAG, String.format(s, args));
    }
}
