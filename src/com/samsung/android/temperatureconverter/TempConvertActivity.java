package com.samsung.android.temperatureconverter;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class TempConvertActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_convert);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.temp_convert, menu);
        return true;
    }
}
