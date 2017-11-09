package com.example.esauocrospoma.appar.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.esauocrospoma.appar.Managers.FirebaseManager;
import com.example.esauocrospoma.appar.Managers.PreferenceManager;
import com.example.esauocrospoma.appar.R;
import com.example.esauocrospoma.appar.Util.FirebaseConstants;
import com.example.esauocrospoma.appar.models.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by esauocrospoma on 18/10/17.
 */

public class AccountFragment extends Fragment {
    EditText et_name;
    EditText et_last_name;
    EditText et_address;
    EditText et_email;
    EditText et_user;
    Button bt_save;
//    TextView tv_change_pass;

    PreferenceManager p_manager;

    FirebaseManager manager;

    List<String> users_ls = new ArrayList<>();

    User user;

    String key;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_account,container,false);

        manager = FirebaseManager.getInstance();

        et_name = v.findViewById(R.id.et_name);
        et_last_name = v.findViewById(R.id.et_last_name);
        et_address = v.findViewById(R.id.et_address);
        et_email = v.findViewById(R.id.et_email);
        et_user = v.findViewById(R.id.et_user);
        bt_save = v.findViewById(R.id.bt_save);
//        tv_change_pass = (TextView) v.findViewById(R.id.tv_change_pass);

//        tv_change_pass.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent i = new Intent(getActivity(), ChangePasswordActivity.class);
//                startActivity(i);
//            }
//        });

        p_manager = PreferenceManager.getInstance(getActivity());

        Gson gson = new Gson();
        user = gson.fromJson(p_manager.getPreferenceSession(), User.class);

        fillData();

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!verifyEdit()){
                    saveData(et_name.getText().toString(),et_last_name.getText().toString(),et_address.getText().toString(),et_email.getText().toString(),et_user.getText().toString());
                }
            }
        });

        return v;
    }


    private void saveData(final String nombres, final String apellidos,final String direccion,final String email,final String username) {
        if(manager.getDatabase().getReference(FirebaseConstants.REF_DATA).child(FirebaseConstants.CHILD_USERS) != null) {
            manager.getDatabase().getReference(FirebaseConstants.REF_DATA).child(FirebaseConstants.CHILD_USERS).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot data : dataSnapshot.getChildren()) {
                        for (DataSnapshot data_child : data.getChildren()) {
                            if (data_child.child("username").getValue(String.class).equals(p_manager.getPreferenceUsername())) {
                                key = data_child.getKey();
                                break;
                            }
                        }
                    }

                    DatabaseReference ref = manager.getUsersReference().child(p_manager.getPreferenceUsername()).child(key);
                    ref.child("nombres").setValue(nombres);
                    ref.child("apellidos").setValue(apellidos);
                    ref.child("direccion").setValue(direccion);
                    ref.child("mail").setValue(email);
                    ref.child("username").setValue(username);


                    User u = new User();
                    u.setNombres(nombres);
                    u.setApellidos(apellidos);
                    u.setDireccion(direccion);
                    u.setMail(email);
                    u.setUsername(username);

                    Gson gson = new Gson();
                    String json = gson.toJson(u);

                    p_manager.setPrefenceSession(json);
                    p_manager.setPreferenceUsername(username);
                    p_manager.setPreferenceMail(email);

                    Toast.makeText(getActivity(),"Datos editados correctamente",Toast.LENGTH_SHORT).show();

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }
    }

    private boolean verifyEdit() {
        boolean cancel = false;

        et_name.setError(null);
        et_last_name.setError(null);
        et_address.setError(null);
        et_email.setError(null);
        et_user.setError(null);

        if(et_name.getText().toString().isEmpty() || et_name.getText().toString().equals("")){
            et_name.setError("Requerido");
            cancel = true;
        }

        if(et_last_name.getText().toString().isEmpty() || et_last_name.getText().toString().equals("")){
            et_last_name.setError("Requerido");
            cancel = true;
        }

        if(et_address.getText().toString().isEmpty() || et_address.getText().toString().equals("")){
            et_address.setError("Requerido");
            cancel = true;
        }

        if(et_email.getText().toString().isEmpty() || et_email.getText().toString().equals("")){
            et_email.setError("Requerido");
            cancel = true;
        }

        if(et_user.getText().toString().isEmpty() || et_user.getText().toString().equals("")){
            et_user.setError("Requerido");
            cancel = true;
        }

        if(manager.getDatabase().getReference(FirebaseConstants.REF_DATA).child(FirebaseConstants.CHILD_USERS) != null) {
            manager.getDatabase().getReference(FirebaseConstants.REF_DATA).child(FirebaseConstants.CHILD_USERS).addListenerForSingleValueEvent(new ValueEventListener() {

                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {

                }
            });
        }

        return cancel;
    }

    private void fillData() {
        et_name.setText(user.getNombres());
        et_last_name.setText(user.getApellidos());
        et_address.setText(user.getDireccion());
        et_email.setText(user.getMail());
        et_user.setText(user.getUsername());
    }
}
