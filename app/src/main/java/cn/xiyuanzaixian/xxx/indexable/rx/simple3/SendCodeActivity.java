package cn.xiyuanzaixian.xxx.indexable.rx.simple3;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import java.util.concurrent.TimeUnit;

import cn.xiyuanzaixian.xxx.indexable.MainActivity;
import cn.xiyuanzaixian.xxx.indexable.R;
import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

/**
 * Created by xxx on 2017/4/14.
 */

public class SendCodeActivity extends AppCompatActivity implements View.OnClickListener {

    private Button codeBtn;
    private Button backto_main;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_code_activity);
        codeBtn = (Button) findViewById(R.id.codeBtn);
        codeBtn.setOnClickListener(this);

        backto_main = (Button) findViewById(R.id.backto_main);
        backto_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SendCodeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onClick(View v) {
        final long count = 60;
        Observable.interval(0, 1, TimeUnit.SECONDS)
                .take(count + 1)
                .map(new Function<Long, Long>() {
                    @Override
                    public Long apply(@NonNull Long aLong) throws Exception {
                        return count - aLong;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        codeBtn.setEnabled(false);
                        codeBtn.setTextColor(Color.GRAY);
                    }
                })
                .subscribe(new Observer<Long>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Long aLong) {
                        codeBtn.setText("剩余" + aLong + "秒");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        codeBtn.setEnabled(true);
                        codeBtn.setTextColor(Color.RED);
                        codeBtn.setText("发送验证码");
                    }
                });
    }
}
