package cn.xiyuanzaixian.xxx.indexable.rx.simple2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.blankj.aloglibrary.ALog;

import cn.xiyuanzaixian.xxx.indexable.MainActivity;
import cn.xiyuanzaixian.xxx.indexable.R;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class RxActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx);

        method3();

        Button rxButton = (Button) findViewById(R.id.rx_activity);
        rxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RxActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        Button goto_btnclickactivity = (Button) findViewById(R.id.goto_btnclickactivity);
        goto_btnclickactivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RxActivity.this, ButtonClickActivity.class);
                startActivity(intent);
            }
        });

    }

    //订阅方式一
    public void method1() {
        Observable<String> observable = getObservable();
        Observer<String> observer = getObserver();
        observable.subscribe(observer);
    }

    //订阅方式二
    public void method2() {
        Observable<String> observable = getObservable();
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                Log.e("accept", s);
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {

            }
        }, new Action() {
            @Override
            public void run() throws Exception {

            }
        }, new Consumer<Disposable>() {
            @Override
            public void accept(@NonNull Disposable disposable) throws Exception {

            }
        });
    }

    //订阅方式三
    public void method3() {
        //创建被观察者
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            //默认在主线程里执行该方法
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("俊俊俊很帅");
                e.onNext("你值得拥有");
                e.onNext("取消关注");
                e.onNext("但还是要保持微笑");
                e.onComplete();
            }
        })
                //将被观察者切换到子线程
                .subscribeOn(Schedulers.io())
                //将观察者切换到主线程
                .observeOn(AndroidSchedulers.mainThread())
                //创建观察者并订阅
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {
                        ALog.e("onNext", s);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    /**
     * 获取被观察者
     *
     * @return
     */
    public Observable<String> getObservable() {
        //1、可发送对应的方法
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                e.onNext("俊俊俊很帅");
                e.onNext("你值得拥有");
                e.onNext("取消关注");
                e.onNext("但还是要保持微笑");
                e.onComplete();
            }
        });
        //2、发送多个数据
//        return Observable.just("俊俊俊很帅","你值得拥有","取消关注","但还是要保持微笑");
        //3、发送数组
//        return Observable.fromArray("俊俊俊很帅","你值得拥有","取消关注","但还是要保持微笑");
        //4、发送一个数据
//        return Observable.fromCallable(new Callable<String>() {
//            @Override
//            public String call() throws Exception {
//                return "俊俊俊很帅";
//            }
//        });
    }

    /**
     * 获取观察者
     *
     * @return
     */
    public Observer<String> getObserver() {
        return new Observer<String>() {

            Disposable disposable = null;

            @Override
            public void onSubscribe(Disposable d) {
                disposable = d;
            }

            @Override
            public void onNext(String s) {
                ALog.e("onNext", s);
                if (s.equals("取消关注")) {
                    //断开订阅
                    disposable.dispose();
                }
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                ALog.e("onComplete", "onComplete");
            }
        };
    }
}
