package ai.hou.lifecycledemo.activity;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import javax.inject.Inject;

import ai.hou.lifecycledemo.R;
import ai.hou.lifecycledemo.base.BaseActivity;
import ai.hou.lifecycledemo.network.Resource;
import ai.hou.lifecycledemo.room.entity.User;
import ai.hou.lifecycledemo.viewmodel.UserViewModel;

/**
 * @author zhaoxl
 */
public class MainActivity extends BaseActivity {
    @Inject UserViewModel userViewModel;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DaggerMainComponent.builder()
                .appComponent(getAppComponent())
                .mainModule(new MainModule())
                .build().inject(this);

        textView = findViewById(R.id.textView);
    }

    public void onLogin(View view) {
        userViewModel.getUser("userId").observe(this, new Observer<Resource<User>>() {
            @Override
            public void onChanged(@Nullable Resource<User> userResource) {
                //update UI
                if (userResource.status == Resource.SUCCESS) {
                    User user = userResource.data;
                    textView.setText(user.userName);
                    Toast.makeText(MainActivity.this, "" + user.userName, Toast.LENGTH_SHORT).show();
                } else if (userResource.status == Resource.LOADING) {
                    textView.setText("加载中…");
                } else {
                    Toast.makeText(MainActivity.this, "失败", Toast.LENGTH_SHORT).show();
                    textView.setText("获取失败");
                }
            }
        });
    }
}
