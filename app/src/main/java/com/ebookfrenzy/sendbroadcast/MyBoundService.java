package com.ebookfrenzy.sendbroadcast;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

public class MyBoundService extends Service
{
    private final IBinder binder = new LocalBinder();

    public MyBoundService()
    {
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        return binder;
    }

    public class LocalBinder extends Binder
    {
        MyBoundService getService()
        {
            return MyBoundService.this;
        }
    }
}
