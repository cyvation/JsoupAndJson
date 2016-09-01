package com.edu.gvn.jsoupdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.edu.gvn.jsoupdemo.network.Top100AsyncTask;

public class HomeActivity extends AppCompatActivity {

    private static final String TAG = HomeActivity.class.getSimpleName();
    private Toolbar mToolbar ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Top100AsyncTask async = new Top100AsyncTask(this);
        async.execute("Việt Nam");
    }




}
