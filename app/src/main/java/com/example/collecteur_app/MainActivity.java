package com.example.collecteur_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button creer,modifier,controle, collecter, bilan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        creer = findViewById(R.id.creer);
        modifier = findViewById(R.id.modifier);
        controle = findViewById(R.id.controle);
        collecter = findViewById(R.id.collecter);
        bilan = findViewById(R.id.bilan);

        creer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent creation = new Intent(getApplicationContext(), Creation.class);
                startActivity(creation);
            }
        });

        modifier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent modification = new Intent(getApplicationContext(), Modification.class);
                startActivity(modification);
            }
        });

        controle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent controles = new Intent(getApplicationContext(), Controle.class);
                startActivity(controles);
            }
        });

        collecter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent collecters = new Intent(getApplicationContext(), Collecter.class);
                startActivity(collecters);
            }
        });

        bilan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bilans = new Intent(getApplicationContext(), Bilan.class);
                startActivity(bilans);
            }
        });

    }
}