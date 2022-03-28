package ru.mirea.goryacheva.looper;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

import java.util.concurrent.ThreadLocalRandom;

public class MyLooper extends Thread {
    private int number = 0;
    Handler handler;
    int Miliseconds;

    /*@SuppressLint("HandlerLeak")
    @Override
    public void run() {
        Log.d("MyLooper", "run");

        Looper.prepare();
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                Log.d("MyLooper", number + ":"+ msg.getData().getString("KEY"));
                number++;
            }
        };
        Looper.loop();*/

    @SuppressLint("HandlerLeak")
    @Override
    public void run() {
        Log.d("MyLooper", "run");

        Looper.prepare();
        Miliseconds = ThreadLocalRandom.current().nextInt(18,40);
        handler = new Handler(){
            @Override
            public void handleMessage(Message msg){
                Miliseconds = ThreadLocalRandom.current().nextInt(18,40);

                try { Thread.sleep(Miliseconds); } catch (InterruptedException e) {}

                Log.d("MyLooper", "Age" + ":"+ msg.getData().getInt("AGE"));
                Log.d("MyLooper", "Work" + ":"+ msg.getData().getString("WORK"));
            }
        };
        Looper.loop();
    }
}