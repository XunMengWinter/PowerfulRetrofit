package top.wefor.powerfulretrofit.data;

import android.content.Context;
import android.content.SharedPreferences;


/**
 *
 */
public class PreferencesHelper {

    public static final String PREF_FILE_NAME = "android_pref_file";

    private final SharedPreferences mPref;

    public PreferencesHelper(Context context) {
        mPref = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void clear() {
        mPref.edit().clear().apply();
    }

    public String getToken() {
        return mPref.getString("token", "");
    }

    public void setToken(String token) {
        mPref.edit().putString("token", token).apply();
    }

}
