package com.slopestyle.advancedandroid.util;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

public class ButterknifeUtils {

    private ButterknifeUtils() {

    }

    public static void unbind(Unbinder unbinder) {
        if (unbinder != null) {
            try {
                unbinder.unbind();
            } catch (IllegalStateException e) {
                Timber.e(e, "Error unbinding views");
            }
        }
    }
}
