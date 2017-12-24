package com.por.sha.ui;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by portia on 19/11/2017.
 */

@Entity
public class ImageData {

    @PrimaryKey
    int key;

    @ColumnInfo(name = "path")
    String path;

    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

}
