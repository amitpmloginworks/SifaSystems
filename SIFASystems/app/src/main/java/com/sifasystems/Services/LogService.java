package com.sifasystems.Services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import com.sifasystems.helper.SQLiteHandler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class LogService extends IntentService {
    public static final String PARAM_IN_MSG = "imsg";
    public static final String PARAM_OUT_MSG = "omsg";
    private SQLiteHandler db;

    public LogService() {
        super("LogIntentService");
        setIntentRedelivery(true);
    }

    @Override
    public int onStartCommand(@Nullable Intent intent, int flags, int startId) {
      //   db = new SQLiteHandler(LogService.this);
        Log.d("start service","on Start Command");
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
//
       String msg = intent.getStringExtra(PARAM_IN_MSG);
//        SystemClock.sleep(30000); // 30 seconds
//        String resultTxt = msg + " "
//                + DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis());
//        Log.d("start service",""+resultTxt);


      Log.d("on handle","started");



        ScheduledExecutorService scheduleTaskExecutor = Executors.newScheduledThreadPool(1);

// This schedule a task to run every 2 Minutes:
        scheduleTaskExecutor.scheduleAtFixedRate(new Runnable() {
            public void run() {
                // Do the actual job:
                String resultTxt =  " "
                + DateFormat.format("MM/dd/yy h:mmaa", System.currentTimeMillis());
               Log.d("Service", "Running"+resultTxt);


//                // In case if you need update UI, simply do this:
//                runOnUiThread(new Runnable() {
//                    public void run() {
//                        // update your UI component here.
//
//                    }
//                }); // end of runOnUiThread

            }
        }, 0, 120, TimeUnit.SECONDS);

    }

    }
