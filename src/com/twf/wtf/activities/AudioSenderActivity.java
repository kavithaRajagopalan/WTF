package com.twf.wtf.activities;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.Toast;
import com.twf.wtf.R;
import com.twf.wtf.tasks.TimerTask;

public class AudioSenderActivity extends Activity {
    private TimerTask timerTask;
    private Button recordButton;
    private Chronometer timer;

    private void togglePlayReset() {
        recordButton = (Button) findViewById(R.id.play_reset_button);
        recordButton.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                timerTask = new TimerTask(timer);
                timerTask.execute();
                startStreaming();
                return true;
            }
        });
        recordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!timerTask.isCancelled())
                    timerTask.cancelTask();
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
