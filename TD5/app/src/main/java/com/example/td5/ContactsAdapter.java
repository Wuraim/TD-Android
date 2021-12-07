package com.example.td5;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.ViewHolder>{

    private final List<Contact> mContacts;

    public ContactsAdapter(List<Contact> contacts){
        mContacts = contacts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View contactView =inflater.inflate(R.layout.activity_contact, parent, false);

        return new ViewHolder(contactView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Contact contact = mContacts.get(position);

        TextView idTextView = holder.idTextView;
        idTextView.setText("#"+contact.getmId());

        TextView lastNameTextView = holder.lastNameTextView;
        lastNameTextView.setText(contact.getmLastName());

        ImageView imageImageView = holder.imageImageView;
        Glide.with(holder.imageImageView).load(contact.getmImageUrl()).into(imageImageView);
    }

    @Override
    public int getItemCount() {
        return mContacts.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView idTextView;
        public TextView lastNameTextView;
        public ImageView imageImageView;

        public ViewHolder(View itemView){
            super(itemView);
            idTextView = (TextView) itemView.findViewById(R.id.id);
            lastNameTextView = (TextView) itemView.findViewById(R.id.nom);
            imageImageView = (ImageView) itemView.findViewById(R.id.image);
        }
    }
}
