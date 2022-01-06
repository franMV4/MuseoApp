package com.example.museoapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.museoapp.database.AppDatabase;
import com.example.museoapp.domain.Artwork;
import com.example.museoapp.domain.City;

import java.util.ArrayList;
import java.util.List;

public class ShowArtwork extends AppCompatActivity implements AdapterView.OnItemClickListener {

    public List<Artwork> artworks;
    public static ArrayAdapter<Artwork> artworksAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_artwork);



        artworks = new ArrayList<>();

        ListView lvArtworks = findViewById(R.id.showArtworks_list);
        artworksAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, artworks);
        lvArtworks.setAdapter(artworksAdapter);

        lvArtworks.setOnItemClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        loadArtworks();

    }

    @Override
    public void onItemClick (AdapterView<?> parent, View view, int position, long id) {


       Artwork artwork = artworks.get(position);
        Intent intent = new Intent(this, ArtworkDetail.class);
        intent.putExtra("Artwork", (Parcelable) artwork);

        intent.putExtra("name", artwork.getName());
        intent.putExtra("author", artwork.getAuthor());
        intent.putExtra("museumId", artwork.getMuseumId());
        intent.putExtra("artworkImageV", artwork.getImage());


        startActivity(intent);

        /*Artwork obra = artworks.get(position);



            Intent intent = new Intent(this, ModifyArtwork.class);
            intent.putExtra("modify", 1);
            intent.putExtra("museumName", museum.getName());
            intent.putExtra("Artwork", artwork);
            startActivity(intent);

         */
    }






    private void loadArtworks(){
        artworks.clear();
        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class,"artworks").allowMainThreadQueries().
                fallbackToDestructiveMigration().build();

        artworks.addAll(db.artworkDao().getAll());

    }
/*
    @Override
    public boolean onContextItemSelected(MenuItem item) {

        AdapterView.AdapterContextMenuInfo info =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        final int itemSeleccionado = info.position;

        if (item.getItemId() == R.id.modify_context) {
            Artwork artwork = artworks.get(itemSeleccionado);
            Intent intent = new Intent(this, ModifyArtwork.class);
            intent.putExtra("modify", 1);
            intent.putExtra("museumName", museum.getName());
            intent.putExtra("Artwork", artwork);
            startActivity(intent);
        }
        if (item.getItemId() == R.id.delete_context) {
            deleteVisit(info);
        }
        return true;
    }

 */


}