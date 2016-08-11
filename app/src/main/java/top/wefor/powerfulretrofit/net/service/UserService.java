package top.wefor.powerfulretrofit.net.service;

import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import rx.Observable;
import top.wefor.powerfulretrofit.data.LoginRequest;
import top.wefor.powerfulretrofit.data.entity.UserEntity;

/**
 * Created on 16/8/4.
 *
 * @author ice
 */
public interface UserService {

    @POST("/login")
    Observable<Response<UserEntity>> login(@Body LoginRequest loginRequest);

    /* == ↑ 同于上面 . 相当于把LoginRequest 里的参数摆放出来，效果是一样的 */
    @FormUrlEncoded
    @POST("/login")
    Observable<Response<UserEntity>> loginOtherWay(
            @Field("phone") String phone,
            @Field("password") String password,
            @Field("macAddress") String macAddress,
            @Field("ip") String ip
    );

    /* == 同于 @GET("/users/username")... */
    @GET("/users/{username}")
    Observable<UserEntity> getInfo(@Path("username") String username);

}
