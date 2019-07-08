package com.summercourse.keepwatchingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class LogIn extends Activity implements View.OnClickListener {
    EditText nameoflogin ,passwordoflogin;
    Button checklogin , gotoregester;
    private DbHelper db;

    //private Session session;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        db = new DbHelper(this);
        // session = new Session(this);
        checklogin = (Button)findViewById(R.id.LogInNow);
        gotoregester = (Button)findViewById(R.id.goregs);
        nameoflogin = (EditText)findViewById(R.id.LogInName);
        passwordoflogin = (EditText)findViewById(R.id.LogInpass);
        checklogin.setOnClickListener(this);
        gotoregester.setOnClickListener(this);
/*
        if(session.loggedin()){
            startActivity(new Intent(LoginActivity.this,MainActivity.class));
            finish();
        }
        //mesh fahm dh bt3mal eh
        */
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.LogInNow:
                login();
                break;
            case R.id.goregs:
                startActivity(new Intent(LogIn.this,SignUp.class));
                break;
            default:

        }
    }

    private void login(){
        String email = nameoflogin.getText().toString();
        String pass = passwordoflogin.getText().toString();

        if(db.getUser(email,pass)){
            //session.setLoggedin(true);
            Intent intent=new Intent(LogIn.this,Home.class);
            intent.putExtra("name",email);
            startActivity(intent);
            //startActivity(new Intent(LogIn.this, Home.class));
            Toast.makeText(this, "Login success", Toast.LENGTH_SHORT).show();
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "Wrong email/password",Toast.LENGTH_SHORT).show();
        }
    }


}
