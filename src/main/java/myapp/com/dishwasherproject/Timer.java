package myapp.com.dishwasherproject;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

public class Timer extends Activity
{
    TimePicker timePicker;
    Spinner dropDown;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        timePicker = findViewById(R.id.timepicker);
       // timePicker.setBackgroundColor(Color.TRANSPARENT);
        dropDown = findViewById(R.id.spinner);
        ArrayAdapter<String> list = new ArrayAdapter<String>(Timer.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.activity_arrays));
        list.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropDown.setAdapter(list);
        //dropDown.setBackgroundColor(Color.LTGRAY);

        Button setTimer = findViewById(R.id.setTimer);

        setTimer.setOnClickListener(new View.OnClickListener()
        {
            @SuppressLint("ShowToast")
            @Override
            public void onClick(View v)
            {
                MainScreen.hour = timePicker.getHour();
                MainScreen.minutes = timePicker.getMinute();
                 Toast.makeText(getApplicationContext(),"saved changes", Toast.LENGTH_LONG).show();
                finish();
            }
        });

    }
}
