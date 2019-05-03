package com.cdac.myfinalapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText editemail;
EditText editpass;
Signuphelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editemail=findViewById(R.id.editEmail);
        editpass=findViewById(R.id.editpassword);
        helper=new Signuphelper(this);
    }

    public void Login(View view) {
        String email,password;
        email=editemail.getText().toString();
        password=editpass.getText().toString();
       boolean valid= helper.getUserwithemail(email,password);
        if(valid)
        {
            startActivity(new Intent(MainActivity.this,HomeActivity.class));
        }
        else
        {
            Toast.makeText(getApplicationContext(),"Login fail", Toast.LENGTH_LONG).show();
        }

    }

    public void signup(View view) {
        Intent intent=new Intent(MainActivity.this,SignupActivity.class);
        startActivity(intent);
        finish();
    }
}
