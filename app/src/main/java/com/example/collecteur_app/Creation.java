package com.example.collecteur_app;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.collecteur_app.models.Activites;
import com.example.collecteur_app.models.Contribuables;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Creation extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private Spinner nationaliteListe;
    List<String> listactiv = new ArrayList<>();
    List<Integer> listActiId = new ArrayList<Integer>();
    String nationalite, noms, prenoms, contacts, numcnis, logins, passwords, paswordconfirms;
    Button enregistre;
    EditText nom, prenom, contact, numcni, login, password, paswordconfirm;
    private JsonPlaceHolderApi jsonPlaceHolderApi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);
        enregistre = findViewById(R.id.enregistre);
        nom = findViewById(R.id.nom);
        prenom = findViewById(R.id.prenom);
        contact = findViewById(R.id.contact);
        numcni = findViewById(R.id.cni);
        login = findViewById(R.id.login);
        password = findViewById(R.id.pasword);
        paswordconfirm = findViewById(R.id.paswordconfirm);
        //getActivites();
        nationaliteListe = findViewById(R.id.nationaliteListe);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,R.array.nation, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        nationaliteListe.setAdapter(adapter);
        nationaliteListe.setOnItemSelectedListener(this);

        enregistre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noms = nom.getText().toString();
                prenoms = prenom.getText().toString();
                contacts = contact.getText().toString();
                numcnis = numcni.getText().toString();
                logins = login.getText().toString();
                passwords = password.getText().toString();
                paswordconfirms = paswordconfirm.getText().toString();
                if(TextUtils.isEmpty(noms)||TextUtils.isEmpty(prenoms)|| TextUtils.isEmpty(contacts)||TextUtils.isEmpty(numcnis)||
                        TextUtils.isEmpty(logins)||TextUtils.isEmpty(passwords)||TextUtils.isEmpty(paswordconfirms)){
                    errorMessage();
//                    Toast.makeText(Creation.this, "Veuillez renseigner toutes les informations", Toast.LENGTH_LONG).show();
                }else if(!paswordconfirms.equals(passwords)){
                    errorMessagePas();
//                    Toast.makeText(Creation.this, "Les mot de passe sont différents", Toast.LENGTH_LONG).show();

                }else{
                    enVoieData();
                    //savecontrib();
                    //Toast.makeText(Creation.this, "Enregistré", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        nationalite = parent.getItemAtPosition(position).toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    private void savecontrib() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.1.169/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Contribuables contrib = new Contribuables(""+noms+"",""+prenoms+"",""+numcnis+"",""+contacts+"",""+nationalite+"",new int[]{246995,246997,246998},""+logins+"",""+passwords+"",0256,2589);
        Call<Contribuables> call = jsonPlaceHolderApi.sendcontribuable(contrib);
        call.enqueue(new Callback<Contribuables>() {
            @Override
            public void onResponse(Call<Contribuables> call, Response<Contribuables> response) {
                if(!response.isSuccessful()){
                    Contribuables contribu = response.body();
                    Toast.makeText(getApplicationContext(), "Echec enregistrement", Toast.LENGTH_LONG).show();
                    return;
                }
                else{
                    Toast.makeText(getApplicationContext(), "succès enregistré", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Contribuables> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "serveur ne repond pas", Toast.LENGTH_LONG).show();
            }
        });
    }

//    private void getActivites(){
//        Retrofit retrofit = new Retrofit.Builder()
////                .baseUrl("http://192.168.1.169/api/")
//                .baseUrl("http://192.168.137.1/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
//        Call<List<Activites>> call = jsonPlaceHolderApi.obtactivite();
//        call.enqueue(new Callback<List<Activites>>() {
//            @Override
//            public void onResponse(Call<List<Activites>> call, Response<List<Activites>> response) {
//                if(!response.isSuccessful()){
//                    Toast.makeText(getApplicationContext(), ""+response.errorBody()+"", Toast.LENGTH_LONG).show();
//                    return;
//                }else{
//                    List<Activites> activitesList = (List<Activites>) response.body();
//                    for (Activites e : activitesList){
//                        listactiv.add(e.getActivLib());
//                        listActiId.add(e.getActivid());
//                    }
//                    Toast.makeText(getApplicationContext(), "succès", Toast.LENGTH_LONG).show();
//                }
//            }
//            @Override
//            public void onFailure(Call<List<Activites>> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), ""+t.getCause()+"", Toast.LENGTH_LONG).show();
//            }
//        });
//    }

    private void enVoieData(){
        Intent activites = new Intent(getApplicationContext(), ActiviteSave.class);
        //Bundle bundle = new Bundle();
        //Bundle bundle1 = new Bundle();
        //bundle.putStringArrayList("ListeAct", (ArrayList<String>) listactiv);
        //activites.putExtras(bundle);
        activites.putExtra(ActiviteSave.nom,noms);
        activites.putExtra(ActiviteSave.prenom,prenoms);
        activites.putExtra(ActiviteSave.contact,contacts);
        activites.putExtra(ActiviteSave.numcni,numcnis);
        activites.putExtra(ActiviteSave.login,logins);
        activites.putExtra(ActiviteSave.password,passwords);
        activites.putExtra(ActiviteSave.nationalite, nationalite);
        //activites.putIntegerArrayListExtra("ListeActId", (ArrayList<Integer>)listActiId);
        startActivity(activites);
        finish();
    }
    public void errorMessage() {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("Veuillez renseigner toutes les informations!").show();
    }
    public void errorMessagePas() {
        new SweetAlertDialog(this, SweetAlertDialog.ERROR_TYPE).setTitleText("Oops...").setContentText("Les mots de passe sont différents!").show();
    }
}