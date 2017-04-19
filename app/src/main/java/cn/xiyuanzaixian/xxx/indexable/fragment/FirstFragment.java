package cn.xiyuanzaixian.xxx.indexable.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.blankj.aloglibrary.ALog;
import com.ms.square.android.expandabletextview.ExpandableTextView;

import java.util.Calendar;

import cn.xiyuanzaixian.xxx.indexable.R;
import cn.xiyuanzaixian.xxx.indexable.SaveCode;
import cn.xiyuanzaixian.xxx.indexable.ToastUtil;
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
 * A simple {@link Fragment} subclass.
 */
public class FirstFragment extends Fragment implements CityPickerListener {


    private TextView contactName;
    private TextView cityName;
    private CityPicker cityPicker;
    private TextView select_city;

    public static FirstFragment newInstance() {
        return new FirstFragment();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.activity_main, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Button cityIndex = (Button) getActivity().findViewById(R.id.CityIndex);
        Button nameIndex = (Button) getActivity().findViewById(R.id.NameIndex);
        Button selectCity = (Button) getActivity().findViewById(R.id.selectBtn);
        Button gotoRxActivity = (Button) getActivity().findViewById(R.id.goToRxActivity);
        Button goto_sendcode = (Button) getActivity().findViewById(R.id.goto_sendcode);
        Button go_to_edittext = (Button) getActivity().findViewById(R.id.go_to_edittext);
        Button goto_login = (Button) getActivity().findViewById(R.id.goto_login);
        Button go_to_cart = (Button) getActivity().findViewById(R.id.go_to_cart);
        Button lessonStart = (Button) getActivity().findViewById(R.id.lessonStart);


        cityName = (TextView) getActivity().findViewById(R.id.cityName);
        contactName = (TextView) getActivity().findViewById(R.id.contactName);
        select_city = (TextView) getActivity().findViewById(R.id.select_city);


        cityIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CityIndexActivity.class);
                startActivityForResult(intent, SaveCode.REQUESCITYTCODE);
            }
        });

        nameIndex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PickContactActivity.class);
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
                Intent intent = new Intent(getContext(), RxActivity.class);
                startActivity(intent);
            }
        });

        goto_sendcode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), SendCodeActivity.class);
                startActivity(intent);
            }
        });

        go_to_edittext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), TextChangeActivity.class);
                startActivity(intent);
            }
        });

        goto_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), RxLoginActivity.class));
            }
        });

        go_to_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), CartMegerActivity.class));
            }
        });

        cityPicker = new CityPicker(getActivity(), this);
        selectCity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityPicker.show();
            }
        });

        // 文字内容过多折叠
        ExpandableTextView expandableTextView = (ExpandableTextView) getActivity().findViewById(R.id.expand_text_view);
        expandableTextView.setText(getString(R.string.expandText));

        // 倒计时
        EasyCountDownTextureView easyCountDownTextureView = (EasyCountDownTextureView) getActivity().findViewById(R.id.cutdownText);

        Calendar mCalendar = Calendar.getInstance();
        //mCalendar.setTimeInMillis(time);
        int mHour = mCalendar.get(Calendar.HOUR_OF_DAY);
        int mMinute = mCalendar.get(Calendar.MINUTE);
        int mSecond = mCalendar.get(Calendar.SECOND);

        // 此处的倒计时的截止时间为下一天凌晨
        ALog.d("TIME: " + mHour + ":" + mMinute + ":" + mSecond);
        int hour = 24 - mHour - 1;
        int minute = 60 - mMinute;
        int second = 60 - mSecond;
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
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == SaveCode.REQUESCITYTCODE) {
            String city_name = data.getStringExtra(SaveCode.CITYNAME);
            cityName.setText("选中的城市是: " + city_name);
        } else if (resultCode == SaveCode.REQUESTCONTACTCODE) {
            String contact = data.getStringExtra(SaveCode.CONTACT);
            contactName.setText("选中的名字是: " + contact);
        } else {
            ToastUtil.showShort(getContext(), "Nothing");
        }
    }
}
