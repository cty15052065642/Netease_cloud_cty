package activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.administrator.netease_cloud.R;

/**
 * Created by Administrator on 2018/4/16.
 */

public class SplashActivity extends AppCompatActivity{

    private boolean user_first;
    private SharedPreferences setting;
    private Handler handler = new Handler();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        setting=getSharedPreferences("setting", 0);
        user_first = setting.getBoolean("FIRST",true);

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (user_first){
                    startWelcomeActivity();
                }
                else {
                    startMainActivity();
                }

            }
        },2000);
    }
    private  boolean startWelcomeActivity = false;//防止startMainActivity()被调用两次出现两个MianActivity
    private void startWelcomeActivity() {
        if(!startWelcomeActivity){
            startWelcomeActivity = true;
            setting.edit().putBoolean("FIRST", false).commit();
            startActivity(new Intent(SplashActivity.this,WelcomeActivity.class));

            finish();
        }
    }
    private  boolean startMainActivity = false;
    private void startMainActivity() {
        if(!startMainActivity){
            startMainActivity = true;
            startActivity(new Intent(SplashActivity.this,MainActivity.class));
            finish();
        }
    }
    //移除hedler一切防止退出层序handler再运行
    @Override
    protected void onDestroy() {
        handler.removeCallbacksAndMessages(null);
        super.onDestroy();
    }
}
