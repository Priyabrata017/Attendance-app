package com.attend.attendance;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    EditText email, password;
    Button login;
    String d_email, d_pass;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firebaseAuth= FirebaseAuth.getInstance();
        if(firebaseAuth.getCurrentUser() != null){
            finish();
            startActivity(new Intent(MainActivity.this,dashboard.class));
        }
        email = findViewById(R.id.emailid);
        password = findViewById(R.id.pass);
        login = findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSignIn();
                d_email = email.getText().toString();
                d_pass = password.getText().toString();
            }

        });
    }
    @Override
    protected void onStart() {
        super.onStart();
    }
    public void startSignIn(){
        String  d_email = email.getText().toString();
        String  d_pass = password.getText().toString();
        if(TextUtils.isEmpty(d_email)|| TextUtils.isEmpty(d_pass))
        {
            Toast.makeText(MainActivity.this, "fields are empty", Toast.LENGTH_LONG).show();

        }
        else{
            firebaseAuth.signInWithEmailAndPassword(d_email,d_pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (!task.isSuccessful()) {
                        Toast.makeText(MainActivity.this,  task.getException()+"Sign in prob", Toast.LENGTH_LONG).show();
                    }
                    else
                    {
                        finish();
                        startActivity(new Intent(MainActivity.this,dashboard.class));
                    }
                }

            });
        }
    }
}