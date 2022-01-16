package com.example.movies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.ViewHolder> {
    private final List<Movie> mMovies;

    public MoviesAdapter(List<Movie> mMovies) {
        this.mMovies = mMovies;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView =inflater.inflate(R.layout.activity_movie, parent, false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Movie movie = mMovies.get(position);

        TextView movieNameTextView = holder.nameMovie;
        TextView movieOverviewTextView = holder.overviewMovie;
        movieNameTextView.setText(movie.getOriginal_title());

        holder.imageMovie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(),MovieDetails.class);
                intent.putExtra("id", movie.getId());
                v.getContext().startActivity(intent);
            }
        });

        Glide.with(holder.imageMovie).load("https://image.tmdb.org/t/p/w500"+movie.getPoster_path()).apply(
                new RequestOptions().override(200, 600)).into(holder.imageMovie);

    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nameMovie;
        public TextView overviewMovie;
        public ImageView imageMovie;

        public ViewHolder(View itemView){
            super(itemView);
            imageMovie = (ImageView) itemView.findViewById(R.id.imageMovie);
            nameMovie = (TextView) itemView.findViewById(R.id.nameMovie);
        }
    }


}
