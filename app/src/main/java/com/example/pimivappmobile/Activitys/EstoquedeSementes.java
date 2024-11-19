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

public class EstoquedeSementes extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_estoquede_sementes);
    }

    public void bt_Estoquedesementesadd(View view){
        Intent in = new Intent(EstoquedeSementes.this, EstoquedeSementesadd.class);
        startActivity(in);
    }

    public void bt_Estoquedesementesativas(View view){
        Intent in = new Intent(EstoquedeSementes.this, EstoquedeSementesativas.class);
        startActivity(in);
    }

    public void bt_voltarHomeS(View view){
        Intent in = new Intent(EstoquedeSementes.this, Home.class);
        startActivity(in);
    }
}