package myapp.com.dishwasherproject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import java.util.concurrent.TimeUnit;

public class popUp extends Activity
{
    TextView text1,info;
    MediaPlayer mp;

    Button start,oK;
    private static final String FORMAT = "%02d:%02d:%02d";
    private boolean isPaused = false;
    private boolean isCanceled = false;
    private long timeRemaining = 0;


    int washTime;
    String information,hourText;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pop_up);
        MainScreen.activeMode = true;

        information = getIntent().getStringExtra("quickInfo");
        hourText = getIntent().getStringExtra("quickHourText");
        washTime = getIntent().getIntExtra("washTiming",7200000);
        DisplayMetrics dn = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dn);
        int width = dn.widthPixels;
        int height = dn.heightPixels;
        getWindow().setLayout((int) (width * .8), (int) (height * .7));
        WindowManager.LayoutParams params = getWindow().getAttributes();
        params.gravity = Gravity.CENTER;
        params.x = 0;
        params.y = -20;
        getWindow().setAttributes(params);

        info = findViewById(R.id.textInfo);
        start = findViewById(R.id.start);
        oK = findViewById(R.id.ok);
        oK.setVisibility(View.GONE);
        final Button btnPause =findViewById(R.id.pause);
        final Button btnResume = findViewById(R.id.resume);
        final Button btnCancel = findViewById(R.id.cancel);
        btnPause.setEnabled(false);btnPause.setVisibility(View.INVISIBLE);
        btnResume.setEnabled(false);btnResume.setVisibility(View.INVISIBLE);
        btnCancel.setEnabled(false);btnCancel.setVisibility(View.INVISIBLE);
        info.setText(information);
        info.setTextColor(Color.BLACK);

        text1 = findViewById(R.id.textView1);
        text1.setText(hourText);
        text1.setTextSize(50.0f);
        text1.setTextColor(Color.BLACK);

        start.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View view)
            {
                text1.setText("");
                isPaused = false;
                isCanceled = false;
                start.setEnabled(false);
                start.setVisibility(View.GONE);
                btnResume.setEnabled(false);
                btnResume.setVisibility(View.VISIBLE);
                btnPause.setVisibility(View.VISIBLE);
                btnPause.setEnabled(true);
                btnCancel.setEnabled(true);
                btnCancel.setVisibility(View.VISIBLE);


                long millisInFuture = washTime;
                long countDownInterval = 1000;
                new CountDownTimer(millisInFuture,countDownInterval)
                {
                    @SuppressLint("DefaultLocale")
                    public void onTick(long millisUntilFinished)
                    {
                        if(isPaused || isCanceled)
                        {
                            cancel();
                        }
                        else
                        {
                            text1.setText("" + String.format(FORMAT,TimeUnit.MILLISECONDS.toHours(millisUntilFinished), TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                            timeRemaining = millisUntilFinished;
                        }
                    }
                    @SuppressLint("ResourceType")
                    public void onFinish()
                    {
                        text1.setText("  Done");
                        start.setVisibility(View.GONE);
                        btnPause.setVisibility(View.GONE);
                        btnResume.setVisibility(View.GONE);
                        btnCancel.setVisibility(View.GONE);
                        oK.setVisibility(View.VISIBLE);


                       //alarm
                        Uri notification = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
                        mp = MediaPlayer.create(getApplicationContext(), notification);
                        mp.setLooping(true);
                        mp.start();
                        oK.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v)
                            {
                                mp.stop();
                                oK.setVisibility(View.GONE);
                                finish();
                            }
                        });


                    }
                }.start();
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                isPaused = true;
                btnResume.setEnabled(true);btnCancel.setEnabled(true);
                start.setEnabled(false);start.setVisibility(View.GONE);btnPause.setEnabled(false);
            }
        });
        btnResume.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                start.setEnabled(false);
                start.setVisibility(View.GONE);
                btnResume.setEnabled(false);btnPause.setEnabled(true);btnCancel.setEnabled(true);
                isPaused = false;
                isCanceled = false;

                long millisInFuture = timeRemaining;
                long countDownInterval = 1000;
                new CountDownTimer(millisInFuture, countDownInterval)
                {
                    @SuppressLint("DefaultLocale")
                    public void onTick(long millisUntilFinished)
                    {
                        if(isPaused || isCanceled)
                        {
                            cancel();
                        }
                        else {
                            text1.setText("" + String.format(FORMAT,TimeUnit.MILLISECONDS.toHours(millisUntilFinished), TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(millisUntilFinished)), TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
                            timeRemaining = millisUntilFinished;
                        }
                    }
                    public void onFinish(){
                        text1.setText("                    "+"Done");
                        btnPause.setEnabled(false);
                        btnResume.setEnabled(false);
                        btnCancel.setEnabled(false);
                        start.setEnabled(true);
                    }
                }.start();

                btnCancel.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        isCanceled = true;
                        btnPause.setEnabled(false);btnPause.setVisibility(View.GONE); btnResume.setEnabled(false); btnResume.setVisibility(View.GONE); btnCancel.setEnabled(false);btnCancel.setVisibility(View.GONE);
                        start.setEnabled(true);
                        start.setVisibility(View.VISIBLE);
                        text1.setText("stopped");
                    }
                });
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v)
            {
                isCanceled = true;
                btnPause.setEnabled(false);btnPause.setVisibility(View.GONE);
                btnResume.setEnabled(false);btnResume.setVisibility(View.GONE);
                btnCancel.setEnabled(false);btnCancel.setVisibility(View.GONE);
                start.setEnabled(true);
                start.setVisibility(View.VISIBLE);
                text1.setText("stopped");
            }
        });
    }
    @Override
    public void onStop() {
        super.onStop();
        MainScreen.activeMode = false;
    }

}

