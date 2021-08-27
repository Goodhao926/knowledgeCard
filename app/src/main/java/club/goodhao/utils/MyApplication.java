package club.goodhao.utils;

import android.content.Context;

import org.litepal.LitePalApplication;

public class MyApplication extends LitePalApplication {
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
