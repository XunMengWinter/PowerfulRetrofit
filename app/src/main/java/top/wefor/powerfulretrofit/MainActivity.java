package top.wefor.powerfulretrofit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.orhanobut.logger.Logger;

import okhttp3.Headers;
import retrofit2.Response;
import rx.Observer;
import top.wefor.powerfulretrofit.data.entity.UserEntity;
import top.wefor.powerfulretrofit.net.HttpObserver;
import top.wefor.powerfulretrofit.net.api.UserApi;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void retrofitTest() {
        new UserApi().getInfo("XunMengWinter").subscribe(
                new Observer<UserEntity>() {
                    @Override
                    public void onCompleted() {
                        // 成功后
                    }

                    @Override
                    public void onError(Throwable e) {
                        // 所有的错误全在这里
                    }

                    @Override
                    public void onNext(UserEntity userEntity) {
                        // 成功后
                    }
                }
        );

        new UserApi().getInfo("XunMengWinter").subscribe(
                new HttpObserver<UserEntity>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSuccess(UserEntity model) {

                    }
                });

        new UserApi().login(null)
                .subscribe(new HttpObserver<Response<UserEntity>>() {
                    @Override
                    public void onComplete() {

                    }

                    @Override
                    public void onSuccess(Response<UserEntity> model) {
                        Headers headers = model.headers();
                        Logger.i(headers.toString());
                        String token = headers.get("token");
                        App.get().getPreferencesHelper().setToken(token);
                    }
                });
    }


}
