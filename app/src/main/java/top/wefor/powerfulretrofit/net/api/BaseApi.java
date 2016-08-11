package top.wefor.powerfulretrofit.net.api;

import com.orhanobut.logger.Logger;

import java.io.IOException;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import top.wefor.powerfulretrofit.App;
import top.wefor.powerfulretrofit.net.Urls;

/**
 * Created on 16/8/4.
 *
 * @author ice
 */
public class BaseApi {

    private Retrofit getRetrofit(final String baseUrl, final boolean isWithToken) {
        final OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder builder = original.newBuilder()
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .method(original.method(), original.body())
                        .header("key", "value")
                        .header("key2", "value2");
                if (isWithToken)
                    builder.header("token", App.get().getPreferencesHelper().getToken());

                Request finalRequest = builder.build();
                HttpUrl url = finalRequest.url().newBuilder()
                        .addQueryParameter("platform", "android")
                        .addQueryParameter("v", "1.0")
                        .build();

                Logger.i(url.toString());
                return chain.proceed(builder.build());
            }
        });

        OkHttpClient okHttpClient = httpClient.build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        return retrofit;
    }

    public Retrofit getRetrofit() {
        return getRetrofit(Urls.BASE_URL, false);
    }

    public Retrofit getRetrofitWithToken() {
        return getRetrofit(Urls.BASE_URL, true);
    }

}
