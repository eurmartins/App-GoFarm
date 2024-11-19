package com.example.pimivappmobile.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.pimivappmobile.R;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_home);
        }

    public void bt_culturas(View view){
        Intent in = new Intent(Home.this, Culturas.class);
        startActivity(in);
    }

    public void bt_Monitoramento(View view){
        Intent in = new Intent(Home.this, Estufas.class);
        startActivity(in);
    }


    public void bt_EstoquedeMercadorias(View view){
        Intent in = new Intent(Home.this, EstoquedeMercadorias.class);
        startActivity(in);
    }

    public void bt_Estoquedesementes(View view){
        Intent in = new Intent(Home.this, EstoquedeSementes.class);
        startActivity(in);
    }

}
