package com.example.final1;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Button;
import android.os.CountDownTimer;
import java.util.Locale;


public class MainActivity extends AppCompatActivity {
    private static final int callnumber = 0;
    private EditText editnumber;
    private static final long starttimer = 600000;
    private TextView thetimeremain;
    private Button buttonstart;
    private Button buttonreset;
    private Button buttonstop;
    private CountDownTimer timer;
    private boolean running;
    private long timeleft = starttimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        thetimeremain = findViewById(R.id.timeRemain);
        setContentView(R.layout.activity_main);
        editnumber = findViewById(R.id.edit_text_number);
        ImageView phone = findViewById(R.id.ic_newicon1);
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callit();
            }
        });
    }

    private void callit() {
        String number = editnumber.getText().toString();
        if (number.trim().length() <= 0) {
            Toast.makeText(MainActivity.this,"Enter Correct Number", Toast.LENGTH_SHORT).show();
        } else {
            if (ContextCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[] {Manifest.permission.CALL_PHONE}, callnumber);
            } else {
                String d = "tel:" + number;
                startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(d)));
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == callnumber) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                callit();
            } else {
                Toast.makeText(this, "Permission denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
