package com.twf.wtf.tasks;

import android.os.AsyncTask;
import android.os.SystemClock;
import android.widget.Chronometer;

public class TimerTask extends AsyncTask {
    private final Chronometer timer;

    public TimerTask(Chronometer chronometer) {
        this.timer = chronometer;
    }

    public void cancelTask() {
        timer.stop();
    }

    @Override
    protected Object doInBackground(Object... objects) {
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        timer.setBase(SystemClock.elapsedRealtime());
        timer.start();
        timer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
            @Override
            public void onChronometerTick(Chronometer chronometer) {
                timer.setText(chronometer.getText());
            }
        });
    }
}
