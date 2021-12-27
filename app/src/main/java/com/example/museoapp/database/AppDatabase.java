package com.example.museoapp.database;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.museoapp.dao.ArtworkDao;
import com.example.museoapp.dao.CityDao;
import com.example.museoapp.dao.MuseumDao;
import com.example.museoapp.domain.Artwork;
import com.example.museoapp.domain.City;
import com.example.museoapp.domain.Museum;


@Database(entities = {Artwork.class, Museum.class, City.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ArtworkDao artworkDao();
    public abstract MuseumDao museumDao();
    public abstract CityDao cityDao();

}