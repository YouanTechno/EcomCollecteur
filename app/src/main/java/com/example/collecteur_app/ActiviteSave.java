package com.example.collecteur_app;

import static com.google.zxing.pdf417.PDF417Common.toIntArray;
import static okhttp3.ResponseBody.*;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationRequest;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.collecteur_app.models.Activites;
import com.example.collecteur_app.models.Contribuables;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnCanceledListener;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class ActiviteSave extends AppCompatActivity {

    AutoCompleteTextView autotextactivite;
//    TextView nom_activite;
//    "CI","MAli","EGYPTE","TOGO","GUINNE","NIGERIA","SENEGAL","FRANCE","CONGO","ALLEMAGNE","AMERIQUE"
    String[] listepays = {"CI","MAli","EGYPTE","TOGO","GUINNE","NIGERIA","SENEGAL","FRANCE","CONGO","ALLEMAGNE","AMERIQUE"};

    public static final List<String> listactiv = new ArrayList<>();
    public static final List<Integer> listActiId = new ArrayList<>();
    Button enregistre,ajouter;
    String choixActive;
    List<Integer> listIdActiv = new ArrayList<Integer>();
    JsonPlaceHolderApi jsonPlaceHolderApi;
    ListView listeactivite;
    ArrayAdapter<String> adapterajouter;
    ArrayList<String> listItems;
    TextView cordx,cordy, commune, quartier;

    //configuration des coordonnees x et y
    public static final int DEFAULT_UPDATE_INTERVAL = 30;
    public static final int FAST_UPDATE_INTERVAL = 5;
    private static final int PERMISSION_FINE_LOCATION = 99;
    boolean updateOn = false;
    LocationRequest locationRequest;
    LocationCallback locationCallback;
    FusedLocationProviderClient fusedLocationProviderClient;


    public static final String nationalite = "nationalite";
    public static final String nom = "noms";
    public static final String prenom ="prenoms";
    public static final String contact = "contacts";
    public static final String numcni = "numcnis";
    public static final String login = "logins";
    public static final String password = "passwords";

    String noms,prenoms,contacts,numcnis,nationalites,logins, passwords;
    List<String> Newlistactiv = new ArrayList<>();
    int NewlistActiId ;
    String choixtri;
    float coordonnex, coordonney;

    @RequiresApi(api = Build.VERSION_CODES.Q)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activite_save);
        getActivites();
        //getCurrentLocation();
        Intent receves = getIntent();
       // Bundle b = getIntent().getExtras();

        //Newlistactiv = b.getStringArrayList("ListeAct");
        //NewlistActiId = receves.getIntegerArrayListExtra("ListeActId");

        autotextactivite = (AutoCompleteTextView) findViewById(R.id.autotextactivite);
//        nom_activite = (TextView) findViewById(R.id.nom_activite);
        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, listactiv);
        autotextactivite.setAdapter(adapter);

        autotextactivite.setThreshold(1);
        autotextactivite.setAdapter(adapter);
        enregistre = (Button) findViewById(R.id.enregistre);
        ajouter = (Button) findViewById(R.id.ajouter);
        listeactivite = (ListView) findViewById(R.id.listeactivite);
        cordx = (TextView)findViewById(R.id.cordx);
        cordy = (TextView) findViewById(R.id.cordy);
        commune = (TextView) findViewById(R.id.commune);
        quartier = (TextView) findViewById(R.id.quartier);
        //initialisation de fuseLocationProviderClient
        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);
        listItems = new ArrayList<String>();
        adapterajouter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,listItems);
        listeactivite.setAdapter(adapterajouter);

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentLocation();
                if(autotextactivite.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Veiller entrer une activité svp", Toast.LENGTH_LONG).show();
                }else{
                    int compt = 0;
                    for(int i=0; i < listactiv.size(); i++){
                        choixActive = autotextactivite.getText().toString();
                        choixtri = listactiv.get(i);

                        if(choixtri.equals(choixActive)){
                            compt++;
                            if(compt==1){
                                NewlistActiId = listActiId.get(i);
                              //  Toast.makeText(getApplicationContext(),""+listActiId.get(i), Toast.LENGTH_LONG).show();
                                listIdActiv.add(NewlistActiId);
                                listItems.add(choixtri);
                                adapterajouter.notifyDataSetChanged();
                            }
                            autotextactivite.setText("");
                           //Toast.makeText(getApplicationContext(),""+listIdActiv, Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }
        });
        enregistre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent receve = getIntent();
                noms = receve.getStringExtra(nom);
                prenoms = receve.getStringExtra(prenom);
                contacts = receve.getStringExtra(contact);
                numcnis = receve.getStringExtra(numcni);
                nationalites = receve.getStringExtra(nationalite);
                logins = receve.getStringExtra(login);
                passwords = receve.getStringExtra(password);
                Toast.makeText(getApplicationContext(),""+noms+" ;"+prenoms+" ;"+contacts+" ;"+numcnis+" ;"+nationalites+" ;"+logins+" ;"+passwords, Toast.LENGTH_LONG).show();
                savecontrib();
            }
        });
    }

    private void getActivites(){
        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.137.1/api/")
                .baseUrl("http://192.168.1.169/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Call<List<Activites>> call = jsonPlaceHolderApi.obtactivite();
        call.enqueue(new Callback<List<Activites>>() {
            @Override
            public void onResponse(Call<List<Activites>> call, Response<List<Activites>> response) {
                if(!response.isSuccessful()){
                    Toast.makeText(getApplicationContext(), ""+response.errorBody()+"", Toast.LENGTH_LONG).show();
                    return;
                }else{
                    List<Activites> activitesList = (List<Activites>) response.body();
                    for (Activites e : activitesList){
                        listactiv.add(e.getActivLib());
                        listActiId.add(e.getActivid());
                    }
                    Toast.makeText(getApplicationContext(), "succès", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<List<Activites>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), ""+t.getCause()+"", Toast.LENGTH_LONG).show();
            }
        });
    }
//    public void getIdactivite(){
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.137.1/api/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
//        Call<Activites> call = jsonPlaceHolderApi.obtIdACtivite(""+choixtri);
//        call.enqueue(new Callback<Activites>() {
//            @Override
//            public void onResponse(Call<Activites> call, Response<Activites> response) {
//                if(!response.isSuccessful()){
//                    Toast.makeText(getApplicationContext(), ""+response.errorBody()+"", Toast.LENGTH_LONG).show();
//                    return;
//                }else{
//                    NewlistActiId = response.body().getActivid();
//                }
//            }
//            @Override
//            public void onFailure(Call<Activites> call, Throwable t) {
//                Toast.makeText(getApplicationContext(), ""+t.getCause()+"", Toast.LENGTH_LONG).show();
//            }
//        });
//
//    }

    private void savecontrib() {
        Retrofit retrofit = new Retrofit.Builder()
               // .baseUrl("http://192.168.137.1/api/")
                .baseUrl("http://192.168.1.169/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        jsonPlaceHolderApi = retrofit.create(JsonPlaceHolderApi.class);
        Contribuables contrib = new Contribuables(""+noms+"",
                ""+prenoms+"",
                ""+numcnis+"",
                ""+contacts+"",
                ""+nationalites+"",
                toIntArray(listIdActiv),
                ""+logins+"",
                ""+passwords+"",
                coordonnex,
                coordonney);
        Call<Contribuables> call = jsonPlaceHolderApi.sendcontribuable(contrib);
        call.enqueue(new Callback<Contribuables>() {
            @Override
            public void onResponse(Call<Contribuables> call, Response<Contribuables> response) {
                if(!response.isSuccessful()){
                    Contribuables contribu = response.body();
                    Toast.makeText(getApplicationContext(), "Echec enregistrement"+response.errorBody(), Toast.LENGTH_LONG).show();
                    return;
                }
                else{
                    successMessage();
                    Toast.makeText(getApplicationContext(), "succès enregistré", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Contribuables> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "serveur ne repond pas", Toast.LENGTH_LONG).show();
            }
        });
    }
    @RequiresApi(api = Build.VERSION_CODES.Q)
    public void getCurrentLocation(){
        if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED){
            fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>(){
                @Override
                public void onComplete(@NonNull Task<Location> task){
                    //initialisation de Location
                    Location location = task.getResult();
                    if(location != null){
                        try {
                        //initialisation de geocode
                        Geocoder geocoder = new Geocoder(getApplicationContext(), Locale.getDefault());
                        //initialiser la liste d'adresse
                        List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            coordonnex = ((float) addresses.get(0).getLatitude());
                            coordonney = ((float) addresses.get(0).getLongitude());
                            cordx.setText(""+coordonnex);
                            cordy.setText(""+coordonney);
                            commune.setText(addresses.get(0).getLocality());
                            quartier.setText(addresses.get(0).getSubLocality());
                            Toast.makeText(getApplicationContext(), "Latitude : "+coordonnex+ "Longitude :"+coordonney, Toast.LENGTH_LONG).show();

                        }catch (IOException e){
                            e.printStackTrace();
                        }
                    }
                }
            });
        }else{
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_BACKGROUND_LOCATION},44);
        }
    }
    public void successMessage() {
        new SweetAlertDialog(this, SweetAlertDialog.SUCCESS_TYPE).setTitleText("succès!").setContentText("Contribuable enregistré!").setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
            @Override
            public void onClick(SweetAlertDialog sweetAlertDialog) {
                sweetAlertDialog.dismissWithAnimation();
                Intent menu = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(menu);
                finish();
            }
        }).show();

    }
}