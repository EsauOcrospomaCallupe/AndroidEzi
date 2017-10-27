package com.example.esauocrospoma.appar.models;

import android.graphics.Bitmap;

import com.orm.SugarRecord;

/**
 * Created by esauocrospoma on 20/10/17.
 */

public class Foto extends SugarRecord{
    String id_foto;
    String foto;
    boolean isFavorite;

    public String getId_foto() {
        return id_foto;
    }

    public void setId_foto(String id_foto) {
        this.id_foto = id_foto;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorite) {
        isFavorite = favorite;
    }
}
