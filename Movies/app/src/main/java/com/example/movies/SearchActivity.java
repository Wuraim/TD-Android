package com.example.movies;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SearchActivity extends AppCompatActivity {

    static final String donnee="Ma donnee";
    EditText Edit1;
    Button button1;
    String chaine;
    String vue = "choix vue" ;
    int debut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle("Search Movies");

        BottomNavigationView bottomNavigationView;
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        button1 = ((Button)this.findViewById(R.id.rechercher));
        Edit1 = ((EditText)this.findViewById(R.id.chercher));
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chaine = Edit1.getText().toString();
                Intent intent = new Intent(SearchActivity.this,ResultActivity.class);
                if(chaine != null){
                    intent.putExtra(donnee, chaine);
                    startActivity(intent);
                }
            }
        });

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                Intent intent = new Intent(SearchActivity.this,MainActivity.class);
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
                }
                return true;
            }
        });
    }
}