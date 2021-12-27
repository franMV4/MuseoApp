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
import com.example.museoapp.domain.Artwork;
import com.example.museoapp.domain.Museum;

import java.util.ArrayList;
import java.util.List;

public class NewArtWork extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public static List<Museum> museums;
    private ArrayAdapter<Museum> museumsAdapter;
    private Museum museum = new Museum (0, null, 0, 0, 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_art_work);

        museums = new ArrayList<>();
        ListView lvMuseums = findViewById(R.id.museums_list);
        museumsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, museums);
        lvMuseums.setAdapter(museumsAdapter);
        lvMuseums.setOnItemClickListener(this);

        Button button = findViewById(R.id.new_museum);
        button.setOnClickListener(v -> openNewMuseum());

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadMuseums();
    }
    private void loadMuseums(){
        museums.clear();
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"museums").allowMainThreadQueries().
                fallbackToDestructiveMigration().build();

        museums.addAll(db.museumDao().getAll());

    }



    public void add (View view){


        EditText etName = findViewById(R.id.artwork_name);
        String name = etName.getText().toString();
        EditText etAuthor = findViewById(R.id.artwork_author);
        String author = etAuthor.getText().toString();


        if ((museum.getId() == 0) || (name.equals("")) || (author.equals(""))) {
            Toast.makeText(this, getString(R.string.add_missing_data), Toast.LENGTH_SHORT).show();
        } else {
            Artwork artwork = new Artwork(name, author, museum.getId());

            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "artworks").allowMainThreadQueries().build();
            db.artworkDao().insert(artwork);

            Toast.makeText(this, getString(R.string.artwork_added), Toast.LENGTH_SHORT).show();
            etName.setText("");
            etAuthor.setText("");


        }

    }

    public void openNewMuseum() {
        Intent intent = new Intent(this, NewMuseum.class);
        startActivity(intent);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        museum = museums.get(position);
    }


}