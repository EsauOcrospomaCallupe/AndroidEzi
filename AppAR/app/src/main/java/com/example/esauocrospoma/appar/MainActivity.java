package com.example.esauocrospoma.appar;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.wikitude.architect.ArchitectStartupConfiguration;
import com.wikitude.architect.ArchitectView;
import com.wikitude.common.startup.StartupConfiguration;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ArchitectView architectview;
    public static final String WIKITUDE_SDK_KEY="pIdkO2C2Y0n4PnDmhe+Z17gm/olO2+68pck6vfg7TCrX6f2G8Tk1UYL5EQf6cOhbs8o7h0CIbd7cweIQmV/VMYLpccfS4Hc5so9q/7ETos+Kbx0CVTjvfXZ+HSey5vV0Qn8xXfM22S/rCzJdbrY9kc6z+XsUHBdIdzpIbtVh9T1TYWx0ZWRfX6z4LDOdaAMVSLGV45a836ksmCPCNhGI3Ixq/UjNjwS5Ml+1EWzVybiTQnmO7qs1ywcfYwujAG0T4VhNJGFrWEKmhinDpUIfDfq6fASQTKzCAIuqPWceP5xytKvCzWkHifsiJcHHDhgTnb7Kxt8aOCLND3rTbDagen+ZEHLWzNpAlOiPgSUlg2yqrPIuR+xlmlGJFS3aPRgNXnKaMn68yQoefPyejWzymMPUSSuBoMfJJ8hxguvrre2CjUJ08r92tkFI1M68duuP5/zQRUO1Pwgo+27cxsG02KbI/IRVxzMq7dnJxgxOX7lOJtcgvcBMgMJ87JIA9AiivYShgJe2GXoys8M6mSJQM9PUwmo7MeLnTHm2LwBNHMUsyMxm2gID3ei+mXdfajyHVHVNo2DN+Z2iMQC/VDBLCvzVpvbXP9xoD7euRSKUua2DZWiQ0kkSfTuYZRifzIy1ZOHVG45gR9QU7wEYNzfh6j7Yx8vpowdnkrdsLiAcU+k=";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                this.architectview.load("videosAR/index.html");
            }else{
                Toast.makeText(MainActivity.this,"ES NULL",Toast.LENGTH_LONG).show();
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
