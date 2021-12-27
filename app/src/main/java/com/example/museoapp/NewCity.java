package com.example.museoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.museoapp.database.AppDatabase;
import com.example.museoapp.domain.City;

public class NewCity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_city);
    }


    public void add (View view){

        EditText etName = findViewById(R.id.city_name);

        if(etName.getText().toString().equals("")){
            Toast.makeText(this,"Debes escribir el nombre de la obra de arte",
            Toast.LENGTH_SHORT).show();
            return; }

        String name = etName.getText().toString();

        City city = new City(0, name);

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"cities").allowMainThreadQueries().build();
        db.cityDao().insert(city);

        Toast.makeText(this,"Obra de arte " + name + " añadida ", Toast.LENGTH_SHORT).show();


        etName.setText("");


        etName.requestFocus();
    }










}