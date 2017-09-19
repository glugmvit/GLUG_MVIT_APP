package com.smvit.glugmvit;

import android.app.Application;
import android.os.SystemClock;

/**
 * Created by Abhijeet Singh on 18-Aug-17. For simulating cold start for Splash Screen.
 */

public class SplashScreen_ColdStart extends Application {
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(1000);
    }
}
