package com.example.user.studentpurse.Domain;

import android.os.Environment;

import java.io.Serializable;
import java.util.Date;

public class Spending implements Serializable{
    public static String fileName = Environment.getExternalStorageDirectory() + "/Spending.txt";

    public int Id;
    public String Categories;
    public String SubCategories;
    public Date Date;
    public String Storage;
    public double Value;

    public Spending(Spending spending){
        this.Id = spending.Id;
        this.Categories = spending.Categories;
        this.SubCategories = spending.SubCategories;
        this.Date = spending.Date;
        this.Storage = spending.Storage;
        this.Value = spending.Value;
    }

    public Spending(int id, String categories, String subCategories, Date date, String storage, double value){
        this.Id = id;
        this.Categories = categories;
        this.SubCategories = subCategories;
        this.Date = date;
        this.Storage = storage;
        this.Value = value;
    }
}

