package com.example.pimivappmobile.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pimivappmobile.R;

import org.json.JSONObject;

public class Estufaspprocurar extends AppCompatActivity {

    private EditText searchField;
    private TextView searchResults;
    private Button bt_voltarEstufas;
    private ImageView searchIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estufaspprocurar);

        searchField = findViewById(R.id.searchField);
        searchResults = findViewById(R.id.searchResults);
        bt_voltarEstufas = findViewById(R.id.bt_voltarEstufas);
        searchIcon = findViewById(R.id.searchIcon);


        searchIcon.setOnClickListener(v -> {
            String id = searchField.getText().toString().trim();
            if (!id.isEmpty()) {
                pesquisarEstufaPorId(Integer.parseInt(id));
            } else {
                Toast.makeText(Estufaspprocurar.this, "Por favor, insira um ID", Toast.LENGTH_SHORT).show();
            }
        });


        bt_voltarEstufas.setOnClickListener(v -> finish());
    }

    private void pesquisarEstufaPorId(int id) {
        String url = "http://192.168.15.46:8080/api/estufas/" + id;


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {

                            String nome = response.getString("nome");
                            String localizacao = response.getString("localizacao");

                            String resultado = "Nome: " + nome + "\nLocalização: " + localizacao;
                            searchResults.setText(resultado);
                            searchResults.setVisibility(View.VISIBLE);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(Estufaspprocurar.this, "Erro ao processar os dados", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(com.android.volley.VolleyError error) {
                        searchResults.setVisibility(View.GONE);
                        Toast.makeText(Estufaspprocurar.this, "Estufa não encontrada", Toast.LENGTH_SHORT).show();
                    }
                });


        Volley.newRequestQueue(this).add(request);
    }

    public void bt_voltarEstufas(View view){
        Intent in = new Intent(Estufaspprocurar.this, Estufas.class);
        startActivity(in);
    }

}