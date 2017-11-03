package ai.hou.lifecycledemo.activity;

import ai.hou.lifecycledemo.dagger.ActivityScope;
import ai.hou.lifecycledemo.dagger.AppComponent;
import dagger.Component;

/**
 * @author zhaoxl
 * @since 2017/11/1
 */
@Component(modules = MainModule.class, dependencies = AppComponent.class)
@ActivityScope
public interface MainComponent {
    void inject(MainActivity activity);
}
