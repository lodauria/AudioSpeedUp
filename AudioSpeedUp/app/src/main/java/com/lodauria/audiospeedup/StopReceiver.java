package com.lodauria.audiospeedup;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class StopReceiver extends BroadcastReceiver {
    @Override
    // Since this method is static we only modify a global variable of the main activity
    // The thread running in background will detect this change
    // Is not a good solution, check TODOs of the main for more
    public void onReceive(Context context, Intent intent) {
        MainActivity.mp_stop = false;
    }
}
