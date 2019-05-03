package com.cdac.myfinalapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    EditText editemail;
    EditText editpass;
    EditText edituser;
    Signuphelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        editpass=findViewById(R.id.signpass);
        editemail=findViewById(R.id.signemail);
        edituser=findViewById(R.id.signeuser);
        helper=new Signuphelper(this);
    }

    public void submitdata(View view) {
        String email,password,username;
        email=editemail.getText().toString();
        password=editpass.getText().toString();
        username=edituser.getText().toString();
        helper.addUser(username,email,password);
        Toast.makeText(getApplicationContext(),"signup successfully", Toast.LENGTH_LONG).show();
        startActivity(new Intent(SignupActivity.this,MainActivity.class));
        finish();

    }
}
