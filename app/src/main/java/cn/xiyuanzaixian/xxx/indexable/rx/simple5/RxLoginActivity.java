package cn.xiyuanzaixian.xxx.indexable.rx.simple5;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.aloglibrary.ALog;

import cn.xiyuanzaixian.xxx.indexable.MainActivity;
import cn.xiyuanzaixian.xxx.indexable.R;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RxLoginActivity extends AppCompatActivity {

    private TextView tv_textview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_login);
        tv_textview = (TextView) findViewById(R.id.tv_textview);
        Button show = (Button) findViewById(R.id.showSmothing);
        Button back_to_mainactivity = (Button) findViewById(R.id.back_to_mainactivity);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MapUser();
            }
        });

        back_to_mainactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RxLoginActivity.this, MainActivity.class));
            }
        });
        // 构建 Retrofit
        final ApiService apiService = new Retrofit.Builder()
                .baseUrl("https://httpbin.org")
                // rx 与 Gson 混用
                .addConverterFactory(GsonConverterFactory.create())
                // rx 与 Retrofit 混用
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiService.class);

        // 构建 RxJava
        UserParam param = new UserParam("sbsbsb", "123123");
        // 发送 param 参数
        Observable.just(param)
                .flatMap(new Function<UserParam, ObservableSource<NetBean>>() {
                    @Override
                    public ObservableSource<NetBean> apply(@NonNull UserParam userParam) throws Exception {
                        // 发送网络请求，获取NetBean
                        return apiService.getUserInfo(userParam.getParam1(), userParam.getParam2());
                    }
                })
                .flatMap(new Function<NetBean, ObservableSource<UserBean>>() {
                    @Override
                    public ObservableSource<UserBean> apply(@NonNull NetBean netBean) throws Exception {
                        UserBean user = new UserBean(netBean.getForm().getUsername(), netBean.getForm().getPassword());
                        // 转换 NetBean 数据为 UserBean
                        return Observable.just(user);
                    }
                })
                // 将被观察者放在子线程 将观察者放在主线程
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<UserBean>() {
                    @Override
                    public void accept(@NonNull UserBean userBean) throws Exception {
                        // 接受第二步发送过来的数据进行 UI 更新
                        tv_textview.setText("用户名: " + userBean.getUsername() + "--密码: " + userBean.getPasswrod());
                    }
                });
    }

    public void MapUser() {
        Observable.just(1).map(new Function<Integer, String>() {
            @Override
            public String apply(@NonNull Integer integer) throws Exception {
                return "第" + integer + "号同学";
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                ALog.d(s);
            }
        });

    }
}
