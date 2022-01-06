package com.example.museoapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.museoapp.database.AppDatabase;
import com.example.museoapp.domain.Artwork;
import com.example.museoapp.domain.Museum;

import com.example.museoapp.util.ImageUtils;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class NewArtWork extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private ImageView imageView;
    public static List<Museum> museums;
    private ArrayAdapter<Museum> museumsAdapter;
    private Museum museum = new Museum (0, null, 0);
   private int SELECT_PICTURE_RESULT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_art_work);

        museums = new ArrayList<>();
        ListView lvMuseums = findViewById(R.id.museums_list);
        museumsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, museums);
        lvMuseums.setAdapter(museumsAdapter);
        lvMuseums.setOnItemClickListener(this);
        imageView = findViewById(R.id.artworkImageView);

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
        ImageView artworkImageView = findViewById(R.id.artworkImageView);
        byte[] artworkImage = ImageUtils.fromImageViewToByteArray(artworkImageView);


        if ((museum.getId() == 0) || (name.equals("")) || (author.equals(""))) {
            Toast.makeText(this, getString(R.string.add_missing_data), Toast.LENGTH_SHORT).show();
        } else {
            Artwork artwork = new Artwork(0, name, author,artworkImage, museum.getId());

            AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                    AppDatabase.class, "artworks").allowMainThreadQueries().build();
            db.artworkDao().insert(artwork);

            Toast.makeText(this, getString(R.string.artwork_added), Toast.LENGTH_SHORT).show();
            etName.setText("");
            etAuthor.setText("");
        }

    }


    public void selectPicture(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, SELECT_PICTURE_RESULT);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if ((requestCode == SELECT_PICTURE_RESULT) && (resultCode == RESULT_OK)
                && (data != null)) {
            Picasso.get().load(data.getData()).noPlaceholder().centerCrop().fit()
                    .into((ImageView) findViewById(R.id.artworkImageView));

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