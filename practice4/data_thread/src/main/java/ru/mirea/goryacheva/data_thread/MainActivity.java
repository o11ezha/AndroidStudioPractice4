package ru.mirea.goryacheva.data_thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {
    TextView tvInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvInfo = findViewById(R.id.textView);

        final Runnable runn1 = new Runnable() {
            public void run() {
                tvInfo.setText("runn1");
            }
        };

        final Runnable runn2 = new Runnable() {
            public void run() {
                tvInfo.setText("runn2");
            }
        };

        final Runnable runn3 = new Runnable() {
            public void run() {
                tvInfo.setText("runn3");
            }
        };

        Thread t = new Thread(new Runnable() {
            public void run() {
                try {
                    TimeUnit.SECONDS.sleep(2);
                    runOnUiThread(runn1);
                    //Выполняет указанный runnable в потоке пользовательского интерфейса (главном потоке приложения).
                    //Если текущий поток - поток пользовательского интерфейса, то действие выполняется немедленно.
                    //Если текущий поток не поток пользовательского интерфейса, действие отправляется в очередь событий.
                    TimeUnit.SECONDS.sleep(1);
                    tvInfo.postDelayed(runn3, 2000);
                    //postDelayed() Добавляет выполняемый runnable в очередь, который будет запущен по истечении указанного времени.
                    //Время, которое поток проводит "во сне", будет добавлено дополнительно к заранее указаному времени.
                    tvInfo.post(runn2);
                    //post() Добавляет выполняемый runnable в очередь.
                    //Выполняемый объект будет выполняться в потоке, к которому присоединен этот обработчик.
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
    }
}