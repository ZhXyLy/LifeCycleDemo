package ai.hou.lifecycledemo.base;

import android.support.v7.app.AppCompatActivity;

import ai.hou.lifecycledemo.dagger.AppComponent;

/**
 * @author zhaoxl
 * @since 2017/11/2
 */

public class BaseActivity extends AppCompatActivity {
    protected AppComponent getAppComponent() {
        return ((App) this.getApplication()).getAppComponent();
    }
}
