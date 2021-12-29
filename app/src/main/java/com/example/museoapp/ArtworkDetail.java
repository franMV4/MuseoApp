package com.example.museoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.museoapp.util.ImageUtils;

public class ArtworkDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork_detail);


        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String author = intent.getStringExtra("author");
        String museumId = intent.getStringExtra("museumId");





        TextView etAuthorDetailName = findViewById(R.id.tvArtworkNameDetail);
        etAuthorDetailName.setText(name);

        TextView etAuthorDetailAuthor = findViewById(R.id.tvArtworkDescriptionDetail);
        etAuthorDetailAuthor.setText(author);

        TextView etAuthorDetailMuseum = findViewById(R.id.tvArtworkMuseumDetail);
        etAuthorDetailMuseum.setText(museumId);





    }
}