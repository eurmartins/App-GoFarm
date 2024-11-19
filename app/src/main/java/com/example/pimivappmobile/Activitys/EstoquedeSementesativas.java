package com.example.pimivappmobile.Activitys;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pimivappmobile.R;

import org.json.JSONException;
import org.json.JSONObject;

public class EstoquedeSementesativas extends AppCompatActivity {

    private EditText searchField;
    private TextView searchResults;
    private Button btVoltarEstufas;
    private ImageView searchIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoquede_sementesativas);

        searchField = findViewById(R.id.searchField);
        searchResults = findViewById(R.id.searchResults);
        btVoltarEstufas = findViewById(R.id.bt_voltarEstufas);
        searchIcon = findViewById(R.id.searchIcon);


        searchIcon.setOnClickListener(v -> {
            String id = searchField.getText().toString().trim();
            if (!id.isEmpty()) {
                pesquisarSementePorId(Integer.parseInt(id));
            } else {
                Toast.makeText(EstoquedeSementesativas.this, "Por favor, insira um ID", Toast.LENGTH_SHORT).show();
            }
        });


        btVoltarEstufas.setOnClickListener(v -> finish());
    }

    private void pesquisarSementePorId(int id) {

        String url = "http://192.168.15.46:8080/api/sementes/" + id;


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            String nome = response.getString("nome");
                            String tipoCultura = response.getString("tipo_cultura");
                            int quantidade = response.getInt("quantidade");
                            String unidadeMedida = response.getString("unidade_medida");
                            String fornecedor = response.getString("fornecedor");


                            String resultado = "Nome: " + nome + "\nTipo de Cultura: " + tipoCultura
                                    + "\nQuantidade: " + quantidade + "\nUnidade de Medida: " + unidadeMedida
                                    + "\nFornecedor: " + fornecedor;


                            searchResults.setText(resultado);
                            searchResults.setVisibility(View.VISIBLE);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(EstoquedeSementesativas.this, "Erro ao processar os dados", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(com.android.volley.VolleyError error) {
                        searchResults.setVisibility(View.GONE);
                        Toast.makeText(EstoquedeSementesativas.this, "Semente não encontrada", Toast.LENGTH_SHORT).show();
                    }
                });


        Volley.newRequestQueue(this).add(request);
    }
}
