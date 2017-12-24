package com.por.sha.ui;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.content.Context;
import android.os.AsyncTask;

import java.util.List;

/**
 *
 * Created by portia on 19/11/2017.
 */

public class ImagesViewModel extends ViewModel {
    private MutableLiveData<List<ImageData>> imageData;

    public LiveData<List<ImageData>> getUsers(Context context) {
        if (imageData == null) {
            imageData = new MutableLiveData<>();
            loadImageData(context);
        }
        return imageData;
    }

    private void loadImageData(Context context) {
        //new ImageDataLoader(context).run();
        new ImageDataLoaderTask(context, new ImageDataLoaderInterface() {
            @Override
            public void onCompletion(List<ImageData> data) {
                imageData.setValue(data);
            }
        }).execute();
    }


    interface ImageDataLoaderInterface {
        void onCompletion(List<ImageData> data);
    }


    private class ImageDataLoaderTask extends AsyncTask<String, Integer, List<ImageData>> {

        Context context;
        ImageDataLoaderInterface callbacks;

        ImageDataLoaderTask(Context context, ImageDataLoaderInterface callbacks) {
            this.context = context;
            this.callbacks = callbacks;
        }


        protected List<ImageData> doInBackground(String... urls) {

            return new ImageUtils().getAllmagesPath(context);
        }

        protected void onProgressUpdate(Integer... progress) {

        }

        protected void onPostExecute(List<ImageData> data) {
            callbacks.onCompletion(data);
        }
    }


//    class ImageDataLoader implements Runnable {
//        Context context;
//        ImageDataLoader (Context context) {
//            this.context = context;
//        }
//
//        @Override
//        public void run() {
//           // imageData.setValue(new ImageUtils().getAllmagesPath(context));
//        }
//    }
}
