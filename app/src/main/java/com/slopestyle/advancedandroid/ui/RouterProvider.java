package com.slopestyle.advancedandroid.ui;

import com.bluelinelabs.conductor.Controller;
import com.bluelinelabs.conductor.Router;

public interface RouterProvider {

    Router getRouter();

    Controller initialScreen();
}
