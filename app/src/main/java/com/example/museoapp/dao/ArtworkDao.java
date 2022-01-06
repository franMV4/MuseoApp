package com.example.museoapp.dao;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.museoapp.domain.Artwork;

import java.util.List;

@Dao
public interface ArtworkDao {
    @Query("SELECT * FROM artwork")
    List<Artwork> getAll();

    @Query("SELECT * FROM artwork WHERE museumId = :idMuseum")
    List<Artwork> getArtworksByMuseum(int idMuseum);

    @Insert
    void insert(Artwork artwork);

    @Update
    void update(Artwork artwork);






    @Delete
    void delete(Artwork artwork);

}
