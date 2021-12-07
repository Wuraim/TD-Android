package com.example.td6;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.ByteArrayOutputStream;
import java.util.List;

public class RepoAdapter extends RecyclerView.Adapter<RepoAdapter.ViewHolder>{

    private final List<Repo> mRepos;

    public RepoAdapter(List<Repo> mRepos) {
        this.mRepos = mRepos;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView =inflater.inflate(R.layout.activity_repo, parent, false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Repo repo = mRepos.get(position);

        TextView repoNameTextView = holder.nameRepo;
        repoNameTextView.setText(repo.getFull_name());
    }

    @Override
    public int getItemCount() {
        return mRepos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView nameRepo;

        public ViewHolder(View itemView){
            super(itemView);
            nameRepo = (TextView) itemView.findViewById(R.id.nameRepo);
        }
    }
}
