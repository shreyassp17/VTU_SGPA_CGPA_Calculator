package com.example.cgpacalculator;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    private EditText mEmail, mPass, mConfirmPass;
    private TextView mTextView;
    private Button btnSignup;
    private FirebaseAuth mAuth;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser =  mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEmail = findViewById(R.id.Email_register);
        mPass = findViewById(R.id.password_reg);
        mConfirmPass = findViewById(R.id.confirm_pass);
        btnSignup = findViewById(R.id.reg_button);
        mTextView = findViewById(R.id.textView2);
        mAuth = FirebaseAuth.getInstance();


        mTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,LoginActivity.class));
            }
        });
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String pass = mPass.getText().toString();
                String cpass = mConfirmPass.getText().toString();
                if(pass.length()<6){
                    Toast.makeText(MainActivity.this, "Minimum of 6 characters is required for password !", Toast.LENGTH_SHORT).show();
                }
                else if(!pass.equals(cpass)){
                    Toast.makeText(MainActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
                else{
                    createUser();
                }

            }
        });
    }
    private void createUser(){
        String email = mEmail.getText().toString();
        String pass = mPass.getText().toString();

            if(!email.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                if(!pass.isEmpty()){
                    mAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Toast.makeText(MainActivity.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(MainActivity.this,LoginActivity.class));
                            finish();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(MainActivity.this, "Registration Error", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    mPass.setError("Empty fields are not allowed");
                }
            }
            else if(email.isEmpty()){
                mEmail.setError("Empty fields are not allowed");
            }
            else{
                mEmail.setError("Please enter correct email");
            }
        }

}