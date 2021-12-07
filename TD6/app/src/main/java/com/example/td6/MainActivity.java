package com.example.td6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    static final String donnee="Ma donnee";
    String defaut = "no";
    EditText Edit1;
    Button button1;
    String chaine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GithubService githubService = new Retrofit.Builder()
                .baseUrl(GithubService.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(GithubService.class);

        button1 = ((Button)this.findViewById(R.id.rechercher));
        Edit1 = ((EditText)this.findViewById(R.id.chercher));
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chaine = Edit1.getText().toString();
                Intent intent = new Intent(MainActivity.this,SearchActivity.class);
                if(chaine != null){
                    intent.putExtra(donnee, chaine);
                    startActivity(intent);
                }
            }
        });

        githubService.listRepos("adrienbusin").enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response)
            {
                afficherRepos(response.body());
            }
            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
            }
        });

    }
    public void afficherRepos(List<Repo> repos) {
        Toast.makeText(this,"nombre de dépots : "+repos.size(),
                Toast.LENGTH_SHORT).show();
    }
}
