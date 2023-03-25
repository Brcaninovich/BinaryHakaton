package com.hakaton.binaryhakaton;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.hakaton.binaryhakaton.databinding.ActivityMainBinding;
import com.hakaton.binaryhakaton.databinding.ActivitySupportServiceBinding;

public class SupportService extends AppCompatActivity {
    
   // ActivitySupportServiceBinding binding;

    //navigation_notifications

    private EditText emailAddress;
    private EditText message;
    private EditText subject;

    
    
    

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_support_service);

        emailAddress = findViewById(R.id.emailAddress);
        message = findViewById(R.id.message);
        subject = findViewById(R.id.subject);


        Button sendBtn = findViewById(R.id.sendBtn);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMail();
            }
        });
       // binding = ActivitySupportServiceBinding.inflate(getLayoutInflater());
        //setContentView(binding.getRoot());
        //binding.sendBtn.setOnClickListener(new View.OnClickListener() {
           // @SuppressLint("QueryPermissionsNeeded")
           // @Override
            //public void onClick(View view) {

           /*     String email = binding.emailAddress.getText().toString();
                String subject = binding.subject.getText().toString();
                String message = binding.message.getText().toString();
                
                
                String[] addresses = email.split(",");

                Intent intent = new Intent(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL,addresses);
                intent.putExtra(Intent.EXTRA_SUBJECT,subject);
                intent.putExtra(Intent.EXTRA_TEXT,message);
                
                if (intent.resolveActivity(getPackageManager())!= null){
                    startActivity(intent);
                }else {
                    Toast.makeText(SupportService.this, "No App is installed", Toast.LENGTH_SHORT).show();
                }
                
            }
        });*/
        
    }

    private void sendMail(){
        String recipientList = emailAddress.getText().toString();
        String[] recipents = recipientList.split(",");

        String subjects = subject.getText().toString();
        String mess = message.getText().toString();

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipents);
        intent.putExtra(Intent.EXTRA_SUBJECT,subjects);
        intent.putExtra(Intent.EXTRA_TEXT,mess);

        intent.setType("message/rfc882");
        startActivity(Intent.createChooser(intent,"choose an email client"));
    }


    }

