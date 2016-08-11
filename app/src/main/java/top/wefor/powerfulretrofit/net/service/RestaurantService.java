package top.wefor.powerfulretrofit.net.service;

import android.content.Context;

import com.zhikun.ishangban.BuildConfig;
import com.zhikun.ishangban.net.HttpResult;
import com.zhikun.ishangban.net.Urls;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import rx.Observable;

public interface RestaurantService {

    String ENDPOINT = Urls.BASE_URL;

    @Headers({
            "Accept: application/vnd.github.v3.full+json",
            "User-Agent: Retrofit-Sample-App"
    })
    @POST("/login")
    Observable<HttpResult.FoodListResult> getFoodList(

    );

    /********
     * Factory class that sets up a new boilerplate service
     *******/
    class Factory {

        public static RestaurantService makeRestaurantService(Context context) {
            OkHttpClient okHttpClient = new OkHttpClient();
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY
                    : HttpLoggingInterceptor.Level.NONE);
//            okHttpClient.interceptors().add(logging);

            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(RestaurantService.ENDPOINT)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .build();
            return retrofit.create(RestaurantService.class);
        }

    }
}
