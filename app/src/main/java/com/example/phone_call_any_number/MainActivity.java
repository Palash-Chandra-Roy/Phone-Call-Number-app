package com.example.phone_call_any_number;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = (EditText)findViewById(R.id.edit_TextId);
        button = (Button) findViewById(R.id.buttonId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCall = new Intent(Intent.ACTION_CALL);
                String number = editText.getText().toString();
                if(number.trim().isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please Enter your phone Number", Toast.LENGTH_SHORT).show();
                }
                else {
                    intentCall.setData(Uri.parse("tel"+number));
                }
                if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MainActivity.this, "Please Grant Permission ", Toast.LENGTH_SHORT).show();
                    requestPermission();
                }
                else {
                    startActivity(intentCall);
                }

            }

        });
    }
    private void requestPermission(){
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);

    }
}
