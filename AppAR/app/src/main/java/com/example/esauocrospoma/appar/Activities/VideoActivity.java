package com.example.esauocrospoma.appar.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.example.esauocrospoma.appar.R;
import com.wikitude.architect.ArchitectStartupConfiguration;
import com.wikitude.architect.ArchitectView;

import java.io.IOException;

public class VideoActivity extends AppCompatActivity {

    private ArchitectView architectview;
    public static final String WIKITUDE_SDK_KEY="KQpTYRMV+cgSNoiBaQtRYkHO8wPq2edb7KFLmTsu7LDOzgADg37MgtpMPfeM5icYi8vRw6v+xP/uf19XnEKC79ocsasEgSKvqhTiIOk0GzZUId/dEZWbk1a7NRQlrGOFJ0aMCmR+mep1QdaKtywjExv6j4jNlghMkQEqHOQXCMpTYWx0ZWRfX3CbKPvl+x31WQAVovPnwt4FoDA1XpVVoeTco5uVA2jOcszXTkUL2ji7hsm4R/xH7bjCImx5jg61Ui88T9AxEW33hQbHepQCFQHSS2Sx+heXdJ/82lJRdgk9KjMPYYdWSoG54ilajTRaRR6LHg9z3Bgv9eP4+hcUgF3s7F+FlCAms6cnwUPfukiILYqeFiMSwUy/qfdzNWEhxIil2mNJ07pjs9QNTX9Q7/ycGad1pS4YdRmvqcZ8360x/gOnSPtbTvVfU1NhbO9B+OSVptzZwTc+zJIsOnuC3/iQFtdibrXoUm7OZEAXG0N7iROrvk0V8zJSglpbTpk/aDpK5BOfo5Q+MbpUu6sWR8KYys8ljjPGtpR/ynnRvCi7oekob0qa0nmbS63hnsEUqvy7ZCwnHCYLbN3c6VJp8IVYuV1X/SYIO3UZWP2XkGUlp95pjESCiyVD8YsAXafHQvHQ5l0L2/I4LrGlLUfnaXgnTooHPCDbHmpZcDyTZYOHFGNXOzSyoYHfCCTuiMkiNzSKerHjqgfLJayU6a6uvg==";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        architectview = (ArchitectView)findViewById(R.id.architectview);
        final ArchitectStartupConfiguration configuration = new ArchitectStartupConfiguration();
        configuration.setLicenseKey(WIKITUDE_SDK_KEY);


        try{
            architectview.onCreate(configuration);

        }catch(RuntimeException e){
            e.printStackTrace();
            Toast.makeText(this, "No se pudo realizar",Toast.LENGTH_LONG).show();
            finish();
        }


    }

    @Override
    protected void onPostCreate( Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if(this.architectview != null){
            this.architectview.onPostCreate();
        }
        try {
            if(this.architectview != null){
                this.architectview.load("videosAR/index2.html");
            }else{
                Toast.makeText(VideoActivity.this,"ES NULL",Toast.LENGTH_LONG).show();
            }
        }catch (IOException e ){
            e.printStackTrace();
        }
    }


    @Override
    public void onLowMemory() {
        super.onLowMemory();
        if(this.architectview != null){
            this.architectview.onLowMemory();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(this.architectview != null){
            this.architectview.onResume();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(this.architectview != null){
            this.architectview.onPause();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(this.architectview != null){
            this.architectview.onDestroy();
        }
    }
}
