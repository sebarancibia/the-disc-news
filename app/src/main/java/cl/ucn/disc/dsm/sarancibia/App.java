/*
 * Copyright (c) 2021. Sebastián Arancibia-Arzic
 */

package cl.ucn.disc.dsm.sarancibia;

import android.app.Application;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * The Main App
 */
public class App extends Application {

    /**
     * Initialize the Application
     */
    @Override
    public void onCreate(){
        super.onCreate();
        Fresco.initialize(this);
    }
}
