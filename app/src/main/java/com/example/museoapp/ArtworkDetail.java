package com.example.museoapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.museoapp.database.AppDatabase;
import com.example.museoapp.domain.Artwork;
import com.example.museoapp.util.ImageUtils;

import java.util.List;

public class ArtworkDetail extends AppCompatActivity {

    public List<Artwork> artworks;
    private Artwork artwork = new Artwork (0, null, null, null, 0);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork_detail);


        Intent intent = getIntent();

        String name = intent.getStringExtra("name");
        String author = intent.getStringExtra("author");



        TextView etAuthorDetailName = findViewById(R.id.tvArtworkNameDetail);
        etAuthorDetailName.setText(name);

        TextView etAuthorDetailAuthor = findViewById(R.id.tvArtworkDescriptionDetail);
        etAuthorDetailAuthor.setText(author);


    }


}