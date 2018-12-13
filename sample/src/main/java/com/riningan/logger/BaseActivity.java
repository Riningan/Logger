package com.riningan.logger;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.riningan.util.Logger;

abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Logger.debug();
        Logger.forThis(this).debug();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.forThis(this).error(new RuntimeException("sfsefsef"));
    }
}
