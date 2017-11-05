package com.example.esauocrospoma.appar.Fragments;

import android.Manifest;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.esauocrospoma.appar.Managers.FirebaseManager;
import com.example.esauocrospoma.appar.Managers.PreferenceManager;
import com.example.esauocrospoma.appar.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.MapsInitializer;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esauocrospoma on 18/10/17.
 */

public class MapFragment extends Fragment {

    MapView mMapView;
    private GoogleMap googleMap;
    FirebaseManager manager;
    PreferenceManager p_manager;
    Dialog dialog;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_mapa,container,false);
        mMapView = v.findViewById(R.id.mapView);
        mMapView.onCreate(savedInstanceState);

        manager = new FirebaseManager();
        p_manager = PreferenceManager.getInstance(getActivity());

        mMapView.onResume(); // needed to get the map to display immediately

        try {
            MapsInitializer.initialize(getActivity().getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }

        mMapView.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap mMap) {
                googleMap = mMap;

                // For showing a move to my location button
                if (ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }else{
                    googleMap.setMyLocationEnabled(true);

                    // For zooming automatically to the location of the marker
                    LatLng ubicacion_lima = new LatLng(-12.0780938,-77.0368886);
                    CameraPosition cameraPosition = new CameraPosition.Builder().target(ubicacion_lima).zoom(15).build();
                    googleMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));
                    addMarker(-12.0780938,-77.0368886);
                }
            }
        });

        return v;
    }

    private void addMarker(Double latitud, Double longitud) {
        // For dropping a marker at a point on the Map
        LatLng ubicacion = new LatLng(latitud,longitud);

        googleMap.addMarker(new MarkerOptions().position(ubicacion).title("MUSEO DE HISTORIA NATURAL UNMSM").snippet(""));
    }
}
