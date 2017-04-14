package cn.xiyuanzaixian.xxx.indexable.rx.simple4;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.blankj.aloglibrary.ALog;
import com.jakewharton.rxbinding2.widget.RxTextView;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import cn.xiyuanzaixian.xxx.indexable.MainActivity;
import cn.xiyuanzaixian.xxx.indexable.R;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class TextChangeActivity extends AppCompatActivity {

    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_change);

        editText = (EditText) findViewById(R.id.edit_text);
        Button back_to_main = (Button) findViewById(R.id.back_to_main);
        back_to_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(TextChangeActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        RxTextView.textChanges(editText)
                // 输入字后延迟 500ms 就会执行下列语句
                .debounce(500, TimeUnit.MILLISECONDS)
                // flatMap: 多个网络请求前面的数据会覆盖后面的数据
                // switchMap: 多个网络请求时 会以后面的数据为准 前面的数据会被后面的数据覆盖
                .switchMap(new Function<CharSequence, ObservableSource<List<String>>>() {
                    @Override
                    public ObservableSource<List<String>> apply(@NonNull CharSequence charSequence) throws Exception {
                        // 网络操作 获取需要的数据
                        List<String> list = new ArrayList<String>();
                        list.add("增加的第一个数据");
                        list.add("增加的第二个数据");
                        return Observable.just(list);
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<String>>() {
                    @Override
                    public void accept(@NonNull List<String> strings) throws Exception {
                        // 界面更新
                        ALog.d(strings.toString());
                    }
                });
    }
}
