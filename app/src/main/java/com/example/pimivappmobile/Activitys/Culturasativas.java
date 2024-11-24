package com.example.pimivappmobile.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
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

public class Culturasativas extends AppCompatActivity {

    private EditText searchField;
    private ImageView searchIcon;
    private TextView searchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culturasativas);

        searchField = findViewById(R.id.searchField);
        searchIcon = findViewById(R.id.searchIcon);
        searchResults = findViewById(R.id.searchResults);

        searchIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String searchId = searchField.getText().toString();

                if (!searchId.isEmpty()) {
                    searchForData(searchId);
                } else {
                    Toast.makeText(Culturasativas.this, "Por favor, insira um ID", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void searchForData(String searchId) {
        String url = "http://192.168.15.46:8080/api/culturas/" + searchId;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String result = String.format(
                                    "Nome: %s\n" +
                                            "Tipo de Cultura: %s\n" +
                                            "Requisitos de Solo: %s\n" +
                                            "Requisitos de Água: %s\n" +
                                            "Intervalo de Temperatura: %s\n" +
                                            "Requisitos de Umidade: %s\n" +
                                            "Pragas e Doenças: %s\n" +
                                            "Tempo de Colheita: %d dias\n",
                                    response.optString("nome", "Não disponível"),
                                    response.optString("tipo_cultura", "Não disponível"),
                                    response.optString("requisitos_solo", "Não disponível"),
                                    response.optString("requisitos_agua", "Não disponível"),
                                    response.optString("intervalo_temperatura", "Não disponível"),
                                    response.optString("requisitos_umidade", "Não disponível"),
                                    response.optString("pragas_doencas", "Não disponível"),
                                    response.optInt("tempo_colheita", 0)
                            );

                            searchResults.setText(result);
                            searchResults.setVisibility(View.VISIBLE);
                        } catch (Exception e) {
                            searchResults.setText("Erro ao processar os dados.");
                            searchResults.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error.networkResponse != null && error.networkResponse.statusCode == 404) {
                            searchResults.setText("Cultura não encontrada para o ID informado.");
                        } else {
                            searchResults.setText("Erro ao buscar dados. Verifique a conexão.");
                        }
                        searchResults.setVisibility(View.VISIBLE);
                    }
                }
        );

        Volley.newRequestQueue(this).add(jsonObjectRequest);
    }

    public void bt_voltarCulturas(View view){
        Intent in = new Intent(Culturasativas.this, Culturas.class);
        startActivity(in);
    }
}
