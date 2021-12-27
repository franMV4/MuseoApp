package com.example.museoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.museoapp.database.AppDatabase;
import com.example.museoapp.domain.City;

import java.util.ArrayList;
import java.util.List;

public class ShowCity extends AppCompatActivity {

    public List<City> cities;
    public static ArrayAdapter<City> citiesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_city);


    cities = new ArrayList<>();

    ListView lvEvents = findViewById(R.id.showArtworks_list);
    citiesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
        lvEvents.setAdapter(citiesAdapter);


}


    @Override
    protected void onResume() {
        super.onResume();
        loadCities();

    }

    private void loadCities(){
        cities.clear();
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"cities").allowMainThreadQueries().
                fallbackToDestructiveMigration().build();

        cities.addAll(db.cityDao().getAll());

    }




}