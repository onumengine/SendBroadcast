package com.ebookfrenzy.sendbroadcast;

import android.app.Service;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service
{
    public static final String TAG = "ServiceExample";

    public MyService()
    {
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId)
    {
        new AsyncServiceTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, startId);
        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent)
    {
        Log.d(TAG, "Service called onBind");
        return null;
    }

    @Override
    public void onCreate()
    {
        super.onCreate();
        Log.d(TAG, "Service called onCreate");
    }

    @Override
    public void onDestroy()
    {
        Log.d(TAG, "Service called onDestroy");
    }

    private class AsyncServiceTask extends AsyncTask<Integer, Integer, String>
    {

        @Override
        protected String doInBackground(Integer... integers)
        {
            int startId = integers[0];
            int i = 0;
            while (i <= 3)
            {
                publishProgress(integers[0]);
                try
                {
                    Thread.sleep(10000);
                    i++;
                }
                catch (Exception e)
                {}
            }
            return ("Service complete " + startId);
        }

        @Override
        protected void onPostExecute(String result)
        {
            Log.d(TAG, result);
        }

        @Override
        protected void onPreExecute()
        {
            //TODO
        }

        @Override
        protected void onProgressUpdate(Integer... values)
        {
            super.onProgressUpdate(values);
            Log.d(TAG, "Service running " + values[0]);
        }
    }
}
