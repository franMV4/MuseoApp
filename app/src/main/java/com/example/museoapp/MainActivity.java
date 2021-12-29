package com.example.museoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


   public void NewArtworkActivity(View view){
     Intent new_artwork_activity= new Intent(this, NewArtWork.class);
     startActivity(new_artwork_activity); }

    public void NewCityActivity(View view){
        Intent new_city_activity= new Intent(this, NewCity.class);
        startActivity(new_city_activity); }


    public void NewMuseumActivity(View view){
        Intent new_museum_activity= new Intent(this, NewMuseum.class);
        startActivity(new_museum_activity); }

    public void ShowCitiesActivity(View view){
        Intent show_cities_activity= new Intent(this, ShowCity.class);
        startActivity(show_cities_activity); }

    public void ShowMuseumsActivity(View view){
        Intent show_museums_activity= new Intent(this, ShowMuseum.class);
        startActivity(show_museums_activity); }

    public void ShowArtworksActivity(View view){
        Intent show_artworks_activity= new Intent(this, ShowArtwork.class);
        startActivity(show_artworks_activity); }

    public void GoogleMapActivity(View view){
        Intent google_map_activity= new Intent(this, MapaGoogle.class);
        startActivity(google_map_activity); }
}