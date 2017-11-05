package com.example.esauocrospoma.appar.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.esauocrospoma.appar.Managers.FirebaseManager;
import com.example.esauocrospoma.appar.Managers.PreferenceManager;
import com.example.esauocrospoma.appar.models.User;
import com.example.esauocrospoma.appar.R;
import com.example.esauocrospoma.appar.Util.FirebaseConstants;
import com.example.esauocrospoma.appar.Util.Utils;
import com.google.firebase.database.DatabaseReference;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity {

    EditText et_name;
    EditText et_last_name;
    EditText et_pass;
    EditText et_email;
    EditText et_user;
    EditText et_address;
    ImageButton bt_register;

    FirebaseManager manager;
    PreferenceManager p_manager;

    User newUser;
    List<String> users = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        manager = new FirebaseManager();

        p_manager = PreferenceManager.getInstance(this);

        users = Utils.getListOfUsers(manager);

        setupViews();
    }

    private void setupViews() {
        et_name =  findViewById(R.id.et_name);
        et_last_name =  findViewById(R.id.et_last_name);
        et_pass =  findViewById(R.id.et_pass);
        et_email =  findViewById(R.id.et_email);
        et_user =  findViewById(R.id.et_user);
        et_address =  findViewById(R.id.et_address);
        bt_register =  findViewById(R.id.bt_register);

        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!Utils.verifyRegister(et_name,et_last_name,et_user,et_pass,et_email,et_address)){
                    if(!verifyUser(et_user)){
                        createUser(et_name,et_last_name,et_user,et_pass,et_email,et_address);
                    }else{
                        Toast.makeText(RegisterActivity.this,"Usuario existente.", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private boolean verifyUser(EditText et_user) {
        boolean cancel = false;
        if(users != null){
            for(String s : users){
                if(s.trim().equals(et_user.getText().toString().trim())){
                    cancel = true;
                    break;
                }
            }
        }

        return cancel;
    }

    private void createUser(EditText name, EditText lastName, EditText user, EditText pass, EditText email, EditText address) {
        newUser = new User();
        newUser.setNombres(name.getText().toString().trim());
        newUser.setApellidos(lastName.getText().toString().trim());
        newUser.setMail(email.getText().toString().trim());
        newUser.setPassword(pass.getText().toString().trim());
        newUser.setUsername(user.getText().toString().trim());
        newUser.setDireccion(address.getText().toString().trim());

        Gson gson = new Gson();
        String json = gson.toJson(newUser);

        p_manager.setPrefenceSession(json);
        p_manager.setPreferenceMail(newUser.getMail());
        p_manager.setPreferenceUsername(newUser.getUsername());

        if(manager.getDatabase().getReference(FirebaseConstants.CHILD_USERS) == null){
            manager.getUsersReference().child(FirebaseConstants.CHILD_USERS).push();
        }

        DatabaseReference newRef = manager.getUsersReference().child(newUser.getUsername()).push();
        newRef.setValue(newUser);

        Intent i = new Intent(RegisterActivity.this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
        finish();
    }
}
