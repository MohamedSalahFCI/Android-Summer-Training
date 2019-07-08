package com.summercourse.keepwatchingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class SignUp extends Activity implements View.OnClickListener {

    EditText name;
    EditText password;
    Button reg;
    TextView gologin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        name = (EditText) findViewById(R.id.enternam);
        password = (EditText) findViewById(R.id.enterpass);
        reg = (Button) findViewById(R.id.SignUpNowb);
        gologin = (TextView) findViewById(R.id.SignUpSignIn);
        reg.setOnClickListener(this);
        gologin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        // dh taree2a gdeda l ktabt l setonclick bta3t l zrayr
        //hna bygeb l id aly 3aml amr l click
        //lw l id dh =btnreg bta3 l zorar f hwa hynadi 3la function asmha register
        //tyb lw gay mn l textview aly l pass bta3o tvlogin hayft7lo saf7t l login
        switch (v.getId()) {
            case R.id.SignUpNowb:
                register();
                break;
            case R.id.SignUpSignIn:
                startActivity(new Intent(SignUp.this, LogIn.class));
                finish();
                break;
            default:

        }
    }

    private void register() {
        String email = name.getText().toString();
        String pass = password.getText().toString();
        DbHelper db = null;
        db = new DbHelper(getApplicationContext());
        if (email.isEmpty() || pass.isEmpty()) {
            displayToast("Username/password field empty");
        } else {
            //dh l method aly f class l db aly bt3mal add l user gded
            db.addUser(email, pass);
            displayToast("User registered");
            finish();
        }
    }

    private void displayToast(String message) {
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

}