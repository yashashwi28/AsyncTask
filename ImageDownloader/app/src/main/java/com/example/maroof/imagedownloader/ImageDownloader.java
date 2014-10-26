package com.example.maroof.imagedownloader;



import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;

/**
 * Created by maroof on 10/22/2014.
 */
public class ImageDownloader extends AsyncTask<String, Integer, Bitmap> {

    private Activity activity;

    public ImageDownloader(FragmentActivity myActivity) {
        activity = myActivity;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Log.i("hello1", "hello1");
        publishProgress(1);
        try {
            URL url = new URL(params[0]);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            if (con.getResponseCode() != HttpURLConnection.HTTP_OK) {
                throw new Exception("Failed to connect");
            }
            InputStream is = con.getInputStream();
            publishProgress(0);
            is.close();
            return BitmapFactory.decodeStream(is);
        } catch (Exception e) {
            Log.e("Image", "Failed to load image", e);
            Log.e("error", e.getMessage());
        }
        return null;
    }



    @Override
    protected void onPostExecute(Bitmap img) {
        Log.i("hello4", "hello4");
        ImageView iv = (ImageView) activity.findViewById(R.id.remote_image);
        Log.i("hello5", "hello5");
        if (iv != null && img != null) {
            Log.i("hello6", "hello6");
            iv.setImageBitmap(img);
        }
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        TextView tv = (TextView) activity.findViewById(R.id.tv_loading);
        if (values[0] == 1) {
            tv.setText("Loading...");
        } else {
            tv.setText("");
        }
    }


}
