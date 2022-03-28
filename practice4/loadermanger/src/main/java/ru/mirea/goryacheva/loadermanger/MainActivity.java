package ru.mirea.goryacheva.loadermanger;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<String> {

    public final String TAG = this.getClass().getSimpleName();
    private int LoaderID = 1234;

    EditText WordField;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WordField = findViewById(R.id.editTextTextPersonName);
        textView = findViewById(R.id.textView);

    }

    public void onClick(View view) {
        Bundle bundle = new Bundle();
        bundle.putString(MyLoader.ARG_WORD, WordField.getText().toString());

        getSupportLoaderManager().initLoader(LoaderID, bundle, this);
    }

    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        if (i == LoaderID) {
            Toast.makeText(this, "onCreateLoader:" + i, Toast.LENGTH_SHORT).show();
            return new MyLoader(this, bundle);
        }
        return null;
    }

    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        if (loader.getId() == LoaderID) {
            Log.d(TAG, "onLoadFinished" + s);
            Toast.makeText(this, "onLoadFinished:" + s, Toast.LENGTH_SHORT).show();

            textView.setText(s);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        Log.d(TAG, "onLoaderReset");
    }

}