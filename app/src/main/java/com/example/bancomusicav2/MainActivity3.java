package com.example.bancomusicav2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    MusicaDAO musicaDAO;
    EditText txtPesquisar;
    Button btnCadastrar;
    Button btnPesquisar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        txtPesquisar = findViewById(R.id.txtPesquisar);
        btnCadastrar = findViewById(R.id.btnCadastrar);
        btnPesquisar = findViewById(R.id.btnPesquisar);
        musicaDAO = MusicaDAO.getInstance(getBaseContext());
    }

    public void VoltarTela(View v)
    {
        Intent i = new Intent(this, MainActivity2.class);
        startActivity(i);
    }

    public void Cadastrar(View v)
    {
        Intent i= getIntent();
        Bundle params = i.getExtras();

        Musica musica = new Musica(params.getString("musica"),params.getString("artista"),
                params.getString("genero"),params.getString("album"));
        musicaDAO.salvar(musica);

        Toast.makeText(this, "Musica cadastrada com sucesso" , Toast.LENGTH_SHORT).show();
    }

    public void Pesquisar(View v)
    {
        String pesquisa = txtPesquisar.getText().toString();
        Musica m = new Musica(pesquisa,"","","");
        m = musicaDAO.recuperarMusicaNome(m);


        if(m != null)
        {
            Intent i = new Intent(v.getContext(), MainActivity4.class);
            Bundle params= new Bundle();
            params.putString("pesquisa",pesquisa);
            i.putExtras(params);
            startActivity(i);
        }
        else
        {
            Toast.makeText(this, "Musica não Encontrada \uD83D\uDE15" , Toast.LENGTH_SHORT).show();
        }

    }

}