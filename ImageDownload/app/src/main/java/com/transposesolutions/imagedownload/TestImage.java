package com.transposesolutions.imagedownload;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.io.InputStream;

public class TestImage extends AppCompatActivity {
    String url = "https://freepngimg.com/thumb/cartoon/3-2-cartoon-free-png-image.png";
    ImageView image;
    Button button;
    ProgressDialog mProgressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_image);
        image = (ImageView) findViewById(R.id.image);
        button = findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadImage().execute(url);
            }
        });
    }
     class DownloadImage extends AsyncTask <String,Void,Bitmap>{

         @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressDialog = new ProgressDialog(TestImage.this);
            mProgressDialog.setTitle("Download Image Tutorial");
            mProgressDialog.setMessage("Loading...");
            mProgressDialog.setIndeterminate(false);
            mProgressDialog.show();
        }
        @Override
        protected Bitmap doInBackground(String... URL) {
            String imageURL = URL[0];
            Bitmap bitmap = null;
            try {
                // Download Image from URL
                InputStream input = new java.net.URL(imageURL).openStream();
                // Decode Bitmap
                bitmap = BitmapFactory.decodeStream(input);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return bitmap;
        }
        @Override
        protected void onPostExecute(Bitmap result) {
            // Set the bitmap into ImageView
            image.setImageBitmap(result);
            // Close progressdialog
            mProgressDialog.dismiss();
        }
    }
}
