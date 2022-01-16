package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;


import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.example.movies.fragment.PopularFragment;
import com.example.movies.fragment.UpcommingFragment;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    /*Nous avons essayé de réaliser les pages avec des fragments mais
    nous avions un probleme d'adapter alors nous avons tout réalisé dans la MainActivity*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setTitle("Popular Movies");

        Intent intent = getIntent();
        int debut = intent.getIntExtra("vue",0);
        Log.i("AZERTY TEST §§§§§§§§", String.valueOf(debut));


        RequestApi requestApi = new Retrofit.Builder()
                .baseUrl(RequestApi.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RequestApi.class);

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottom_navigation);


        if(debut == 0){
            requestApi.searchPopular("1864da1ce1110783fe8bb3f89f04771d").enqueue(new Callback<MoviesList>() {
                @Override
                public void onResponse(Call<MoviesList> call, Response<MoviesList> response)
                {
                    MoviesList liste = response.body();
                    Log.i("AZERTY TEST §§§§§§§§", liste.getItems().toString());
                    displaySearchedMovies(liste.getItems());
                }
                @Override
                public void onFailure(Call<MoviesList> call, Throwable t) {
                }
            });
        }else{
            setTitle("Upcoming Movies");
            requestApi.searchUpcoming("1864da1ce1110783fe8bb3f89f04771d").enqueue(new Callback<MoviesList>() {
                @Override
                public void onResponse(Call<MoviesList> call, Response<MoviesList> response)
                {
                    MoviesList liste = response.body();
                    Log.i("AZERTY TEST §§§§§§§§", liste.getItems().toString());
                    displaySearchedMovies(liste.getItems());
                }
                @Override
                public void onFailure(Call<MoviesList> call, Throwable t) {
                }
            });
        }

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.popular:
                        setTitle("Popular Movies");
                        requestApi.searchPopular("1864da1ce1110783fe8bb3f89f04771d").enqueue(new Callback<MoviesList>() {
                            @Override
                            public void onResponse(Call<MoviesList> call, Response<MoviesList> response)
                            {
                                MoviesList liste = response.body();
                                Log.i("AZERTY TEST §§§§§§§§", liste.getItems().toString());
                                displaySearchedMovies(liste.getItems());
                            }
                            @Override
                            public void onFailure(Call<MoviesList> call, Throwable t) {
                            }
                        });
                        break;
                    case R.id.rechercher:
                        Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.upcomming:
                        setTitle("Upcoming Movies");
                        requestApi.searchUpcoming("1864da1ce1110783fe8bb3f89f04771d").enqueue(new Callback<MoviesList>() {
                            @Override
                            public void onResponse(Call<MoviesList> call, Response<MoviesList> response)
                            {
                                MoviesList liste = response.body();
                                Log.i("AZERTY TEST §§§§§§§§", liste.getItems().toString());
                                displaySearchedMovies(liste.getItems());
                            }
                            @Override
                            public void onFailure(Call<MoviesList> call, Throwable t) {
                            }
                        });
                        break;
                }
                return true;
            }
        });

    }

    public void displaySearchedMovies(List<Movie> movie){
        MoviesAdapter adapter = new MoviesAdapter(movie);

        RecyclerView rvMovies = (RecyclerView) findViewById(R.id.listPopular);

        rvMovies.setAdapter(adapter);

        rvMovies.setLayoutManager(new GridLayoutManager(this, 2));
    }



}