package com.madhram.testalarstudios.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.madhram.testalarstudios.utils.MessageView;

import io.reactivex.disposables.Disposable;

public abstract class BaseActivity extends AppCompatActivity implements MessageView {

    protected Disposable mDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutResource());
    }

    protected abstract int getLayoutResource();

    @Override
    public void showMessage(int message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void showMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}