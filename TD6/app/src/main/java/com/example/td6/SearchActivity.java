package com.example.td6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        setContentView(R.layout.activity_search);
        String search = intent.getStringExtra(MainActivity.donnee);

        GithubService githubService = new Retrofit.Builder()
                .baseUrl(GithubService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubService.class);

        githubService.searchRepos(""+search).enqueue(new Callback<RepoList>() {
            @Override
            public void onResponse(Call<RepoList> call, Response<RepoList> response)
            {
                RepoList liste = response.body();
                Log.i("azerty", liste.getItems().toString());
                displaySearchedRepo(liste.getItems());
            }
            @Override
            public void onFailure(Call<RepoList> call, Throwable t) {
            }
        });
    }

    public void displaySearchedRepo(List<Repo> repos){
        RepoAdapter adapter = new RepoAdapter(repos);

        RecyclerView rvRepos = (RecyclerView) findViewById(R.id.list);

        rvRepos.setAdapter(adapter);

        rvRepos.setLayoutManager(new LinearLayoutManager(this));
    }

}