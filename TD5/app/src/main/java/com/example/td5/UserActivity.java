package com.example.td5;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class UserActivity extends AppCompatActivity {

    List<Contact> contacts = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        RecyclerView rvContacts = (RecyclerView) findViewById(R.id.rvContacts);

        contacts.add(new Contact("395","Pingoléon"));
        contacts.add(new Contact("150","Mewtwo"));
        contacts.add(new Contact("493","Arceus"));
        contacts.add(new Contact("392","Simiabraz"));
        contacts.add(new Contact("389","Torterra"));
        contacts.add(new Contact("263","Zigzaton"));
        contacts.add(new Contact("264","Linéon"));
        contacts.add(new Contact("6","Dracaufeu"));
        contacts.add(new Contact("3","Florizare"));
        contacts.add(new Contact("9","Tortank"));

        ContactsAdapter adapter = new ContactsAdapter(contacts);

        rvContacts.setAdapter(adapter);

        rvContacts.setLayoutManager(new LinearLayoutManager(this));
    }
}