package com.example.administrator.volley;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.util.LruCache;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.Volley;

public class MainActivity extends AppCompatActivity {

    ImageView imageView;
    ImageLoader mImageLoader;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView) findViewById(R.id.imageview);

        final LruCache<String, Bitmap> mCache = new LruCache<String, Bitmap>(10);

        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mImageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {

            public void putBitmap(String url, Bitmap bitmap) {
                mCache.put(url, bitmap);
            }
            public Bitmap getBitmap(String url) {
                return mCache.get(url);
            }
        });
        NetworkImageView imgAvatar = (NetworkImageView) findViewById(R.id.imgAvatar);
        String urlhinh = "http://bactrangtinhdoi.hexat.com/hinh-nen-game-dep-nhat/bactrangtinhdoi.hexat+25.jpg";

        imgAvatar.setImageUrl(urlhinh,mImageLoader);
        Log.d("BBB",mCache.maxSize() +" ");
//        ImageRequest imageRequest = new ImageRequest(urlhinh, new Response.Listener<Bitmap>() {
//            @Override
//            public void onResponse(Bitmap response) {
//                imageView.setImageBitmap(response);
//            }
//        }, 0, 0, ImageView.ScaleType.FIT_XY, null, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("BBB",error.getMessage());
//            }
//        });
//        requestQueue.add(imageRequest);

    }
}
