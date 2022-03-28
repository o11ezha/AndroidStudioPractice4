package ru.mirea.goryacheva.loadermanger;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.loader.content.AsyncTaskLoader;

import java.util.concurrent.ThreadLocalRandom;

public class MyLoader extends AsyncTaskLoader<String> {

    private String Word;
    public static final String ARG_WORD = "word";

    public MyLoader(@NonNull Context context, Bundle args) {
        super(context);
        if (args != null)
            Word = args.getString(ARG_WORD);
    }

    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }

    StringBuffer RandomWord;
    StringBuffer WordBuffer;

    int WordLenght;
    int Index;

    @Nullable
    @Override
    public String loadInBackground() {
        WordBuffer = new StringBuffer(Word);
        RandomWord = new StringBuffer();
        WordLenght = WordBuffer.length();

        for (int i = 0; i < WordLenght; i++) {
            Index = ThreadLocalRandom.current().nextInt(WordBuffer.length());
            RandomWord.append((WordBuffer.charAt(Index))) ;
            WordBuffer.deleteCharAt(Index);
        }

        return String.valueOf(RandomWord);
    }
}