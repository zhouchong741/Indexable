package cn.xiyuanzaixian.xxx.indexable.rx.simple5;

/**
 * Created by xxx on 2017/4/14.
 */

public class NetBean {

    private FormBean form;

    public FormBean getForm() {
        return form;
    }

    public void setForm(FormBean form) {
        this.form = form;
    }

    public static class FormBean {
        private String username;
        private String password;

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
