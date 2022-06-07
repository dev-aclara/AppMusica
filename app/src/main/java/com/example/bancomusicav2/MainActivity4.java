package com.example.bancomusicav2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity4 extends AppCompatActivity {
    ListView listResultado;
    MusicaDAO musicaDAO;
    String pesquisa,resultado;
    TextView txtResultMusic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        listResultado = findViewById(R.id.listResultado);
        txtResultMusic = findViewById(R.id.txtResultMusic);
        musicaDAO = MusicaDAO.getInstance(getBaseContext());
        Intent i= getIntent();
        Bundle params = i.getExtras();
        pesquisa = params.getString("pesquisa");
        resultado = musicaDAO.ResultadoMusica(pesquisa);
        txtResultMusic.setText(resultado);
        Listar();

        //listResultado = musicaDAO.listaTodos();
    }

    public void Listar(){
        ArrayList<String> musicas = musicaDAO.listaTodos();
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, musicas);
        listResultado.setAdapter(adapter);
    }

    public void novaPesquisa(View v)
    {
        Intent i= getIntent();
        i = new Intent(this, MainActivity3.class);
        startActivity(i);
    }

    public void novoCadastro(View v)
    {
        Intent i= getIntent();
        i = new Intent(this, MainActivity.class);
        startActivity(i);
    }

}