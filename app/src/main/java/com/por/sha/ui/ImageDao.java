package com.por.sha.ui;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by portia on 26/11/2017.
 */

@Dao
public interface ImageDao {
    @Query("SELECT * FROM ImageData")
    List<ImageData> getAll();

    @Insert
    void insertAll(List<ImageData> data);

    @Delete
    void delete(ImageData user);
}
