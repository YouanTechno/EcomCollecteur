package com.example.collecteur_app;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.collecteur_app.models.Agent;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Connexion extends AppCompatActivity {

    Button connecter;
    EditText reclogin, recpasword;
    String reclogins ;
    String recpasswords;
    String recloginf, recpasswordf;

    int cont_id;
    private Context context;
    private JsonPlaceHolderApi jsonPlaceHolderApi, getidconts;
//    DatabaseHelper myDb;
//    private SQLiteDatabase sql;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        connecter = findViewById(R.id.connecter);
        reclogin = findViewById(R.id.reclogin);
        recpasword = findViewById(R.id.recpasword);

        connecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reclogins = recloginf;
                recpasswords = recpasswordf;
                if(TextUtils.isEmpty(reclogins)|| TextUtils.isEmpty(recpasswords)){
                    Toast.makeText(Connexion.this, "Veillez entrer un nom et un mot de passe", Toast.LENGTH_LONG).show();
                }else{
                    getInfoconnexion();
                }
//                Intent menu = new Intent(getApplicationContext(), MainActivity.class);
//                startActivity(menu);
            }
        });

        reclogin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                recloginf = reclogin.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        recpasword.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                recpasswordf = recpasword.getText().toString();
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

    }

    private void getInfoconnexion(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.169/api/")
//                .baseUrl("http://192.168.137.1/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Agent infoAgent = new Agent(""+reclogins+"", ""+recpasswords+"");
        Call<Agent> call = jsonPlaceHolderApi.getidentifiant(infoAgent);
        call.enqueue(new Callback<Agent>() {
            @Override
            public void onResponse(Call<Agent> call, Response<Agent> response) {
                if(!response.isSuccessful()){
                    Agent agentRecup = response.body();
                    Toast.makeText(Connexion.this, "Login ou mot de passe incorrecte", Toast.LENGTH_LONG).show();
                    return;
                }else{
                        Intent menu = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(menu);
                        finish();
                }
            }
            @Override
            public void onFailure(Call<Agent> call, Throwable t) {
                Toast.makeText(Connexion.this, "Erreur de connexion au serveur", Toast.LENGTH_LONG).show();
            }
        });
    }
}