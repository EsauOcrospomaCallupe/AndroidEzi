package com.example.esauocrospoma.appar.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.esauocrospoma.appar.Managers.PreferenceManager;
import com.example.esauocrospoma.appar.R;

public class SplashActivity extends AppCompatActivity {

    Button bt_init;

    PreferenceManager manager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        manager = PreferenceManager.getInstance(this);

        bt_init = findViewById(R.id.bt_init);

        bt_init.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Class activity_class;
                if(manager.getPreferenceSession().isEmpty()){
                    activity_class= LoginActivity.class;
                }else{
                    activity_class = MainActivity.class;
                }
                Intent i = new Intent(SplashActivity.this, activity_class);
                startActivity(i);
                finish();
            }
        });
    }
}
