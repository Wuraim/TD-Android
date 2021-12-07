package com.example.td5;

public class Contact {
    String id;
    String nom;
    String imageUrl;

    private String getSpecialRUrl(String id){
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/"+id+".png";
    }

    public Contact(String id, String nom){
        this.id = id;
        this.nom = nom;
        this.imageUrl = getSpecialRUrl(id);
    }

    public String getmLastName() {
        return this.nom;
    }

    public String getmId() {
        return this.id;
    }

    public String getmImageUrl() {
        return imageUrl;
    }


}
