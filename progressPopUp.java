package myapp.com.dishwasherproject;

import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class progressPopUp extends Activity
{
    ProgressBar progressBar;
    private int mprogress = 0;
    private Handler mhandler = new Handler();
    String toastMessage;
    TextView prompt;
    String txt,flag;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_progress_pop_up);
        progressBar = findViewById(R.id.progressBar);
        prompt = findViewById(R.id.prompt);
        toastMessage = getIntent().getStringExtra("mess");
        txt = getIntent().getStringExtra("txt");
        flag = getIntent().getStringExtra("flag");
        prompt.setText(txt);
        prompt.setTextColor(Color.WHITE);
        if(flag.equals("prewash"))
        {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.prewash);
            mediaPlayer.start();
        }
        else if(flag.equals("dry"))
        {
            mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.blowdryer);
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (mprogress<100)
                {
                    mprogress++;
                    android.os.SystemClock.sleep(50);
                    mhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(mprogress);
                        }
                    });
                }
            }
        }).start();

        new CountDownTimer(3000, 3000)
        {
            public void onTick(long millisUntilFinished) {

            }

            public void onFinish()
            {
                mediaPlayer.stop();
                mediaPlayer.release();
                Toast.makeText(getApplicationContext(),toastMessage, Toast.LENGTH_LONG).show();
                finish();
            }
        }.start();
    }
}
