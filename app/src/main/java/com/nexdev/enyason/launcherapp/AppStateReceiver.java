package com.nexdev.enyason.launcherapp;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Enyason on 3/14/2018.
 */

public class AppStateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        LauncherAdapter.setUpApps();
    }
}
