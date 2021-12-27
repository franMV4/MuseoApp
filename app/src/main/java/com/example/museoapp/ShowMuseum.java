package com.example.museoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.museoapp.database.AppDatabase;
import com.example.museoapp.domain.Museum;

import java.util.ArrayList;
import java.util.List;

public class ShowMuseum extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public List<Museum> museums;
    public static ArrayAdapter<Museum> museumsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_museums);


        museums = new ArrayList<>();

        ListView lvMuseums = findViewById(R.id.showArtworks_list);
        museumsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, museums);
        lvMuseums.setAdapter(museumsAdapter);

        lvMuseums.setOnItemClickListener((AdapterView.OnItemClickListener) this);

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Museum museum = museums.get(position);
        Intent intent = new Intent(this, MuseumDetail.class);
        intent.putExtra("name", museum.getName());
        intent.putExtra("cityId", museum.getCityId());
        startActivity(intent);
    }



    @Override
    protected void onResume() {
        super.onResume();
        loadMuseums();
        museumsAdapter.notifyDataSetChanged();
    }

    private void loadMuseums(){
        museums.clear();
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"museums").allowMainThreadQueries().
                fallbackToDestructiveMigration().build();

        museums.addAll(db.museumDao().getAll());

    }

}