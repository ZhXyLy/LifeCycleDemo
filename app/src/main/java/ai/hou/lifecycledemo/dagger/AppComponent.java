package ai.hou.lifecycledemo.dagger;

import android.content.Context;

import javax.inject.Singleton;

import ai.hou.lifecycledemo.webservice.WebService;
import dagger.Component;

/**
 * @author zhaoxl
 * @since 2017/11/1
 */
@Singleton
@Component(modules = ApiModule.class)
public interface AppComponent {
    Context context();
    WebService webService();
}
