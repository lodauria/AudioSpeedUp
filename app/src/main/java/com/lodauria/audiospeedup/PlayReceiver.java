package com.lodauria.audiospeedup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class PlayReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        MainActivity.mp_play = false;
    }
}
