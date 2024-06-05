package com.example.happytimeskindergarten.ui;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.widget.ImageView;

import java.io.ByteArrayOutputStream;

public class Base64image {

    public static Bitmap decode_image(String image_data){
        byte[] decodedString = Base64.decode(image_data, Base64.DEFAULT);

        return BitmapFactory.decodeByteArray(decodedString, 0, decodedString.length);
    }

    public static String encode_image(Bitmap image){
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        byte[] byteArray = byteArrayOutputStream.toByteArray();

        return "data:image/png;base64," + Base64.encodeToString(byteArray, Base64.DEFAULT);
    }
}
