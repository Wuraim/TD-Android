package com.example.movies.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.movies.Movie;
import com.example.movies.MoviesAdapter;
import com.example.movies.MoviesList;
import com.example.movies.R;
import com.example.movies.RequestApi;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PopularFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PopularFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public PopularFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment PopularFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static PopularFragment newInstance(String param1, String param2) {
        PopularFragment fragment = new PopularFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        RequestApi requestApi = new Retrofit.Builder()
                .baseUrl(RequestApi.ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(RequestApi.class);

        requestApi.searchPopular("1864da1ce1110783fe8bb3f89f04771d").enqueue(new Callback<MoviesList>() {
            @Override
            public void onResponse(Call<MoviesList> call, Response<MoviesList> response)
            {
                MoviesList liste = response.body();
                Log.i("AZERTY TEST §§§§§§§§", liste.getItems().toString());
                MoviesAdapter adapter = new MoviesAdapter(liste.getItems());
                View view = inflater.inflate(R.layout.fragment_popular, container, false);
                RecyclerView rvMovies = (RecyclerView) view.findViewById(R.id.listPopular);
                rvMovies.setAdapter(adapter);
                rvMovies.setLayoutManager(new LinearLayoutManager(getContext()));
            }
            @Override
            public void onFailure(Call<MoviesList> call, Throwable t) {
            }
        });
        return inflater.inflate(R.layout.fragment_popular, container, false);
    }
}