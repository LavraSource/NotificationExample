
package com.example.notificationexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {
    Button start_button,stop_button;
    EditText hours,minutes,seconds;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        start_button=findViewById(R.id.launchserv);
        stop_button=findViewById(R.id.stopserv);

        hours=findViewById(R.id.hour);
        minutes=findViewById(R.id.minutes);
        seconds=findViewById(R.id.seconds);


        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String hours_text=hours.getText().toString();
                String minutes_text=minutes.getText().toString();
                String seconds_text=seconds.getText().toString();
                Intent intent=new Intent(MainActivity.this,MyIntentService.class);
                intent.putExtra("hours",hours_text);
                intent.putExtra("minutes",minutes_text);
                intent.putExtra("seconds",seconds_text);
                startService(intent);
                Intent intent1=new Intent(MainActivity.this,MusicSservice.class);
                startService(intent1);




            }
        });
        stop_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                stopService(new Intent(MainActivity.this,MyIntentService.class));
                stopService(new Intent(MainActivity.this,MusicSservice.class));

            }
        });



    }

}