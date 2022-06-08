package com.example.bancomusicav2;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MusicaDAO {
    public static final String nomeTabela = "Musica";
    public static final String colunaId = "idMusica";
    public static final String colunaMusica = "nomeMusica";
    public static final String colunaArtista = "nomeArtista";
    public static final String colunaGenero = "nomeGenero";
    public static final String colunaAlbum= "nomeAlbum";

    public static final String scriptCriaMusica = "CREATE TABLE " + nomeTabela + "("
            + colunaId + " INTEGER PRIMARY KEY," + colunaMusica + " TEXT,"
            + colunaArtista + " TEXT," +
            colunaGenero + " TEXT," +
            colunaAlbum + " TEXT" +")";

    public static final String scriptDropMusica = "DROP TABLE IF EXISTS " + nomeTabela;

    private SQLiteDatabase dataBase = null;

    private static MusicaDAO instance;  // singleton

    public static MusicaDAO getInstance(Context context) {
        if (instance == null)
            instance = new MusicaDAO(context);
        return instance;
    }

    private MusicaDAO(Context context) {
        PersistenceHelper persistenceHelper = PersistenceHelper.getInstance(context);
        dataBase = persistenceHelper.getWritableDatabase();
    }

    public void salvar(Object obj) {
        Musica musica = (Musica) obj;   // casting
        ContentValues values = gerarContentValuesMusica(musica);
        dataBase.insert(nomeTabela, null, values);
    }

    private ContentValues gerarContentValuesMusica(Musica musica) {
        ContentValues values = new ContentValues();
        values.put(colunaMusica, musica.getMusica());
        values.put(colunaArtista, musica.getArtista());
        values.put(colunaGenero, musica.getGenero());
        values.put(colunaAlbum, musica.getAlbum());
        // Serve para linkar a coluna com o registro:
        return values;
    }

    public Musica recuperarMusicaNome(Musica musica) {
        //String query = "SELECT * FROM " + nomeTabela + " where nomeMusica = " + "'" + musica.getMusica() + "'";
        String pesquisa = musica.getMusica();
        Cursor cursor = dataBase.query("Musica", new String[]{"idMusica","nomeMusica", "nomeArtista", "nomeGenero", "nomeAlbum"}
                ,"nomeMusica = ?", new String[]{pesquisa},null,null,null);
        musica = construirMusica(cursor);
        return musica;
    }

    public ArrayList<String> listaTodos()
    {
        ArrayList<String> musicas = new ArrayList<>();
        Cursor cursor = dataBase.query("Musica", new String[]{"idMusica","nomeMusica", "nomeArtista", "nomeGenero", "nomeAlbum"}
                ,null, null,null,null,null);
        while (cursor.moveToNext()){
            musicas.add("Musica\uD83C\uDFB5: "+cursor.getString(1) + " \n"+
                    "Artista\uD83D\uDC68\u200D\uD83C\uDFA4: "+cursor.getString(2)+ " \n"+
                    "Genero\uD83C\uDFB7: "+cursor.getString(3)+ " \n"+
                    "Album\uD83C\uDFA7: "+cursor.getString(4));
        }
        return musicas;
    }

    public String ResultadoMusica(String pesquisa)
    {
        String resultado=null;
        Cursor cursor = dataBase.query("Musica", new String[]{"idMusica","nomeMusica", "nomeArtista", "nomeGenero", "nomeAlbum"}
                ,"nomeMusica = ?", new String[]{pesquisa},null,null,null);
        while (cursor.moveToNext()) {
            resultado = "Musica\uD83C\uDFB5: " + cursor.getString(1) + " \n"
                    + "Artista\uD83D\uDC68\u200D\uD83C\uDFA4: " + cursor.getString(2);
        }
        return resultado;
    }

    public Musica recuperarMusicaId(Musica musica) {
        // Este é um procedimento para recuperar um cliente no BD a partir do Nome
        String query = "SELECT * FROM " + nomeTabela + " where idMusica = " + Integer.toString(musica.getIdMusica());
        Cursor cursor = dataBase.rawQuery(query,null);
        musica = construirMusica(cursor);
        return musica;
    }

    private Musica construirMusica(Cursor cursor) {
        if (cursor == null)
            return null;
        try {
            cursor.moveToFirst();    // posiciona o cursor - recupera o primeiro que localizar
            // retorna a coluna do campo - inteiro
            int indexId = cursor.getColumnIndex(colunaId);
            int indexMusica = cursor.getColumnIndex(colunaMusica);
            int indexArtista = cursor.getColumnIndex(colunaArtista);
            int indexGenero = cursor.getColumnIndex(colunaGenero);
            int indexAlbum = cursor.getColumnIndex(colunaAlbum);

            int id = cursor.getInt(indexId);
            String musica = cursor.getString(indexMusica);
            String artista = cursor.getString(indexArtista);
            String genero = cursor.getString(indexGenero);
            String album = cursor.getString(indexAlbum);
            // cria uma nova instancia do cliente e retorna o mesmo
            return new Musica(id, musica, artista, genero, album);

        } catch (Exception e) {
            return null;
        }

    }
}
