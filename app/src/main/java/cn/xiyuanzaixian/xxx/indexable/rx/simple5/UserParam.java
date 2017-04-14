package cn.xiyuanzaixian.xxx.indexable.rx.simple5;

import com.google.gson.Gson;

/**
 * Created by xxx on 2017/4/14.
 */

public class UserParam {
    public String param1;
    public String param2;

    public UserParam(String param1, String param2) {
        this.param1 = param1;
        this.param2 = param2;
    }

    public String getParam1() {
        return param1;
    }

    public void setParam1(String param1) {
        this.param1 = param1;
    }

    public String getParam2() {
        return param2;
    }

    public void setParam2(String param2) {
        this.param2 = param2;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
