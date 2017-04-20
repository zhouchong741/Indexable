package cn.xiyuanzaixian.xxx.indexable;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
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

    // 处理点击空白区域软键盘隐藏
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {

        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isShouldHideInput(view, ev)) {
                InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (inputManager != null) {
                    inputManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }
        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    private boolean isShouldHideInput(View view, MotionEvent ev) {

        if (view != null && (view instanceof EditText)) {
            int[] leftTop = {0, 0};
            view.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + view.getHeight();
            int right = left + view.getWidth();
            if (ev.getX() > left && ev.getX() < right && ev.getY() > top && ev.getY() < bottom) {
                return false;
            } else {
                view.setFocusable(false);
                view.setFocusableInTouchMode(true);
                return true;
            }
        }
        return false;
    }
}
