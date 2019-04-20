package edu.gatech.spacetraders.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.os.CountDownTimer;

import edu.gatech.spacetraders.R;

/**
 * Created loading screen
 */
public class Loading extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);

        CountDownTimer timer = new CountDownTimer(1850, 1000) //10 second Timer
        {
            public void onTick(long l)
            {

            }

            @Override
            public void onFinish()
            {
                kill_activity();
            };
        }.start();
    }

    void kill_activity()
    {
        finish();
    }
}
