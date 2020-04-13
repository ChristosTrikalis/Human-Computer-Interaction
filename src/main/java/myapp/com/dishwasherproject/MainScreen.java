package myapp.com.dishwasherproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class MainScreen extends Activity
{
    ImageButton quickStart,timer ,wmode,more;
    static int hour,minutes;
    static boolean activeMode = false;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        quickStart = findViewById(R.id.quickStart);
        timer = findViewById(R.id.timer);
        wmode = findViewById(R.id.wmode);
        more = findViewById(R.id.more);


        wmode.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view)
        {
            Intent intent = new Intent(MainScreen.this,Modes.class);
            startActivity(intent);
        }});

        quickStart.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view)
        {
            Intent intent = new Intent(MainScreen.this,popUp.class);
            intent.putExtra("quickInfo","              Default Mode\n             60° C / KWh: 2\n    water consumption: 3 Lt");
            intent.putExtra("quickHourText","02:00:00");
            intent.putExtra("washTiming",7200000);
            startActivity(intent);
        }});
        more.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view)
        {
            Intent intent2 = new Intent(MainScreen.this,More.class);
            startActivity(intent2);
        }});
        timer.setOnClickListener(new View.OnClickListener() {@Override public void onClick(View view)
        {
            Intent intent3 = new Intent(MainScreen.this,Timer.class);
            startActivity(intent3);
        }});

    }



}
