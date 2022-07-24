package ru.nsu.ccfit.zuev.audio.serviceAudio;


import android.app.Activity;
import android.app.Application;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

import ru.nsu.ccfit.zuev.osu.AppException;
import ru.nsu.ccfit.zuev.osu.GlobalManager;
import ru.nsu.ccfit.zuev.osuplus.BuildConfig;

public class SaveServiceObject extends Application {
    //tzl: 这又是个什么类，为什么会继承Application，总不会就只是为了Override一个onCreate()吧

    static private SongService songService;
    private String string = "NONONNOONONO";

    public static void finishAllActivities() {
        if (GlobalManager.getInstance().getMainActivity() != null)
            GlobalManager.getInstance().getMainActivity().finish();
    }

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }

    public SongService getSongService() {
        return songService;
    }

    public void setSongService(SongService object) {
        songService = object;
        if (songService != null) {
            System.out.println("SongService Created!");
        } else {
            System.out.println("SongService is NULL");
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //注册App异常崩溃处理器
        //tzl:又一个应用监控SDK?
        UMConfigure.init(this,
                BuildConfig.DEBUG ?
                        "5fccbf9d19bda368eb483d62": // Debug key
                        "5fe567c044bb94418a649888", // Release key
                String.format("%s[%s]", BuildConfig.FLAVOR, BuildConfig.BUILD_TYPE),
                UMConfigure.DEVICE_TYPE_PHONE, "");
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);
        Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler());
        if (Build.VERSION.SDK_INT > 14) {
            registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
                @Override
                public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

                }

                @Override
                public void onActivityStarted(Activity activity) {

                }

                @Override
                public void onActivityResumed(Activity activity) {

                }

                @Override
                public void onActivityPaused(Activity activity) {

                }

                @Override
                public void onActivityStopped(Activity activity) {

                }

                @Override
                public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

                }

                @Override
                public void onActivityDestroyed(Activity activity) {
                    Log.w("onActivityDestroyed", "I'm going to Dead O_x");
                    if (songService != null) {
                        Log.w("onActivityDestroyed", "I'm Dead x_x");
                        songService.hideNotifyPanel();
                    }
                }
            });
        }
    }
}
