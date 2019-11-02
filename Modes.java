package myapp.com.dishwasherproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Modes extends Activity
{
    ImageButton eco,glass,pot,fast;
    //turn of modes:
    //1. Default
    //2. Eco
    //3. Glass
    //4. Pot
    //5. Fast

    int[] washTime = {5400000,7200000,7200000,1000};//3600000
    String[] hourTexts = {"01:30:00","02:00:00","02:00:00","00:05:00"};
    String[] info = {
            "             Economy Mode\n              40° C / KWh: 1\n   water consumption: 1,5 Lt",
            "              Glass Mode\n            55° C / KWh: 2\n   water consumption: 2 Lt",
            "                 Pot Mode\n            65° C / KWh: 2,5\n   water consumption: 2,5 Lt",
            "                Fast Mode\n          45-55° C / KWh: 3\n   water consumption: 3,5 Lt"};
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modes);
        eco = findViewById(R.id.eco);
        glass = findViewById(R.id.glass);
        pot = findViewById(R.id.pot);
        fast = findViewById(R.id.fast);

        eco.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Modes.this,popUp.class);
                intent.putExtra("quickInfo",info[0]);
                intent.putExtra("quickHourText",hourTexts[0]);
                intent.putExtra("washTiming",washTime[0]);
                startActivity(intent);
            }
        });
        glass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Modes.this,popUp.class);
                intent.putExtra("quickInfo",info[1]);
                intent.putExtra("quickHourText",hourTexts[1]);
                intent.putExtra("washTiming",washTime[1]);
                startActivity(intent);
            }
        });
        pot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Modes.this,popUp.class);
                intent.putExtra("quickInfo",info[2]);
                intent.putExtra("quickHourText",hourTexts[2]);
                intent.putExtra("washTiming",washTime[2]);
                startActivity(intent);
            }
        });
        fast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Modes.this,popUp.class);
                intent.putExtra("quickInfo",info[3]);
                intent.putExtra("quickHourText",hourTexts[3]);
                intent.putExtra("washTiming",washTime[3]);
                startActivity(intent);
            }
        });
    }
}
