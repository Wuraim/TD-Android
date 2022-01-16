package com.example.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ResultActivity extends AppCompatActivity {

    String vue = "choix vue" ;
    int debut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        setTitle("Result Search");
        Intent intent = getIntent();
        setContentView(R.layout.activity_result);
        String search = intent.getStringExtra(SearchActivity.donnee);

        RequestApi requestApi = new Retrofit.Builder()
                .baseUrl(RequestApi.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RequestApi.class);

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = new Intent(ResultActivity.this,MainActivity.class);
                switch (item.getItemId()) {
                    case R.id.popular:
                        debut=0;
                        intent.putExtra("vue", debut);
                        startActivity(intent);
                        break;
                    case R.id.upcomming:
                        debut=1;
                        intent.putExtra("vue", debut);
                        startActivity(intent);
                        break;
                    case R.id.rechercher:
                        Intent intent2 = new Intent(ResultActivity.this,SearchActivity.class);
                        startActivity(intent2);
                        break;
                }
                return true;
            }
        });

        requestApi.searchMovie("1864da1ce1110783fe8bb3f89f04771d", ""+search).enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response)
            {
                MoviesList liste = response.body();
                /*Log.i("AZERTY TEST §§§§§§§§", liste.getItems().toString());*/
                displaySearchedMovies(liste.getItems());
            }
            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
            }
        });
    }

    public void displaySearchedMovies(List<Movie> movie){
        MoviesAdapter adapter = new MoviesAdapter(movie);

        RecyclerView rvMovies = (RecyclerView) findViewById(R.id.listResult);

        rvMovies.setAdapter(adapter);

        rvMovies.setLayoutManager(new GridLayoutManager(this, 2));
    }
}