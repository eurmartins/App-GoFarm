package com.example.pimivappmobile.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pimivappmobile.R;

public class Culturas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_culturas);
    }

    public void bt_culturasadd(View view){
        Intent in = new Intent(Culturas.this, Culturasadd.class);
        startActivity(in);
    }

    public void bt_culturasAtivas(View view){
        Intent in = new Intent(Culturas.this, Culturasativas.class);
        startActivity(in);
    }

    public void bt_voltarHomeC(View view){
        Intent in = new Intent(Culturas.this, Home.class);
        startActivity(in);
    }
}