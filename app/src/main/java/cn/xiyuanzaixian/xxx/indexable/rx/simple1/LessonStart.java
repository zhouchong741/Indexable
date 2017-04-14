package cn.xiyuanzaixian.xxx.indexable.rx.simple1;

/**
 * Created by xxx on 2017/4/14.
 */

public class LessonStart {

    public void rollCall() {
        Observable teacher = new Teacher();
        Observer xiaoming = new Student();
        xiaoming.setName("小明");
        Observer xiaohong = new Student();
        xiaohong.setName("小红");

        teacher.attach(xiaoming);
        teacher.attach(xiaohong);

        teacher.notifyObservers("点名了啊");
    }
}