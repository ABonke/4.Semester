package com.example.andreas.mandatorycanteenapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Andreas on 14-04-2017.
 */

public class DishListItemAdapter extends ArrayAdapter<Dish> {
    private int resource;

    public DishListItemAdapter(Context context, int resource, List<Dish> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Dish dish = getItem(position);
        String dishName = dish.getTitle();
        String description = dish.getDescription();
        LinearLayout dishView;
        if (convertView == null) {
            dishView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(inflater);
            li.inflate(resource, dishView, true);
        } else {
            dishView = (LinearLayout) convertView;
        }
        TextView nameView = (TextView) dishView.findViewById(R.id.dishList_item_name);
        TextView descriptionView = (TextView) dishView.findViewById(R.id.dishList_item_description);
        nameView.setText(dishName);
        descriptionView.setText("Description: " + description);
        return dishView;
    }
}
