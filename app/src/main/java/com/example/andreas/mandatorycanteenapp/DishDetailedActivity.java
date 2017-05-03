package com.example.andreas.mandatorycanteenapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.InputStream;

public class DishDetailedActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dish_detailed);

        Intent intent = getIntent();
        Dish dish = (Dish) intent.getSerializableExtra("DISH");

        new DownloadImageTask((ImageView) findViewById(R.id.dish_imageView))
                .execute(dish.getPictureUrl());

        TextView nameTextView = (TextView) findViewById(R.id.Dish_name);
        nameTextView.setText("Title: " + dish.getTitle());

        TextView DescriptionTextView = (TextView) findViewById(R.id.Dish_description);
        DescriptionTextView.setText("Description: " + dish.getDescription());

        TextView priceTextView = (TextView) findViewById(R.id.Dish_price);
        priceTextView.setText("Price: " + dish.getPrice());

        TextView alcoholTextView = (TextView) findViewById(R.id.dish_alcohol);
        alcoholTextView.setText("Alcohol: " + dish.getAlcohol());

        TextView carbohydratesTextView = (TextView) findViewById(R.id.dish_carbohydrates);
        carbohydratesTextView.setText("Carbohydrates: " + dish.getCarbohydrates());

        TextView energyTextView = (TextView) findViewById(R.id.dish_energy);
        energyTextView.setText("Energy: " + dish.getEnergy());

        TextView fatTextView = (TextView) findViewById(R.id.dish_fat);
        fatTextView.setText("Fat: " + dish.getFat());

        TextView proteinTextView = (TextView) findViewById(R.id.dish_protein);
        proteinTextView.setText("Protein: " + dish.getProtein());

        TextView weightTextView = (TextView) findViewById(R.id.dish_weight);
        weightTextView.setText("Weight: " + dish.getWeight());

    }
}

class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
    ImageView bmImage;

    public DownloadImageTask(ImageView bmImage) {
        this.bmImage = bmImage;
    }

    protected Bitmap doInBackground(String... urls) {
        String urldisplay = urls[0];
        Bitmap mIcon11 = null;
        try {
            InputStream in = new java.net.URL(urldisplay).openStream();
            mIcon11 = BitmapFactory.decodeStream(in);
        } catch (Exception e) {
            Log.e("Image error", e.getMessage());
            e.printStackTrace();
        }
        return mIcon11;
    }

    protected void onPostExecute(Bitmap result) {
        bmImage.setImageBitmap(result);
    }
}
