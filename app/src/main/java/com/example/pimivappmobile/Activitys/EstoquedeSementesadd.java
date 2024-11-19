package com.example.pimivappmobile.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pimivappmobile.R;

import org.json.JSONException;
import org.json.JSONObject;

public class EstoquedeSementesadd extends AppCompatActivity {

    private EditText nomeSemente, tipoCultura, quantidade;
    private Spinner fornecedorSpinner;
    private Button buttonSalvar, btVoltarSementes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoquede_sementesadd);

        nomeSemente = findViewById(R.id.nomeSemente);
        tipoCultura = findViewById(R.id.tipoCultura);
        quantidade = findViewById(R.id.quantidade);
        fornecedorSpinner = findViewById(R.id.fornecedorSpinner);
        buttonSalvar = findViewById(R.id.buttonSalvar);
        btVoltarSementes = findViewById(R.id.bt_voltarSementes);

        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarSemente();
            }
        });

        btVoltarSementes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                finish();
            }
        });
    }

    private void salvarSemente() {

        String nome = nomeSemente.getText().toString();
        String tipo = tipoCultura.getText().toString();
        String qtd = quantidade.getText().toString();

        if (nome.isEmpty() || tipo.isEmpty() || qtd.isEmpty()) {
            Toast.makeText(EstoquedeSementesadd.this, "Todos os campos devem ser preenchidos!", Toast.LENGTH_SHORT).show();
            return;
        }


        JSONObject sementeJson = new JSONObject();
        try {
            sementeJson.put("nome", nome);
            sementeJson.put("tipo_cultura", tipo);
            sementeJson.put("quantidade", Integer.parseInt(qtd));


            String url = "http://192.168.15.46:8080/api/sementes";

            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST,
                    url,
                    sementeJson,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            try {

                                if (response != null) {
                                    Toast.makeText(EstoquedeSementesadd.this, "Semente adicionada com sucesso!", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                            } catch (Exception e) {
                                Toast.makeText(EstoquedeSementesadd.this, "Erro ao salvar a semente", Toast.LENGTH_SHORT).show();
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(EstoquedeSementesadd.this, "Erro de comunicação com o servidor", Toast.LENGTH_SHORT).show();
                        }
                    });


            Volley.newRequestQueue(this).add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(EstoquedeSementesadd.this, "Erro ao criar o JSON", Toast.LENGTH_SHORT).show();
        }
    }

    public void bt_voltarSementes(View view){
        Intent in = new Intent(EstoquedeSementesadd.this, EstoquedeSementes.class);
        startActivity(in);
    }

}