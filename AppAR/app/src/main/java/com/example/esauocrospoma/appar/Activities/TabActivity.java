package com.example.esauocrospoma.appar.Activities;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.esauocrospoma.appar.R;

public class TabActivity extends AppCompatActivity {

    TabLayout tabs;
    //ViewPager pager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);
    }
}
