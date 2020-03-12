package com.example.authentication;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class ContractActivity extends AppCompatActivity {
    private ImageButton CallButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contract);
        CallButton = (ImageButton) findViewById(R.id.CallButton_id);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void onClick(View view) {
        if (view.getId()== R.id.CallButton_id){
            String phone = "01521491135";
            Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
            startActivity(intent);
        }
    }
}
