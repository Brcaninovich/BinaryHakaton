package com.hakaton.binaryhakaton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.hakaton.binaryhakaton.databinding.ActivityRegisterFormBinding;

import java.util.ArrayList;

public class RegisterForm extends AppCompatActivity {

    private ActivityRegisterFormBinding binding;
    private FirebaseAuth mAuth;
    private DatabaseReference mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRegisterFormBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        mAuth = FirebaseAuth.getInstance();

        mDatabase = FirebaseDatabase.getInstance().getReference("Users");

        binding.registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

    }

    private void register(){
        String username = binding.usernameInput.getText().toString();
        String email = binding.emailInput.getText().toString().trim();
        String password = binding.passwordInput.getText().toString().trim();
        ArrayList<String> poruke = new ArrayList<String>();
        ArrayList<String> favorites = new ArrayList<String>();
        ArrayList<String> moji_dodani = new ArrayList<String>();

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        //Toast.makeText(LoginActivity.this, "Radi valjda login", Toast.LENGTH_SHORT).show();
                        User user = new User(
                                username,
                                email,
                                "user",
                                poruke,
                                favorites,
                                moji_dodani
                        );

                        mDatabase.child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if(task.isSuccessful()){
                                            //Toast.makeText(RegisterForm.this, "Radi...", Toast.LENGTH_SHORT).show();
                                        }else{
                                            //Toast.makeText(RegisterForm.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                });
                        mAuth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {

                            }
                        });
                        Intent intent = new Intent(RegisterForm.this, GlavniMenu.class);
                        startActivity(intent);
                        BazaHolder.rebase();
                        finish();

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterForm.this, "Ne radi", Toast.LENGTH_SHORT).show();
                    }
                });
    }

}