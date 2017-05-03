package com.example.andreas.mandatorycanteenapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    @Override
    protected void onStart() {
        super.onStart();
        ReadTask task = new ReadTask();
        task.execute("http://anbo-canteen.azurewebsites.net/Service1.svc/dishes");
    }

    private class ReadTask extends ReadHttpTask {
        @Override
        protected void onPostExecute(CharSequence charSequence) {
            final List<Dish> dishes = new ArrayList<>();
            try {
                JSONArray array = new JSONArray(charSequence.toString());
                for (int i = 0; i < array.length(); i++) {
                    JSONObject obj = array.getJSONObject(i);
                    double alcohol = obj.getDouble("Alcohol");
                    double carbohydrates = obj.getDouble("Carbohydrates");
                    String description = obj.getString("Description");
                    double energy = obj.getDouble("Energy");
                    double fat = obj.getDouble("Fat");
                    int id = obj.getInt("Id");
                    String pictureUrl = obj.getString("PictureUrl");
                    double price = obj.getDouble("Price");
                    double protein = obj.getDouble("Protein");
                    String title = obj.getString("Title");
                    double weight = obj.getDouble("Weight");
                    Dish dish = new Dish(alcohol, carbohydrates, description, energy, fat, id, pictureUrl, price, protein, title, weight);
                    dishes.add(dish);
                }
                ListView listView = (ListView) findViewById(R.id.DishesListView);
                DishListItemAdapter adapter = new DishListItemAdapter(getBaseContext(), R.layout.dish_layout_present, dishes);
                listView.setAdapter(adapter);
                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent intent = new Intent(getBaseContext(), DishDetailedActivity.class);
                        intent.putExtra("DISH", dishes.get((int) id));
                        startActivity(intent);
                    }
                });
            } catch (JSONException ex) {
                Log.e("DISHES", ex.getMessage());
            }
        }


    }
}