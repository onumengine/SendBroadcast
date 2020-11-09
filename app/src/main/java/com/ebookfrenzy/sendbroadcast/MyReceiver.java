package com.ebookfrenzy.sendbroadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

public class MyReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(Context context, Intent intent)
    {
        switch (intent.getAction()) {
            case "default action":
                toast(context, "TextView clicked");
                break;
            case "android.intent.action.ACTION_POWER_DISCONNECTED":
                toast(context, "Charger disconnected");
                break;
            case "android.intent.action.ACTION_POWER_CONNECTED":
                toast(context, "Charger connected");
                break;
            case "android.intent.action.BOOT_COMPLETED":
                toast(context, "Boot completed");
                break;
        }
        context.startService(new Intent(context, MyService.class));
    }

    private void toast(Context context, String message)
    {
        Toast.makeText(context, message, Toast.LENGTH_LONG).show();
    }
}
