package com.example.museoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MuseumDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_museum_detail);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String city = intent.getStringExtra("cityId");

        TextView etEventDetailName = findViewById(R.id.tvNameDetail);
        etEventDetailName.setText(name);

        TextView etEventDetailDescription = findViewById(R.id.tvDescriptionDetail);
        etEventDetailDescription.setText(city);
    }
}