package com.fullsail.jketch.j_ketcham_advancedviews;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;


public class JKetcham_AdvancedViews extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jketcham__advanced_views);

//        //Getting the current screen orientation and then setting the orientation of app to that.
//        int deviceOrientation = getResources().getConfiguration().orientation;
//        switch(deviceOrientation) {
//            case Configuration.ORIENTATION_PORTRAIT:
////                setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//                break;
//            case Configuration.ORIENTATION_LANDSCAPE:
////                setRequestedOrientation (ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//                break;
//        }

        

    }

    // Check screen orientation, then go from here
    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
//            if (android.R.layout) {
//
//            }

        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {

        }
    }

}
