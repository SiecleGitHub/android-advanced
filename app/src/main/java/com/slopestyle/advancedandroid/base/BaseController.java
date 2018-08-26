package com.slopestyle.advancedandroid.base;

import android.content.Context;
import android.support.annotation.NonNull;

import com.bluelinelabs.conductor.Controller;
import com.slopestyle.advancedandroid.di.Injector;

public abstract class BaseController extends Controller{

    private boolean injected = false;

    @Override
    protected void onContextAvailable(@NonNull Context context) {
        if(!injected) {
            // Controller instances are retained across config changes, so this method can be called more than once. This makes
            // sure we don't waste any time injecting more than once, though technically it wouldn't change functionality.
            Injector.inject(this);
            injected = true;
        }
        super.onContextAvailable(context);
    }
}
