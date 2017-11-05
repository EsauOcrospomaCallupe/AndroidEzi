package com.example.esauocrospoma.appar.Adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.esauocrospoma.appar.R;
import com.example.esauocrospoma.appar.models.Foto;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esauocrospoma on 20/10/17.
 */

public class FavoritesAdapter extends RecyclerView.Adapter<FavoritesAdapter.MyHolder> {

    Context context;
    List<Foto> data = new ArrayList<>();

    public FavoritesAdapter(Context context, List<Foto> data) {
        this.context = context;
        this.data = data;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.favorite_row,parent,false);

        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        holder.iv_fav.setImageBitmap(Bitmap.createScaledBitmap(StringToBitMap(data.get(position).getFoto()), 300,300, false));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public Bitmap StringToBitMap(String encodedString){
        try {
            byte [] encodeByte = Base64.decode(encodedString,Base64.DEFAULT);
            Bitmap bitmap = BitmapFactory.decodeByteArray(encodeByte, 0, encodeByte.length);
            return bitmap;
        } catch(Exception e) {
            e.getMessage();
            return null;
        }
    }

    public class MyHolder extends RecyclerView.ViewHolder{

        ImageView iv_fav;

        public MyHolder(View itemView) {
            super(itemView);
            iv_fav = (ImageView) itemView.findViewById(R.id.iv_fav);
        }
    }
}
