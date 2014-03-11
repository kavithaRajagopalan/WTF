package com.twf.wtf.activities;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Chronometer;
import android.widget.Toast;
import android.widget.ToggleButton;
import com.twf.wtf.R;
import com.twf.wtf.tasks.TimerTask;

import static android.view.View.OnClickListener;

public class AudioSenderActivity extends Activity {
    private TimerTask timerTask;
    private ToggleButton recordButton;
    private Chronometer timer;

    private void togglePlayReset() {
        recordButton = (ToggleButton) findViewById(R.id.play_reset_button);
        recordButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (timerTask != null && !timerTask.isCancelled())
                    timerTask.cancelTask();
                else {
                    timerTask = new TimerTask(timer);
                    timerTask.execute();
                    startStreaming();
                }
                recordButton.toggle();
            }
        });
    }

    private void startStreaming() {
        Toast.makeText(this, "Start Streaming Voice", Toast.LENGTH_LONG);
//        SipManager manager = SipManager.newInstance(this);
        //SIP maynot be the way to go
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.talk);
        timer = (Chronometer) findViewById(R.id.chronometer);
        togglePlayReset();
    }
}
