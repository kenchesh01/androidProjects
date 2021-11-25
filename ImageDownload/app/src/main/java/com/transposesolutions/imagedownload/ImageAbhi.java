package com.transposesolutions.imagedownload;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class ImageAbhi extends AppCompatActivity {
    Button resize, centrecrop, centreinside, fit, placeholder, error, fade, callback, rotate,complexrotate;
    ImageView iv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_abhi);
        resize = findViewById(R.id.resize);
        centrecrop = findViewById(R.id.centrecrop);
        centreinside = findViewById(R.id.centreinside);
        fit = findViewById(R.id.fit);
        placeholder = findViewById(R.id.placeholder);
        error = findViewById(R.id.error);
        fade = findViewById(R.id.fade);
        callback = findViewById(R.id.callback);
        rotate = findViewById(R.id.rotate);
        iv = findViewById(R.id.iv);
        complexrotate = findViewById(R.id.complexrotation);
        getData();
    }
    private void getData() {

        resize.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.with(getApplicationContext())
                        .load("https://freepngimg.com/thumb/cartoon/3-2-cartoon-free-png-image.png")
                        .resize(600, 200)// resizes the image to these dimensions (in pixel). does not respect aspect ratio
                        .into(iv);
                Toast.makeText(ImageAbhi.this, "Resize called ", Toast.LENGTH_SHORT).show();

            }
        });

        centrecrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.with(getApplicationContext())
                        .load("https://freepngimg.com/thumb/cartoon/3-2-cartoon-free-png-image.png")
                        .resize(100,100)
                        .centerCrop()
                        .into(iv);
                Toast.makeText(ImageAbhi.this, "Centrecrop called ", Toast.LENGTH_SHORT).show();
            }
        });
        centreinside.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.with(getApplicationContext())
                        .load("https://freepngimg.com/thumb/cartoon/3-2-cartoon-free-png-image.png")
                        .resize(100,100)
                        .centerInside()
                        .into(iv);
                Toast.makeText(ImageAbhi.this, "Centreinside called ", Toast.LENGTH_SHORT).show();
            }
        });
        fit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.

                        with(getApplicationContext())
                        .load("https://freepngimg.com/thumb/cartoon/3-2-cartoon-free-png-image.png")
                        .fit()
                        .into(iv);
                Toast.makeText(ImageAbhi.this, "Fit called ", Toast.LENGTH_SHORT).show();
            }
        });

        placeholder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.
                        with(getApplicationContext())

                        .load("https://freepngimg.com/thumb/cartoon/3-2-cartoon-free-png-image.png")

                        .placeholder(R.drawable.icon) // can also be a drawable

                        .into(iv);
                Toast.makeText(ImageAbhi.this, "Placeholder called ", Toast.LENGTH_SHORT).show();

            }
        });

        fade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso
                        .with(getApplicationContext())
                        .load("https://freepngimg.com/thumb/cartoon/3-2-cartoon-free-png-image.png")
                        .placeholder(R.mipmap.ic_launcher) // can also be a drawable
                        .noFade()
                        .into(iv);
                Toast.makeText(ImageAbhi.this, "Fade called ", Toast.LENGTH_SHORT).show();
            }
        });
        error.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Picasso.
                        with(getApplicationContext())
                        .load("https://freepngimg.com/thumb/cartoon/3-2-cartoon-free-png-image.png")
                        .error(R.mipmap.ic_launcher) // will be displayed if the image cannot be loaded
                        .into(iv);

                Toast.makeText(ImageAbhi.this, "Error called ", Toast.LENGTH_SHORT).show();



            }
        });
        callback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                callback();

            }
        });
        rotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Picasso.with(getApplicationContext())

                        .load("https://freepngimg.com/thumb/cartoon/3-2-cartoon-free-png-image.png")

                        .rotate(90f)
                        // .rotate(45f, 220f,100f)

                        .into(iv);
                Toast.makeText(ImageAbhi.this, "Rotate Called", Toast.LENGTH_SHORT).show();


            }
        });

        complexrotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Picasso.with(getApplicationContext())

                        .load("https://freepngimg.com/thumb/cartoon/3-2-cartoon-free-png-image.png")

                        //.rotate(90f)
                        .rotate(45f, 220f,100f)

                        .into(iv);
                Toast.makeText(ImageAbhi.this, "Complex Rotate Called", Toast.LENGTH_SHORT).show();


            }
        });


    }


    public void callback() {
        Picasso.with(ImageAbhi.this)
                .load("https://freepngimg.com/thumb/cartoon/3-2-cartoon-free-png-image.png")
                .into(iv, new com.squareup.picasso.Callback() {
                    @Override
                    public void onSuccess() {
                        //do smth when picture is loaded successfully
                        Toast.makeText(ImageAbhi.this, "Image is loaded succesfully", Toast.LENGTH_SHORT).show();


                    }

                    @Override
                    public void onError() {
                        //do smth when there is picture loading error
                        Toast.makeText(ImageAbhi.this, "some Error occured", Toast.LENGTH_SHORT).show();

                    }


                });

    }

}