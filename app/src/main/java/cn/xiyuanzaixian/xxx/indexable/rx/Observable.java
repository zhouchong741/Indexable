package cn.xiyuanzaixian.xxx.indexable.rx;

/**
 * Created by xxx on 2017/4/14.
 */

public interface Observable {
    public void attach(Observer observer);

    public void detach(Observer observer);

    public void notifyObservers(String message);

}