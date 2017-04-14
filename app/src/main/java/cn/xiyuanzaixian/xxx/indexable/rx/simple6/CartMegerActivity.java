package cn.xiyuanzaixian.xxx.indexable.rx.simple6;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.blankj.aloglibrary.ALog;

import java.util.ArrayList;
import java.util.List;

import cn.xiyuanzaixian.xxx.indexable.MainActivity;
import cn.xiyuanzaixian.xxx.indexable.R;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class CartMegerActivity extends AppCompatActivity implements View.OnClickListener {

    private ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart_meger);

        apiService = new Retrofit.Builder()
                .baseUrl("https://httpbin.org")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(ApiService.class);
        Button cart_activity = (Button) findViewById(R.id.cart_activity);
        cart_activity.setOnClickListener(this);

        Button back_to_menu = (Button) findViewById(R.id.back_to_menu);

        back_to_menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartMegerActivity.this, MainActivity.class));
            }
        });
    }

    @Override
    public void onClick(View v) {
        Observable.merge(getDataForLocal(), getDataForNet())
                .subscribe(new Observer<List<String>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<String> strings) {
                        for (String str : strings) {
                            ALog.d(str);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        ALog.d("onComplete");
                    }
                });
    }

    private Observable<List<String>> getDataForLocal() {
        List<String> list = new ArrayList<>();
        list.add("购物车物品一");
        list.add("购物车物品二");
        return Observable.just(list);
    }

    private Observable<List<String>> getDataForNet() {
        return Observable.just("购物车物品三")
                .flatMap(new Function<String, ObservableSource<NetBean>>() {
                    @Override
                    public ObservableSource<NetBean> apply(@NonNull String s) throws Exception {
                        return apiService.getCartList(s);
                    }
                })
                .flatMap(new Function<NetBean, ObservableSource<List<String>>>() {
                    @Override
                    public ObservableSource<List<String>> apply(@NonNull NetBean netBean) throws Exception {
                        String shop = netBean.get_$Args257().getShopName();
                        List<String> list = new ArrayList<>();
                        list.add(shop);
                        return Observable.just(list);
                    }
                }).subscribeOn(Schedulers.io());
    }
}
