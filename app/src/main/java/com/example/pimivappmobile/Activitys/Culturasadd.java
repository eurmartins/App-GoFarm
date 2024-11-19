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

import org.json.JSONObject;

public class Culturasadd extends AppCompatActivity {

    private EditText nomeEditText, tipoCulturaEditText, cicloVidaEditText, requisitosSoloEditText, requisitosAguaEditText,
            intervaloTemperaturaEditText, requisitosUmidadeEditText, pragasDoencasEditText, tempoColheitaEditText, idEstufaEditText;
    private Button btCadastrarCultura, bt_voltarHome;
    private RequestQueue requestQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_culturasadd);


        nomeEditText = findViewById(R.id.nomeCultura);
        tipoCulturaEditText = findViewById(R.id.tipoCultura);
        cicloVidaEditText = findViewById(R.id.cicloVida);
        requisitosSoloEditText = findViewById(R.id.requisitosSolo);
        requisitosAguaEditText = findViewById(R.id.requisitosAgua);
        intervaloTemperaturaEditText = findViewById(R.id.intervaloTemperatura);
        requisitosUmidadeEditText = findViewById(R.id.requisitosUmidade);
        pragasDoencasEditText = findViewById(R.id.pragasDoencas);
        tempoColheitaEditText = findViewById(R.id.tempoColheita);
        idEstufaEditText = findViewById(R.id.idEstufa);
        btCadastrarCultura = findViewById(R.id.buttonSalvar);
        bt_voltarHome = findViewById(R.id.bt_voltarHome);


        requestQueue = Volley.newRequestQueue(this);


        btCadastrarCultura.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cadastrarCultura();
            }
        });


        bt_voltarHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bt_voltarHome();
            }
        });
    }

    private void cadastrarCultura() {
        String nome = nomeEditText.getText().toString().trim();
        String tipoCultura = tipoCulturaEditText.getText().toString().trim();
        String cicloVida = cicloVidaEditText.getText().toString().trim();
        String requisitosSolo = requisitosSoloEditText.getText().toString().trim();
        String requisitosAgua = requisitosAguaEditText.getText().toString().trim();
        String intervaloTemperatura = intervaloTemperaturaEditText.getText().toString().trim();
        String requisitosUmidade = requisitosUmidadeEditText.getText().toString().trim();
        String pragasDoencas = pragasDoencasEditText.getText().toString().trim();
        String tempoColheita = tempoColheitaEditText.getText().toString().trim();
        String idEstufa = idEstufaEditText.getText().toString().trim();

        if (nome.isEmpty() || tipoCultura.isEmpty() || cicloVida.isEmpty() || requisitosSolo.isEmpty() ||
                requisitosAgua.isEmpty() || intervaloTemperatura.isEmpty() || requisitosUmidade.isEmpty() ||
                pragasDoencas.isEmpty() || tempoColheita.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha todos os campos", Toast.LENGTH_SHORT).show();
            return;
        }


        try {
            JSONObject culturaJson = new JSONObject();
            culturaJson.put("nome", nome);
            culturaJson.put("tipo_cultura", tipoCultura);
            culturaJson.put("ciclo_vida", cicloVida);
            culturaJson.put("requisitos_solo", requisitosSolo);
            culturaJson.put("requisitos_agua", requisitosAgua);
            culturaJson.put("intervalo_temperatura", intervaloTemperatura);
            culturaJson.put("requisitos_umidade", requisitosUmidade);
            culturaJson.put("pragas_doencas", pragasDoencas);
            culturaJson.put("tempo_colheita", Integer.parseInt(tempoColheita));
            culturaJson.put("estufa_id", idEstufa);

            String url = "http://192.168.15.46:8080/api/culturas";


            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.POST, url, culturaJson, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Toast.makeText(Culturasadd.this, "Cultura cadastrada com sucesso!", Toast.LENGTH_SHORT).show();

                    finish();
                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Toast.makeText(Culturasadd.this, "Erro ao cadastrar cultura.", Toast.LENGTH_SHORT).show();
                }
            });

            requestQueue.add(jsonObjectRequest);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void bt_voltarHome() {
        Intent in = new Intent(Culturasadd.this, Culturas.class);
        startActivity(in);
    }
}
