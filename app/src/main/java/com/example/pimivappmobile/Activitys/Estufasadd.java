package com.example.pimivappmobile.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pimivappmobile.R;

import org.json.JSONException;
import org.json.JSONObject;

public class Estufasadd extends AppCompatActivity {

    private EditText nomeEstufa, localizacaoEstufa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estufasadd);

        nomeEstufa = findViewById(R.id.nomeEstufa);
        localizacaoEstufa = findViewById(R.id.localizacaoEstufa);

        findViewById(R.id.buttonSalvarEstufa).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                salvarEstufa();
            }
        });
    }

    private void salvarEstufa() {
        String nome = nomeEstufa.getText().toString().trim();
        String localizacao = localizacaoEstufa.getText().toString().trim();

        if (nome.isEmpty() || localizacao.isEmpty()) {
            Toast.makeText(Estufasadd.this, "Preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }


        JSONObject estufaData = new JSONObject();
        try {
            estufaData.put("nome", nome);
            estufaData.put("localizacao", localizacao);
        } catch (JSONException e) {
            e.printStackTrace();
        }


        String url = "http://192.168.15.46:8080/api/estufas";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, url, estufaData,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(Estufasadd.this, "Estufa salva com sucesso!", Toast.LENGTH_SHORT).show();
                        nomeEstufa.setText("");
                        localizacaoEstufa.setText("");
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(Estufasadd.this, "Erro ao salvar estufa: " + error.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
        );
        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

    public void bt_voltarEstufas(View view){
        Intent in = new Intent(Estufasadd.this, Estufas.class);
        startActivity(in);
    }
}
