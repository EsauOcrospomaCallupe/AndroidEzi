package com.example.esauocrospoma.appar.Fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.esauocrospoma.appar.R;

/**
 * Created by esauocrospoma on 18/10/17.
 */

public class HomeFragment extends Fragment{

    ImageView iv_not_1;
    ImageView iv_not_2;
    ImageView iv_not_3;
    ImageView iv_not_4;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_home,container,false);

        iv_not_1 = (ImageView) v.findViewById(R.id.iv_not_1);
        iv_not_2 = (ImageView) v.findViewById(R.id.iv_not_2);
        iv_not_3 = (ImageView) v.findViewById(R.id.iv_not_3);
        iv_not_4 = (ImageView) v.findViewById(R.id.iv_not_4);

        iv_not_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Uri uri = Uri.parse("http://museohn.unmsm.edu.pe");

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(uri);
                startActivity(i);
            }
        });
        iv_not_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://museohn.unmsm.edu.pe");

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(uri);
                startActivity(i);
            }
        });

        iv_not_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://museohn.unmsm.edu.pe");

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(uri);
                startActivity(i);
            }
        });

        iv_not_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse("http://museohn.unmsm.edu.pe");

                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(uri);
                startActivity(i);
            }
        });


        return v;
    }
}
