package com.example.movies;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MovieDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_movie_details);
        int movie_id = intent.getIntExtra("id",0);



        RequestApi requestApi = new Retrofit.Builder()
                .baseUrl(RequestApi.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RequestApi.class);

        requestApi.searchDescription(movie_id,"1864da1ce1110783fe8bb3f89f04771d").enqueue(new Callback<Movie>() {
            @Override
            public void onResponse(Call<Movie> call, Response<Movie> response)
            {
                Movie info = response.body();
                Log.i("AZERTY TEST §§§§§§§§", info.getOriginal_title());
                TextView nameMovie = (TextView) findViewById(R.id.nameMovie);
                nameMovie.setText(info.getOriginal_title());
                TextView descriptionMovie = (TextView) findViewById(R.id.description);
                descriptionMovie.setText(info.getOverview());
                ImageView imageMovie = (ImageView) findViewById(R.id.imageMovie);
                Glide.with(imageMovie).load("https://image.tmdb.org/t/p/w500"+info.getPoster_path()).apply(
                        new RequestOptions().override(200, 600)).into(imageMovie);
            }
            @Override
            public void onFailure(Call<Movie> call, Throwable t) {
            }
        });
    }
}