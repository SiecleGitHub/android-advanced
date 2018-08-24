package com.slopestyle.advancedandroid.di;

import android.app.Activity;

import com.slopestyle.advancedandroid.base.BaseActivity;

public class Injector {
    private Injector() {

    }

    public static void inject(Activity activity) {
        ActivityInjector.get(activity).inject(activity);
    }

    public static void clearComponent(Activity activity) {
        ActivityInjector.get(activity).clear(activity);
    }
}
