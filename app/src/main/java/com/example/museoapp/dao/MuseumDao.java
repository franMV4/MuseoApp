package com.example.museoapp.dao;


import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;


import com.example.museoapp.domain.Museum;

import java.util.List;

@Dao
public interface MuseumDao {
    @Query("SELECT * FROM museum")
    List<Museum> getAll();

    @Query("SELECT * FROM museum WHERE cityId = :idCity")
    List<Museum> getMuseumByCity(int idCity);

    @Insert
    void insert(Museum museum);
}
