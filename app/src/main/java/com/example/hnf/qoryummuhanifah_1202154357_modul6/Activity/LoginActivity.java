package com.example.hnf.qoryummuhanifah_1202154357_modul6.Activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.hnf.qoryummuhanifah_1202154357_modul6.Constant.Constant;
import com.example.hnf.qoryummuhanifah_1202154357_modul6.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextEmail, editTextPass;
    private ImageButton buttonSigin;

    private ProgressDialog progressDialog;

    private FirebaseAuth Auth;
    private DatabaseReference refUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Auth = Constant.mAuth;
        refUser = Constant.mRefUser;

        progressDialog = new ProgressDialog(this);

        editTextEmail = (EditText) findViewById(R.id.etEmail);
        editTextPass = (EditText) findViewById(R.id.etPass);

        buttonSigin = (ImageButton) findViewById(R.id.imgbtnSignin);

        buttonSigin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkLogin();
            }
        });
    }

    private void checkLogin() {

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPass.getText().toString().trim();

        if(!TextUtils.isEmpty(email) && !TextUtils.isEmpty(password)){

            progressDialog.setMessage("Loading...");
            progressDialog.show();

            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){

                        checkUserExist();
                        progressDialog.dismiss();
                    } else{

                        Toast.makeText(LoginActivity.this, "Error SignIn", Toast.LENGTH_LONG).show();
                        progressDialog.dismiss();
                    }
                }
            });
        }
    }

    private void checkUserExist() {

        final String user_id = mAuth.getCurrentUser().getUid();

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(dataSnapshot.hasChild(user_id)){

                    Intent mainIntent = new Intent(LoginActivity.this, HomeActivity.class);
                    mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(mainIntent);
                } else {

                    Toast.makeText(LoginActivity.this, "You need create an account", Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void signup(View view){

        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }
}
