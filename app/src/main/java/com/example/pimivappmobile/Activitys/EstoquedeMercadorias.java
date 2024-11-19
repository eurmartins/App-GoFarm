package com.example.pimivappmobile.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.pimivappmobile.R;

public class EstoquedeMercadorias extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_estoquede_mercadorias);
    }

    public void bt_AddMercadoria(View view){
        Intent in = new Intent(EstoquedeMercadorias.this, EstoquedeMercadoriasadd.class);
        startActivity(in);
    }

    public void bt_ProcurarMercadoria(View view){
        Intent in = new Intent(EstoquedeMercadorias.this, EstoquedeMercadoriasprocurar.class);
        startActivity(in);
    }

    public void bt_voltarHomeM(View view){
        Intent in = new Intent(EstoquedeMercadorias.this, Home.class);
        startActivity(in);
    }

}