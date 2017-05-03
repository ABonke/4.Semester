package com.example.andreas.mandatorycanteenapp;

import java.io.Serializable;

/**
 * Created by Andreas on 07-04-2017.
 */
public class Dish implements Serializable {

    private double alcohol;
    private double carbohydrates;
    private String description;
    private double energy;
    private double fat;
    private int id;
    private String pictureUrl;
    private double price;
    private double protein;
    private String title;
    private double weight;

    public double getAlcohol() {
        return alcohol;
    }

    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }

    public double getCarbohydrates() {
        return carbohydrates;
    }

    public void setCarbohydrates(double carbohydrates) {
        this.carbohydrates = carbohydrates;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getEnergy() {
        return energy;
    }

    public void setEnergy(double energy) {
        this.energy = energy;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(double fat) {
        this.fat = fat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public Dish() {
    }

    public Dish(double alcohol, double carbohydrates, String description, double energy, double fat, int id, String pictureUrl, double price, double protein, String title, double weight) {
        this.alcohol = alcohol;
        this.carbohydrates = carbohydrates;
        this.description = description;
        this.energy = energy;
        this.fat = fat;
        this.id = id;
        this.pictureUrl = pictureUrl;
        this.price = price;
        this.protein = protein;
        this.title = title;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Dish{" +
                "alcohol=" + alcohol +
                ", carbohydrates=" + carbohydrates +
                ", description='" + description + '\'' +
                ", energy=" + energy +
                ", fat=" + fat +
                ", id=" + id +
                ", pictureUrl='" + pictureUrl + '\'' +
                ", price=" + price +
                ", protein=" + protein +
                ", title='" + title + '\'' +
                ", weight=" + weight +
                '}';
    }
}
