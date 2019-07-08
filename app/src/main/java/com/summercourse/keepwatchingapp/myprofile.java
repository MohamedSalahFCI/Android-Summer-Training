package com.summercourse.keepwatchingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class myprofile extends AppCompatActivity {

   public TextView t1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_myprofile);
         t1=(TextView)findViewById(R.id.myprofilestring);
        Intent c=getIntent();
        String finalname=c.getExtras().getString("usernameprofile");
        t1.setText("User Name :-"+" "+finalname);
    }
}
