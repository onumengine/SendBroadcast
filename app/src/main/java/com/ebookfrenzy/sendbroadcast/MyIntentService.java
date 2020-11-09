package com.ebookfrenzy.sendbroadcast;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.JobIntentService;

public class MyIntentService extends JobIntentService
{

    private final String TAG = "ServiceExample";

    @Override
    protected void onHandleWork(@NonNull Intent intent)
    {
        for(int i = 0; i < 2; i++)
        {
            Log.i(TAG, "Intent service started");
        }
    }
}
