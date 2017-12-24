package com.por.sha.ui;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.net.Uri;
import android.provider.MediaStore;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by portia on 19/11/2017.
 */

public class ImageUtils {

    public List<ImageData> getAllmagesPath(Context context) {
        Uri uri;
        Cursor cursor;
        int column_index_data, column_index_folder_name;
        ArrayList<ImageData> listOfAllImages = new ArrayList<>();
        String absolutePathOfImage = null;
        uri = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI;

        String[] projection = { MediaStore.MediaColumns.DATA,
                MediaStore.Images.Media.BUCKET_DISPLAY_NAME };

        cursor = context.getContentResolver().query(uri, projection, null,
                null, null);

        column_index_data = cursor.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA);
        column_index_folder_name = cursor
                .getColumnIndexOrThrow(MediaStore.Images.Media.BUCKET_DISPLAY_NAME);
        int count = 0;
        while (cursor.moveToNext()) {
            absolutePathOfImage = cursor.getString(column_index_data);
            ImageData id = new ImageData();
            id.setKey(count);
            id.setPath(absolutePathOfImage);
            listOfAllImages.add(id);
            count++;
        }
        cursor.close();
        return listOfAllImages;
    }

    private ImageDatabase getDatabase(Context context) {
        return Room.databaseBuilder(context,
                ImageDatabase.class, "test-database").build();
    }

    public void saveAllImageData(Context context, List<ImageData> data) {
        ImageDatabase db = getDatabase(context);
        try {
            db.imageDao().insertAll(data);
            db.close();
        } catch (SQLiteConstraintException ex) {
        } finally {
            db.close();
        }
    }
}
