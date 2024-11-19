package com.example.pimivappmobile.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
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

        String url = "http://192.168.15.46:8080/api/culturas/CulturaPorId/" + searchId;


        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            String result = "Nome: " + response.getString("nome") + "\n";
                            result += "Tipo de Cultura: " + response.getString("tipo_cultura") + "\n";
                            result += "Requisitos de Solo: " + response.getString("requisitos_solo") + "\n";
                            result += "Requisitos de Água: " + response.getString("requisitos_agua") + "\n";
                            result += "Intervalo de Temperatura: " + response.getString("intervalo_temperatura") + "\n";
                            result += "Requisitos de Umidade: " + response.getString("requisitos_umidade") + "\n";
                            result += "Pragas e Doenças: " + response.getString("pragas_doencas") + "\n";
                            result += "Tempo de Colheita: " + response.getInt("tempo_colheita") + " dias";


                            searchResults.setText(result);
                            searchResults.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            searchResults.setText("Erro ao processar os dados.");
                            searchResults.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        searchResults.setText("Erro ao buscar dados: " + error.getMessage());
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