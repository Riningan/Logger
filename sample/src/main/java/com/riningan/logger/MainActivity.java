package com.riningan.logger;

import android.os.Bundle;
import com.riningan.util.Logger;


public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Logger.debug();
        Logger.forThis(this).debug();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Logger.error(new RuntimeException("sfsefsef"));
        Logger.forThis(this).error(new RuntimeException("sfsefsef"));
    }
}