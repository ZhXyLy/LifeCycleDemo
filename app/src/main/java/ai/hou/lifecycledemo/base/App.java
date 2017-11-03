package ai.hou.lifecycledemo.base;

import android.app.Application;

import ai.hou.lifecycledemo.dagger.ApiModule;
import ai.hou.lifecycledemo.dagger.AppComponent;
import ai.hou.lifecycledemo.dagger.DaggerAppComponent;

/**
 * @author zhaoxl
 * @since 2017/11/1
 */

public class App extends Application {

    private AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent.builder()
                .apiModule(new ApiModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }
}
