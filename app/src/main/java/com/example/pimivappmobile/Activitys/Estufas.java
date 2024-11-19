package com.example.pimivappmobile.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pimivappmobile.R;

public class Estufas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_estufas);
    }

    public void bt_AddEstufa(View view){
        Intent in = new Intent(Estufas.this, Estufasadd.class);
        startActivity(in);
    }

    public void bt_EstufasAtivas(View view){
        Intent in = new Intent(Estufas.this, Estufaspprocurar.class);
        startActivity(in);
    }

    public void bt_voltarHomeE(View view) {
        Intent in = new Intent(Estufas.this, Home.class);
        startActivity(in);
    }
}