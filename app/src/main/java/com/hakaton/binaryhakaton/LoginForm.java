package com.hakaton.binaryhakaton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.hakaton.binaryhakaton.databinding.ActivityLoginFormBinding;

public class LoginForm extends AppCompatActivity {

    TextView register_button;
    ActivityLoginFormBinding binding;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginFormBinding.inflate(getLayoutInflater());
        View root = binding.getRoot();
        setContentView(root);
        mAuth = FirebaseAuth.getInstance();
        register_button= (TextView) findViewById(R.id.register_button);


        binding.prijavaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.emailInput.getText().toString().trim();
                String password = binding.passwordText.getText().toString().trim();
                mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Intent intent = new Intent(LoginForm.this,GlavniMenu.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(LoginForm.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(LoginForm.this,RegisterForm.class);
                startActivity(intent);
            }
        });
    }
}