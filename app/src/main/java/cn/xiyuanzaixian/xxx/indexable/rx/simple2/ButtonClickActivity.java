package cn.xiyuanzaixian.xxx.indexable.rx.simple2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jakewharton.rxbinding2.view.RxView;

import java.util.concurrent.TimeUnit;

import cn.xiyuanzaixian.xxx.indexable.R;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class ButtonClickActivity extends AppCompatActivity {

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_button_click);

        button = (Button) findViewById(R.id.btn_click_activity);

        Button backtorx_activity = (Button) findViewById(R.id.backtorx_activity);
        backtorx_activity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ButtonClickActivity.this, RxActivity.class);
                startActivity(intent);
            }
        });

        RxView.clicks(button).throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(new Observer<Object>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Object o) {
                        System.out.println("俊俊俊点击了按钮");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }
}
