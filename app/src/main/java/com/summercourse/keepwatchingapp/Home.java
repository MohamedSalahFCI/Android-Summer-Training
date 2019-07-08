package com.summercourse.keepwatchingapp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.summercourse.keepwatchingapp.R;

public class Home extends Activity implements View.OnClickListener {
    Button logout;
    Button myMovies;
    Button abouts;
    Button myprof;
    public String username;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_main);
        Intent s=new Intent();
        s=getIntent();
        username=s.getExtras().getString("name");

        logout=(Button)findViewById(R.id.logout);
        myMovies=(Button)findViewById(R.id.mymovies);
        abouts=(Button)findViewById(R.id.about);
        myprof=(Button)findViewById(R.id.buttonmyprofile);

        logout.setOnClickListener(this);
        myMovies.setOnClickListener(this);
        abouts.setOnClickListener(this);
        myprof.setOnClickListener(this);


    }

    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.logout){
            SharedPreferences SP = getApplicationContext().getSharedPreferences("LogPref", MODE_PRIVATE);
            SharedPreferences.Editor edit = SP.edit();
            edit.putBoolean("LogKey", false);
            edit.commit();

            Intent i = new Intent(Home.this,LogIn.class);
            startActivity(i);
        }

        if(view.getId()==R.id.mymovies){
            Intent movies = new Intent(Home.this,MoviesList.class);
            startActivity(movies);
        }
        if(view.getId()==R.id.about){
            Intent about =new Intent(Home.this,About.class);
            startActivity(about);
        }
        if(view.getId()==R.id.buttonmyprofile){
            Intent k =new Intent(Home.this,myprofile.class);
            k.putExtra("usernameprofile",username);
            startActivity(k);
        }

    }

}
