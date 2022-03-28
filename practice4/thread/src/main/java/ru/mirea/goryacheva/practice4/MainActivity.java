package ru.mirea.goryacheva.practice4;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    EditText lessonscounter;
    EditText daycounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView info = findViewById(R.id.textView);

        lessonscounter = findViewById(R.id.editTextTextPersonName3);
        daycounter = findViewById(R.id.editTextTextPersonName6);


        Thread mainThread = Thread.currentThread();
        info.setText("Текущий поток: " + mainThread.getName());

        mainThread.setName("MireaThread");
        info.append("\n Новое имя потока: " + mainThread.getName());
    }

    public void notworkingonClick(View view) {
        long endTime = System.currentTimeMillis() + 20 * 1000;
        while (System.currentTimeMillis() < endTime) {
            synchronized (this) {
                try {
                    wait(endTime - System.currentTimeMillis());
                } catch (Exception e) {
                }
            } //Оценка работоспобности: никакая
        }
    }

    int counter = 0;

    public void potokionClick(View view) {
        Runnable runnable = new Runnable() {
            public void run() {
                int numberThread = counter++;
                Log.i("ThreadProject", "Запущен поток № " + numberThread);
                long endTime = System.currentTimeMillis() + 20 * 1000;
                while (System.currentTimeMillis() < endTime) {
                    synchronized (this) {
                        try {
                            wait(endTime -
                                    System.currentTimeMillis());
                        } catch (Exception e) {
                        }
                    }
                }
                Log.i("ThreadProject", "Выполнен поток № " + numberThread);
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }

    int result;

    public void onClick(View view) {

        Runnable runnable = new Runnable() {
            public void run() {
                TextView resultnumber = findViewById(R.id.textView2);
                synchronized (this) {
                    try {
                        result = Integer.parseInt(lessonscounter.getText().toString()) / Integer.parseInt(daycounter.getText().toString());
                        resultnumber.setText(String.valueOf(result));
                    } catch (Exception e){}

                    lessonscounter.setText("");
                    daycounter.setText("");
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
    }
}