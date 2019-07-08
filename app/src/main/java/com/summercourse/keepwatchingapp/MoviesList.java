package com.summercourse.keepwatchingapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.util.ArrayList;

import com.summercourse.keepwatchingapp.R;
import model.Movies;
import rest.ApiClientMovies;
import rest.ApiInterfaceMovies;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MoviesList extends AppCompatActivity {



    public ArrayList<Movies> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movies_list);


/*
        editt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString());

            }
        });


*/
        ApiInterfaceMovies apiService = ApiClientMovies.getClient().create(ApiInterfaceMovies.class);

        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                DividerItemDecoration.VERTICAL));


        Call<ArrayList<Movies>> call = apiService.getTopRatedMovies();
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        // ON CLICK ITEAM HANDLE
                        Intent movieDetails = new Intent(getApplicationContext(), MovieDetails.class);
                        movieDetails.putExtra("MOVIE_NUMBER", position);
                        startActivity(movieDetails);
//                        Toast.makeText(getApplicationContext(),"Hello",Toast.LENGTH_LONG).show();
                    }
                })
        );
        progressDialog.show();
        call.enqueue(new Callback<ArrayList<Movies>>() {

            @Override
            public void onResponse(Call<ArrayList<Movies>> call, Response<ArrayList<Movies>> response) {
                 movies = response.body();


                if (response.isSuccessful()) {

                    recyclerView.setAdapter(new MoviesAdapter(movies, R.layout.movie_cell, getApplicationContext()));
                    progressDialog.dismiss();

                    //Toast.makeText(getApplicationContext(), "Number : " + movies.size(), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Failed To Connect Server.. " + response.code(), Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onFailure(Call<ArrayList<Movies>> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Unable To Connect..", Toast.LENGTH_LONG).show();
            }
        });




    }
/*
    private void filter(String text){
        ArrayList<Movies> ha=new ArrayList<>();

        for (Movies m:movies){
            if(m.getTitle().toLowerCase().contains(text.toLowerCase())){
               ha.add(m);
               final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle);
               recyclerView.setAdapter(new MoviesAdapter(ha, R.layout.movie_cell, getApplicationContext()));
            }

        }

    }

*/








    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
        final MenuItem item=menu.findItem(R.id.menueSearch);
        final SearchView searchView=(SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {


                ArrayList<Movies> ha=new ArrayList<>();

                for (Movies m:movies) {
                    if (m.getTitle().toLowerCase().contains(s.toLowerCase())) {
                        ha.add(m);
                        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recycle);
                        recyclerView.setLayoutManager(new LinearLayoutManager(MoviesList.this));
                        recyclerView.addItemDecoration(new DividerItemDecoration(getApplicationContext(),
                                DividerItemDecoration.VERTICAL));
                        recyclerView.setAdapter(new MoviesAdapter(ha, R.layout.movie_cell, getApplicationContext()));
                    }
                }


                    return false;
            }
        });

        return super.onCreateOptionsMenu(menu);

    }

}
