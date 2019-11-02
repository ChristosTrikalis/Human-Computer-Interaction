package myapp.com.dishwasherproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

public class More extends Activity
{
    ImageButton Drying, Notifications, Prewash;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);

        Drying = findViewById(R.id.dry);
        Prewash = findViewById(R.id.prewash);
        Notifications = findViewById(R.id.notifier);

        Prewash.setOnClickListener(new View.OnClickListener()
        {@Override public void onClick(View v)
        {
            Intent in = new Intent(More.this,progressPopUp.class);
            in.putExtra("mess","Your Dishes Are prewashed!");
            in.putExtra("txt","Please wait while your dishes are being prewashed!");
            in.putExtra("flag","prewash");
            startActivity(in);
        }
        });

        Drying.setOnClickListener(new View.OnClickListener()
        {
            @Override public void onClick(View v)
            {
                Intent in = new Intent(More.this,progressPopUp.class);
                in.putExtra("mess","Your Dishes Are Dry!");
                in.putExtra("txt","Please wait while your dishes are being dried!");
                in.putExtra("flag","dry");
                startActivity(in);
            }
        });

        Notifications.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(More.this, Notifications.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.finish();
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        this.finish();
    }


}


