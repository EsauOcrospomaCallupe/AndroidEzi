package com.example.esauocrospoma.appar.Menu;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.esauocrospoma.appar.Activities.SplashActivity;
import com.example.esauocrospoma.appar.Activities.ThreeDActivity;
import com.example.esauocrospoma.appar.Activities.VideoActivity;
import com.example.esauocrospoma.appar.Fragments.AccountFragment;
import com.example.esauocrospoma.appar.Fragments.FavoriteFragment;
import com.example.esauocrospoma.appar.Fragments.HomeFragment;
import com.example.esauocrospoma.appar.Fragments.MapFragment;
import com.example.esauocrospoma.appar.Managers.PreferenceManager;
import com.example.esauocrospoma.appar.R;

import java.util.ArrayList;
import java.util.List;


public class FragmentDrawer extends Fragment {

    TextView tv_nombre;
    RecyclerView drawerList;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;
    private NavigationDrawerAdapter adapter;
    private View view;
    private RecyclerView.LayoutManager layoutManager;

    PreferenceManager manager;

    Fragment fragment = null;

    boolean isHome = true;

    public FragmentDrawer() {
    }

    private List<NavDrawerItem> getData() {
        List<NavDrawerItem> data = new ArrayList<>();
        data.add(new NavDrawerItem("Mis Favoritos",true,true, R.drawable.heart));
        data.add(new NavDrawerItem("Home",true,true, R.drawable.casita));
        data.add(new NavDrawerItem("Video",true,true,R.drawable.video));
        data.add(new NavDrawerItem("3D",true,true,R.drawable.video_3d));
        data.add(new NavDrawerItem("Mapa",true,true,R.drawable.account_circle));
        data.add(new NavDrawerItem("Account",true,true,R.drawable.account_off));
        data.add(new NavDrawerItem("Salir",true,true,R.drawable.account_off));
        return data;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_drawer, container, false);

        drawerList = (RecyclerView) view.findViewById(R.id.drawerList);

        manager = PreferenceManager.getInstance(getActivity());
        return view;
    }

    public void setUp(DrawerLayout drawerLayout, boolean home) {
        setupElements();
        isHome = home;
        mDrawerLayout = drawerLayout;
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), drawerLayout, R.string.drawer_open, R.string.drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                getActivity().invalidateOptionsMenu();
            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };

        mDrawerLayout.post(new Runnable() {
            @Override
            public void run() {
                mDrawerToggle.syncState();
            }
        });

    }

    public void setupElements(){
        setupRecyclerView();
    }

    private void setupRecyclerView(){
        drawerList.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        drawerList.setLayoutManager(layoutManager);
        adapter = new NavigationDrawerAdapter(getContext(),getData());
        drawerList.setAdapter(adapter);

        adapter.setOnItemClickListener(new NavigationDrawerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View v) {
                if(getActivity() != null){
                    switch (position){
                        case 0:
                            fragment = new FavoriteFragment();
                            changeFragment();
                            break;
                        case 1:
                            fragment = new HomeFragment();
                            changeFragment();
                            break;
                        case 2:
                            Intent i = new Intent(getActivity(), VideoActivity.class);
                            startActivity(i);
                            break;
                        case 3:
                            Intent e = new Intent(getActivity(), ThreeDActivity.class);
                            startActivity(e);
                            break;
                        case 4:
                            fragment = new MapFragment();
                            changeFragment();
                            break;
                        case 5:
                            fragment = new AccountFragment();
                            changeFragment();
                            break;
                        case 6:
                            showDialog();
                            break;

                    }

                }
            }
        });
    }

    private void changeFragment(){
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.main_fragment_container,fragment);
        fragmentTransaction.commit();
        mDrawerLayout.closeDrawers();
    }

    private void showDialog() {
        manager.setPrefenceSession("");
        manager.setPreferenceUsername("");
        manager.setPreferenceMail("");
        Intent i = new Intent(getActivity(),SplashActivity.class);
        getActivity().startActivity(i);
        getActivity().finish();
//       final Dialog dialog = new Dialog(getActivity());
//
//        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
//        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//
//        dialog.setContentView(R.layout.dialog_logout);
//
//        TextView dialog_si = (TextView)dialog.findViewById(R.id.dialog_si);
//        TextView dialog_no = (TextView)dialog.findViewById(R.id.dialog_no);
//
//        dialog_si.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//                startActivity(new Intent(getActivity(), SplashActivity.class));
//                getActivity().finish();
//            }
//        });
//
//        dialog_no.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dialog.dismiss();
//            }
//        });
//
//        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        dialog.show();
    }
}