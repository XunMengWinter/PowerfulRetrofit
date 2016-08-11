package top.wefor.powerfulretrofit.net;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.orhanobut.logger.Logger;

import java.io.IOException;
import java.net.SocketTimeoutException;

import retrofit2.adapter.rxjava.HttpException;
import rx.Observer;
import top.wefor.powerfulretrofit.App;
import top.wefor.powerfulretrofit.data.entity.ErrorBodyEntity;

/**
 * 网络请求返回需要的模型
 * Created by ice on 3/3/16.
 */
public abstract class HttpObserver<T> implements Observer<T>, INetResult<T> {

    /**
     * 请求失败, 对错误信息进行处理,
     * 默认显示一个Toast提醒用户.
     */
    @Override
    public void onFail(int errorCode, String msg) {
        App.get().showToast(msg);
    }


    @Override
    public void onCompleted() {
        onComplete();
    }

    @Override
    public void onError(Throwable e) {
        onComplete();
        Logger.e(e.toString());
        if (e instanceof SocketTimeoutException) {
            App.get().showToast("连接超时");
        } else if (e instanceof HttpException) {
            HttpException httpException = (HttpException) e;
            Logger.e(httpException.code() + "");
            Logger.e(httpException.message() + "");
            if (httpException.response() != null && httpException.response().errorBody() != null) {
                try {
                    Logger.e(httpException.response().message());
                    String bodyStr = httpException.response().errorBody().string();
                    Logger.e(bodyStr);
                    ErrorBodyEntity errorBodyEntity = JSON.parseObject(bodyStr, ErrorBodyEntity.class);
                    onFail(httpException.code(), errorBodyEntity.getMessage());
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    @Override
    public void onNext(T model) {
        Logger.i(JSONObject.toJSONString(model));
        onSuccess(model);
    }

}
