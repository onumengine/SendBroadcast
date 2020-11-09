package com.ebookfrenzy.sendbroadcast;

import androidx.appcompat.app.AppCompatActivity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.view.View;

public class MainActivity extends AppCompatActivity
{

    BroadcastReceiver receiver;
    MyBoundService boundService;
    boolean serviceIsBound;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureReceiver();
        triggerService();
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();
        unregisterReceiver(receiver);
    }

    public void sendBroadcast(View view)
    {
        Intent broadcastIntent = new Intent();
        broadcastIntent.setAction("default action");
        broadcastIntent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES);
        sendBroadcast(broadcastIntent);
    }

    public void configureReceiver()
    {
        receiver = new MyReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("default action");
        intentFilter.addAction("android.intent.action.ACTION_POWER_DISCONNECTED");
        intentFilter.addAction("android.intent.action.ACTION_POWER_CONNECTED");
        intentFilter.addAction("android.intent.action.BOOT_COMPLETED");
        registerReceiver(receiver, intentFilter);
    }

    public void triggerService()
    {
        Intent serviceIntent = new Intent(this, MyIntentService.class);
        startService(serviceIntent);
    }

    public void buttonClick(View view)
    {
        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    private void triggerBoundService()
    {
        Intent boundServiceIntent = new Intent(this, MyBoundService.class);
        bindService(boundServiceIntent, myConnection, Context.BIND_AUTO_CREATE);
    }

    private ServiceConnection myConnection = new ServiceConnection()
    {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder)
        {
            MyBoundService.LocalBinder binder = (MyBoundService.LocalBinder) iBinder;
            boundService = binder.getService();
            serviceIsBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName)
        {
            serviceIsBound = false;
        }
    };
}