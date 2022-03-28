package ru.mirea.goryacheva.looper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    MyLooper myLooper;
    EditText WorkField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WorkField = findViewById(R.id.editTextTextPersonName);

        myLooper  = new MyLooper();
        myLooper.start();
    }

    public void onClick(View view) {
        Message msg = new Message();
        Bundle bundle = new Bundle();

        String work = WorkField.getText().toString();
        int age = 0;

        if (myLooper != null) {
            myLooper.handler.sendMessage(msg);
            age = myLooper.Miliseconds;
        }

        bundle.putString("WORK", work);
        bundle.putInt("AGE", age);

        msg.setData(bundle);
    }
}