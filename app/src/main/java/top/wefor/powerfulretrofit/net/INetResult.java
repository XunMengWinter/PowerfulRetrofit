package top.wefor.powerfulretrofit.net;

/**
 * Created on 16/6/28 16:40.
 *
 * @author ice, GitHub: https://github.com/XunMengWinter
 */
public interface INetResult<T> {

    /**
     * 该方法在请求完成是第一个执行,
     * 可用于执行一些必须操作, 比如停止进度条.
     */
    abstract void onComplete();

    /**
     * 请求成功, 返回一个实体
     */
    abstract void onSuccess(T model);

    /**
     * 请求失败, 对错误信息进行处理,
     * 默认显示一个Toast提醒用户.
     */

    void onFail(int errorCode, String msg);

}
