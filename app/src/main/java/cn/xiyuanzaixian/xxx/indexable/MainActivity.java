package cn.xiyuanzaixian.xxx.indexable;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.aloglibrary.ALog;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.Calendar;

import cn.xiyuanzaixian.xxx.indexable.city.CityIndexActivity;
import cn.xiyuanzaixian.xxx.indexable.cutdowntext.EasyCountDownTextureView;
import cn.xiyuanzaixian.xxx.indexable.name.PickContactActivity;
import cn.xiyuanzaixian.xxx.indexable.rx.simple1.LessonStart;
import cn.xiyuanzaixian.xxx.indexable.rx.simple2.RxActivity;
import cn.xiyuanzaixian.xxx.indexable.rx.simple3.SendCodeActivity;
import cn.xiyuanzaixian.xxx.indexable.rx.simple4.TextChangeActivity;
import cn.xiyuanzaixian.xxx.indexable.rx.simple5.RxLoginActivity;
import cn.xiyuanzaixian.xxx.indexable.rx.simple6.CartMegerActivity;
import me.leefeng.citypicker.CityPicker;
import me.leefeng.citypicker.CityPickerListener;

/**
 * zhouchong
 * Created by xxx on 2017/4/12.
 */
public class MainActivity extends AppCompatActivity implements CityPickerListener {

    private TextView contactName;
    private TextView cityName;
    private CityPicker cityPicker;
    private TextView select_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ALog.Builder builder = new ALog.Builder(this);
        setContentView(R.layout.activity_main);

        Button cityIndex = (Button) findViewById(R.id.CityIndex);
        Button nameIndex = (Button) findViewById(R.id.NameIndex);
        Button selectCity = (Button) findViewById(R.id.selectBtn);
        Button gotoRxActivity = (Button) findViewById(R.id.goToRxActivity);
        Button goto_sendcode = (Button) findViewById(R.id.goto_sendcode);
        Button go_to_edittext = (Button) findViewById(R.id.go_to_edittext);
        Button goto_login = (Button) findViewById(R.id.goto_login);
        Button go_to_cart = (Button) findViewById(R.id.go_to_cart);
        final Button lessonStart = (Button) findViewById(R.id.lessonStart);
        cityName = (TextView) findViewById(R.id.cityName);
        contactName = (TextView) findViewById(R.id.contactName);
        select_city = (TextView) findViewById(R.id.select_city);


        cityIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CityIndexActivity.class);
                startActivityForResult(intent, SaveCode.REQUESCITYTCODE);
            }
        });

        nameIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PickContactActivity.class);
                startActivityForResult(intent, SaveCode.REQUESCITYTCODE);
            }
        });

        lessonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LessonStart rollCall = new LessonStart();
                rollCall.rollCall();
            }
        });

        gotoRxActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RxActivity.class);
                startActivity(intent);
            }
        });

        goto_sendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SendCodeActivity.class);
                startActivity(intent);
            }
        });

        go_to_edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TextChangeActivity.class);
                startActivity(intent);
            }
        });

        goto_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RxLoginActivity.class));
            }
        });

        go_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, CartMegerActivity.class));
            }
        });

        cityPicker = new CityPicker(MainActivity.this, this);
        selectCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityPicker.show();
            }
        });

        // 文字内容过多折叠
        ExpandableTextView expandableTextView = (ExpandableTextView) findViewById(R.id.expand_text_view);
        expandableTextView.setText(getString(R.string.expandText));

        // 倒计时
        EasyCountDownTextureView easyCountDownTextureView = (EasyCountDownTextureView) findViewById(R.id.cutdownText);

        Calendar mCalendar = Calendar.getInstance();
        //mCalendar.setTimeInMillis(time);
        int mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        int mMinute = mCalendar.get(Calendar.MINUTE);
        int mSecond = mCalendar.get(Calendar.SECOND);

        // 此处的倒计时的截止时间为下一天凌晨
        ALog.d("TIME: " + mHour + ":" + mMinute + ":"    + mSecond);
        int hour = 24-mHour-1;
        int minute = 60-mMinute;
        int second = 60-mSecond;
        easyCountDownTextureView.setTimeHour(hour);
        easyCountDownTextureView.setTimeMinute(minute);
        easyCountDownTextureView.setTimeSecond(second);
    }

    /**
     * 选择后的回调
     *
     * @param name 返回的城市名
     */
    @Override
    public void getCity(String name) {
        select_city.setText("选择的城市是: " + name);
    }

    /**
     * 看下面具体的代码
     *
     * @param requestCode
     * @param resultCode  结果码
     * @param data        存储的数据
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SaveCode.REQUESCITYTCODE) {
            String city_name = data.getStringExtra(SaveCode.CITYNAME);
            cityName.setText("选中的城市是: " + city_name);
        } else if (resultCode == SaveCode.REQUESTCONTACTCODE) {
            String contact = data.getStringExtra(SaveCode.CONTACT);
            contactName.setText("选中的名字是: " + contact);
        } else {
            ToastUtil.showShort(this, "Nothing");
        }
    }

    /**
     * 处理三级联动的返回键
     */
    @Override
    public void onBackPressed() {
        if (cityPicker.isShow()) {
            cityPicker.close();
            return;
        }
        super.onBackPressed();
    }
}
