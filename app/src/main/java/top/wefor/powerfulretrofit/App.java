package top.wefor.powerfulretrofit;

import android.app.Application;
import android.widget.Toast;

import top.wefor.powerfulretrofit.data.PreferencesHelper;


/**
 * Created on 8/7/16 23:36.
 *
 * @author ice
 */
public class App extends Application {

    private static App sApp;

    private Toast mToast;

    private PreferencesHelper mPreferencesHelper;

    public static App get() {
        App inst = sApp;  // <<< 在这里创建临时变量
        if (inst == null) {
            synchronized (App.class) {
                inst = sApp;
                if (inst == null) {
                    inst = new App();
                    sApp = inst;
                }
            }
        }
        return inst;  // <<< 注意这里返回的是临时变量
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
    }

    public void showToast(String msg) {
        if (mToast != null)
            mToast.cancel();
        mToast = Toast.makeText(this, msg, Toast.LENGTH_SHORT);
        mToast.show();
    }

    public PreferencesHelper getPreferencesHelper() {
        if (mPreferencesHelper == null)
            mPreferencesHelper = new PreferencesHelper(this);
        return mPreferencesHelper;
    }

}
