package cn.xiyuanzaixian.xxx.indexable.rx.simple5;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by xxx on 2017/4/14.
 */

public interface ApiService {

    @FormUrlEncoded
    @POST("/post")
    Observable<NetBean> getUserInfo(@Field("username") String username, @Field("password")String password);
}
