package cn.xiyuanzaixian.xxx.indexable.rx.simple6;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by xxx on 2017/4/14.
 */

public interface ApiService {

    @GET("/get")
    Observable<NetBean> getCartList (@Query("shopName")String shopName);
}
