package cn.xiyuanzaixian.xxx.indexable.rx;

import com.blankj.aloglibrary.ALog;

/**
 * Created by xxx on 2017/4/14.
 */

public class Student implements Observer {
    public String name;

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void say(String message) {
        // System.out.println(message + ":" + this.name + "到你妹");
        ALog.d(message + ":" + this.name + "到你妹");
    }
}