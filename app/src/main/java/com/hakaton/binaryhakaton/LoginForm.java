package com.hakaton.binaryhakaton;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class LoginForm extends AppCompatActivity {

    TextView register_button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);
        register_button= (TextView) findViewById(R.id.register_button);

        register_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //binding.loginLayout.setVisibility(View.GONE);//Cover u slucaju da radi na visible/hide
                //binding.odabirLayout.setVisibility(View.VISIBLE);//Cover u slucaju da radi na visible/hide
                Intent intent = new Intent(LoginForm.this,RegisterForm.class);
                startActivity(intent);
            }
        });
    }
}