package com.transposesolutions.imagedownload;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutionException;


public class ImageUrl extends AppCompatActivity {
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_url);
        imageView = findViewById(R.id.imageK);
    }

    public void OnButton(View view) {
        ImageDownload myTask = new ImageDownload();
        Bitmap bitmapImage;
        try {
            bitmapImage = myTask.execute("https://freepngimg.com/thumb/cartoon/3-2-cartoon-free-png-image.png").get();
            imageView.setImageBitmap(bitmapImage);

        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
class ImageDownload extends AsyncTask<String,Void, Bitmap>{

    @Override
    protected Bitmap doInBackground(String... strings) {

        try {
            URL url = new URL(strings[0]);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.connect();
            InputStream inputStream = httpURLConnection.getInputStream();
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            return bitmap;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}



    //class ImageDownloader extends AsyncTask<String,Void, Bitmap>{
//
//    @Override
//    protected Bitmap doInBackground(String... strings) {
//        URL url =
//
//        return null;
//    }
//}
//class ImageDownload extends AsyncTask<String,Void, Bitmap> {
//
//
//    @Override
//    protected Bitmap doInBackground(String... strings) {
//        try {
//            URL url = new URL(strings[0]);
//            HttpURLConnection connection = (HttpURLConnection)url.openConnection();
//            connection.connect();
//            InputStream inputStream = connection.getInputStream();
//            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//            return bitmap;
//
//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        return null;
//    }
//}
//    class DownloadImage extends AsyncTask<String, Void, Bitmap> {
//
//        @Override
//        protected Bitmap doInBackground(String... strings) {
//            try {
//                URL url = new URL(strings[0]);
//                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//                connection.connect();
//                InputStream inputStream = connection.getInputStream();
//                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
//                return bitmap;
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            return null;
//        }
//    }
//}