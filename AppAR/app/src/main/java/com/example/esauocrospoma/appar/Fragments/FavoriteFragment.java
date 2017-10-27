package com.example.esauocrospoma.appar.Fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.esauocrospoma.appar.Adapters.FavoritesAdapter;
import com.example.esauocrospoma.appar.R;
import com.example.esauocrospoma.appar.models.Foto;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

/**
 * Created by esauocrospoma on 18/10/17.
 */

public class FavoriteFragment extends Fragment {

    static final int REQUEST_IMAGE_CAPTURE = 1;

    RecyclerView rv_favoritos;
    FavoritesAdapter adapter;
    FloatingActionButton fab;
    List<Foto> foto_ls = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_favorites,container,false);

        rv_favoritos = (RecyclerView) v.findViewById(R.id.rv_favoritos);
        fab = (FloatingActionButton) v.findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dispatchTakePictureIntent();
            }
        });

        getFotos();

        setDataIntoRv();

        return v;
    }

    private void getFotos() {
        foto_ls.addAll(Foto.listAll(Foto.class));
       // Toast.makeText(getActivity(),"size"+foto_ls.size(),Toast.LENGTH_SHORT).show();
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void setDataIntoRv() {
        rv_favoritos.setLayoutManager(new LinearLayoutManager(getActivity()));
        adapter = new FavoritesAdapter(getActivity(),foto_ls);
        rv_favoritos.setAdapter(adapter);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();

            Bitmap bmp = (Bitmap) extras.get("data");

            String bmp_string = BitMapToString(bmp);

            Foto foto = new Foto();
            foto.setFoto(bmp_string);
            foto.setFavorite(true);
            foto.save();

            foto_ls.add(foto);

            setDataIntoRv();
        }
    }

    public String BitMapToString(Bitmap bitmap){
        ByteArrayOutputStream baos=new  ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG,100, baos);
        byte [] b=baos.toByteArray();
        String temp = Base64.encodeToString(b, Base64.DEFAULT);
        return temp;
    }
}
