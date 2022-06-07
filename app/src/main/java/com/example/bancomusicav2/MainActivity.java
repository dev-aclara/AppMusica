package com.example.bancomusicav2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText txtMusica,txtArtista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtMusica = findViewById(R.id.txtMusica);
        txtArtista = findViewById(R.id.txtArtista);
    }

    public void ProximaTela(View v)
    {
        Intent i = new Intent(v.getContext(), MainActivity2.class);
        Bundle params= new Bundle();
        String musica = txtMusica.getText().toString();
        String artista = txtArtista.getText().toString();
        params.putString("musica",musica);
        params.putString("artista", artista);
        i.putExtras(params);
        startActivity(i);
    }
}