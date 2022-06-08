package com.example.bancomusicav2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity2 extends AppCompatActivity {

    EditText txtGenero,txtAlbum;
    String musica, artista;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        txtGenero = findViewById(R.id.txtGenero);
        txtAlbum = findViewById(R.id.txtAlbum);
        Intent i= getIntent();
        Bundle params = i.getExtras();
        musica = params.getString("musica");
        artista = params.getString("artista");


    }

    public void VoltarTela(View v)
    {
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

    public void ProximaTela(View v)
    {
        Intent i = new Intent(v.getContext(), MainActivity3.class);
        Bundle params= new Bundle();
        String genero = txtGenero.getText().toString();
        String album = txtAlbum.getText().toString();
        params.putString("genero",genero);
        params.putString("album", album);
        params.putString("musica",musica);
        params.putString("artista",artista);
        i.putExtras(params);
        startActivity(i);
    }
}