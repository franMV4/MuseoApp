package com.example.museoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.museoapp.database.AppDatabase;
import com.example.museoapp.domain.City;
import com.example.museoapp.domain.Museum;

import java.util.ArrayList;
import java.util.List;

public class NewMuseum extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static List<City> cities;
    private ArrayAdapter<City> citiesAdapter;
    private City city = new City (0, null);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_museum);


        cities = new ArrayList<>();
        ListView lvCities = findViewById(R.id.museums_list);
        citiesAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, cities);
        lvCities.setAdapter(citiesAdapter);
        lvCities.setOnItemClickListener(this);

        Button button = findViewById(R.id.new_museum);
        button.setOnClickListener(v -> openNewCity());


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


    public void addMuseum(View view) {
        EditText etName = findViewById(R.id.artwork_name);
        String name = etName.getText().toString();

        if ((city.getId() == 0) || (name.equals(""))) {
            Toast.makeText(this, getString(R.string.add_missing_data), Toast.LENGTH_SHORT).show();
        } else {
            Museum museum = new Museum(0, name, city.getId());

            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "museums").allowMainThreadQueries().build();
            db.museumDao().insert(museum);

            Toast.makeText(this, getString(R.string.museum_added), Toast.LENGTH_SHORT).show();
            etName.setText("");
        }
    }

    public void openNewCity() {
        Intent intent = new Intent(this, NewCity.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        city = cities.get(position);
    }

/*
    public void add (View view){


        EditText etName = findViewById(R.id.event_name);
        EditText etDescription = findViewById(R.id.event_description);


        if(etName.getText().toString().equals("")){
            Toast.makeText(this,"Debes escribir el nombre del del evento",
                    Toast.LENGTH_SHORT).show();
            return; }

        if(etDescription.getText().toString().equals("")){
            Toast.makeText(this,"Debes escribir la descripcion del del evento",
                    Toast.LENGTH_SHORT).show();
            return; }

        String name = etName.getText().toString();
        String description = etDescription.getText().toString();






        Event event = new Event(name,description);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"events").allowMainThreadQueries().build();
        db.eventDao().insert(event);

        Toast.makeText(this,"Evento " + name + " añadido ", Toast.LENGTH_SHORT).show();


        etName.setText("");
        etDescription.setText("");
        etName.requestFocus();
    }   */


}