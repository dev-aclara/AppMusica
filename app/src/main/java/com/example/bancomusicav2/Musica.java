package com.example.bancomusicav2;

public class Musica {
    private int idMusica;
    private String musica;
    private String artista;
    private String genero;
    private String album;

    public Musica (String musica, String artista, String genero, String album)
    {	this.musica = musica;
        this.artista = artista;
        this.genero = genero;
        this.album = album;
    }

    public Musica(){}

    public Musica(int idMusica, String musica, String artista, String genero, String album)
    {
        this.musica = musica;
        this.artista = artista;
        this.genero = genero;
        this.album = album;
        this.idMusica = idMusica;
    }

    public String getMusica()
    {  return musica;
    }

    public void setMusica(String musica)
    {  this.musica = musica;
    }

    public String getArtista()
    {   return artista;
    }

    public void setArtista(String artista)
    {   this.artista = artista;
    }

    public String getGenero()
    {  return genero;
    }

    public void setGenero(String genero)
    {  this.genero = genero;
    }

    public String getAlbum()
    {   return album;
    }

    public void setAlbum(String album)
    {   this.album = album;
    }

    public int getIdMusica()
    {
        return idMusica;
    }

    public void setIdMusica(int idMusica) {
        this.idMusica = idMusica;
    }
}
