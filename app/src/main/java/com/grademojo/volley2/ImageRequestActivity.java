package com.grademojo.volley2;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


import com.android.volley.Cache;
import com.android.volley.Cache.Entry;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.grademojo.volley.Common.AppController;

import java.io.UnsupportedEncodingException;

public class ImageRequestActivity extends Activity {

    //private static final String TAG = ImageRequestActivity.class
    // .getSimpleName();

    final String TAG= ImageRequestActivity.class.getName();
    private Button btnImageReq;
    private NetworkImageView imgNetWorkView;
    private ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_request);

        btnImageReq = (Button) findViewById(R.id.btnImageReq);
        imgNetWorkView = (NetworkImageView) findViewById(R.id.imgNetwork);
        imageView = (ImageView) findViewById(R.id.imgView);

        btnImageReq.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                makeImageRequest();
            }
        });
    }

    private void makeImageRequest() {

        if(AppController.getInstance() == null){
            Log.d("###", "null");
        }else{
            Log.d("###", "working");
        }

        ImageLoader imageLoader = AppController.getInstance().getImageLoader();

        // If you are using NetworkImageView
        imgNetWorkView.setImageUrl(Const.URL_IMAGE, imageLoader);



        // Loading image with placeholder and error image
        imageLoader.get(
                Const.URL_IMAGE,
                ImageLoader.getImageListener(
                        imageView,
                        R.drawable.ico_loading,
                        R.drawable.ico_error
                )
        );

        Cache cache = AppController.getInstance().getRequestQueue().getCache();
        Entry entry = cache.get(Const.URL_IMAGE);
        if(entry != null){
            try {
                String data = new String(entry.data, "UTF-8");
                // handle data, like converting it to xml, json, bitmap etc.,
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }else{
            // cached response doesn't exists. Make a network call here
        }

    }
}