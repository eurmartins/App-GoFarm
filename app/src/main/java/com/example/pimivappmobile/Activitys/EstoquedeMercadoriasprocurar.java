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

public class EstoquedeMercadoriasprocurar extends AppCompatActivity {

    private EditText searchField;
    private ImageView searchIcon;
    private TextView searchResults;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoquede_mercadoriasprocurar);

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
                    Toast.makeText(EstoquedeMercadoriasprocurar.this, "Por favor, insira um ID", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void searchForData(String searchId) {

        String url = "http://192.168.15.46:8080/api/mercadorias/" + searchId;


        searchResults.setVisibility(View.GONE);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {


                            String result = "Nome: " + response.getString("nome") + "\n";
                            result += "Tipo de Cultura: " + response.getString("tipo_cultura") + "\n";
                            result += "Quantidade por Caixa: " + response.getInt("quantidade_por_caixa") + "\n";
                            result += "Quantidade de Caixas: " + response.getInt("quantidade_caixas") + "\n";
                            result += "Data de Colheita: " + response.getString("data_colheita") + "\n";
                            result += "Preço: R$ " + response.getDouble("preco");


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

    public void bt_voltarMercadorias(View view) {
        Intent in = new Intent(EstoquedeMercadoriasprocurar.this, EstoquedeMercadorias.class);
        startActivity(in);
    }
}
