package com.lab.bus.tracker;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.ButterKnife;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    @Bind(R.id.home_time_text) EditText mTime;

    @Bind(R.id.home_register_btn)Button mTimeRegisterBtn;

    @Bind(R.id.home_tracking_btn)Button mTrackingBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);

        mTimeRegisterBtn.setOnClickListener(this);
        mTrackingBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_register_btn:

                break;

            case R.id.home_tracking_btn:
                break;
        }
    }
}
