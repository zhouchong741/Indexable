package cn.xiyuanzaixian.xxx.indexable.rx.simple1;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xxx on 2017/4/14.
 */

public class Teacher implements Observable {

    private List<Observer> observers = new ArrayList<>();

    @Override
    public void attach(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void detach(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.say(message);
        }
    }
}
