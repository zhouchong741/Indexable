package cn.xiyuanzaixian.xxx.indexable;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.widget.TextView;

import com.blankj.aloglibrary.ALog;

import java.util.ArrayList;
import java.util.List;

import cn.xiyuanzaixian.xxx.indexable.fragment.FirstFragment;
import cn.xiyuanzaixian.xxx.indexable.fragment.FourthFragment;
import cn.xiyuanzaixian.xxx.indexable.fragment.SecondFragment;
import cn.xiyuanzaixian.xxx.indexable.fragment.ThirdFragment;
import me.leefeng.citypicker.CityPicker;
import me.riddhimanadib.library.BottomBarHolderActivity;
import me.riddhimanadib.library.NavigationPage;

/**
 * zhouchong
 * Created by xxx on 2017/4/12.
 */
public class MainActivity extends BottomBarHolderActivity {

    private TextView contactName;
    private TextView cityName;
    private CityPicker cityPicker;
    private TextView select_city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ALog.Builder builder = new ALog.Builder(this);
        NavigationPage page1 = new NavigationPage("Home", ContextCompat.getDrawable(this, R.drawable.ic_home_black_24dp), FirstFragment.newInstance());
        NavigationPage page2 = new NavigationPage("Support", ContextCompat.getDrawable(this, R.drawable.ic_mail_black_24dp), SecondFragment.newInstance());
        NavigationPage page3 = new NavigationPage("Billing", ContextCompat.getDrawable(this, R.drawable.ic_assessment_black_24dp), ThirdFragment.newInstance());
        NavigationPage page4 = new NavigationPage("Profile", ContextCompat.getDrawable(this, R.drawable.ic_person_black_24dp), FourthFragment.newInstance());

        List<NavigationPage> navigationPages = new ArrayList<>();
        navigationPages.add(page1);
        navigationPages.add(page2);
        navigationPages.add(page3);
        navigationPages.add(page4);

        super.setupBottomBarHolderActivity(navigationPages);
        //setContentView(R.layout.activity_main);

        /*Button cityIndex = (Button) findViewById(R.id.CityIndex);
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


    @Override
    public void getCity(String name) {
        select_city.setText("选择的城市是: " + name);
    }


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

    */

    }

   /* @Override
    public void onBackPressed() {
        if (cityPicker.isShow()) {
            cityPicker.close();
            return;
        }
        super.onBackPressed();
    }*/

}
