package cn.xiyuanzaixian.xxx.indexable.rx.simple5;

import com.google.gson.Gson;

/**
 * Created by xxx on 2017/4/14.
 */

public class UserBean {
    private String username;
    private String passwrod;

    public UserBean(String username, String passwrod) {
        this.username = username;
        this.passwrod = passwrod;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPasswrod() {
        return passwrod;
    }

    public void setPasswrod(String passwrod) {
        this.passwrod = passwrod;
    }

    @Override
    public String toString() {
        return new Gson().toJson(this);
    }
}
