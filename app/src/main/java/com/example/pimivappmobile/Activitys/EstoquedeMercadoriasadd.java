package com.example.pimivappmobile.Activitys;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.pimivappmobile.R;

import org.json.JSONException;
import org.json.JSONObject;

public class EstoquedeMercadoriasadd extends AppCompatActivity {

    private EditText nomeMercadoria, tipoCultura, quantidadePorCaixa, quantidadeCaixas, dataColheita, preco;
    private Button buttonSalvar;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estoquede_mercadoriasaad);


        nomeMercadoria = findViewById(R.id.nomeMercadoria);
        tipoCultura = findViewById(R.id.tipoCultura);
        quantidadePorCaixa = findViewById(R.id.quantidadePorCaixa);
        quantidadeCaixas = findViewById(R.id.quantidadeCaixas);
        dataColheita = findViewById(R.id.dataColheita);
        preco = findViewById(R.id.preco);
        buttonSalvar = findViewById(R.id.buttonSalvar);


        requestQueue = Volley.newRequestQueue(this);


        buttonSalvar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adicionarMercadoria();
            }
        });


    }


    private void adicionarMercadoria() {

        String nome = nomeMercadoria.getText().toString().trim();
        String tipo = tipoCultura.getText().toString().trim();
        String quantidadeCaixa = quantidadePorCaixa.getText().toString().trim();
        String quantidade = quantidadeCaixas.getText().toString().trim();
        String data = dataColheita.getText().toString().trim();
        String precoStr = preco.getText().toString().trim();

        if (nome.isEmpty() || tipo.isEmpty() || quantidadeCaixa.isEmpty() || quantidade.isEmpty() || data.isEmpty() || precoStr.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }

        try {

            JSONObject mercadoriaJson = new JSONObject();
            mercadoriaJson.put("nome", nome);
            mercadoriaJson.put("tipo_cultura", tipo);
            mercadoriaJson.put("quantidade_por_caixa", Integer.parseInt(quantidadeCaixa));
            mercadoriaJson.put("quantidade_caixas", Integer.parseInt(quantidade));
            mercadoriaJson.put("data_colheita", data);
            mercadoriaJson.put("preco", Double.parseDouble(precoStr));


            String url = "http://192.168.15.46:8080/api/mercadorias";


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST, url, mercadoriaJson,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {
                            Toast.makeText(EstoquedeMercadoriasadd.this, "Mercadoria adicionada com sucesso!", Toast.LENGTH_SHORT).show();
                            finish();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    Toast.makeText(EstoquedeMercadoriasadd.this, "Erro ao adicionar mercadoria.", Toast.LENGTH_SHORT).show();
                    error.printStackTrace();
                }
            });


            requestQueue.add(jsonObjectRequest);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Erro ao formatar dados.", Toast.LENGTH_SHORT).show();
        }
    }

    public void bt_voltarMercadorias(View view){
        Intent in = new Intent(EstoquedeMercadoriasadd.this, EstoquedeMercadorias.class);
        startActivity(in);
    }
}
