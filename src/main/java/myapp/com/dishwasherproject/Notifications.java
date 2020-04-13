package myapp.com.dishwasherproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Notifications extends Activity {
    Button save,btnOnOff,btnVibr;
    TextView onoff,vibr;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);
        save = findViewById(R.id.buttonSave);
        onoff = findViewById(R.id.txtONOFF);
        vibr = findViewById(R.id.txtVibr);
        btnOnOff = findViewById(R.id.not);
        btnVibr = findViewById(R.id.vibr);
        onoff.setTextColor(Color.BLACK);

        vibr.setTextColor(Color.BLACK);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                Toast.makeText(getApplicationContext(),"saved changes", Toast.LENGTH_LONG).show();
                finish();
            }
        });
        btnOnOff.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v)
            {
                if(btnOnOff.getText().toString().equals("On"))
                    btnOnOff.setText("Off");
                else
                    btnOnOff.setText("On");
            }
        });
        btnVibr.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View v)
            {
                if(btnVibr.getText().toString().equals("On"))
                    btnVibr.setText("Off");
                else
                    btnVibr.setText("On");
            }
        });
    }
    @Override
    public void onBackPressed()
    {
        super.onBackPressed();
        finish();
    }
}
