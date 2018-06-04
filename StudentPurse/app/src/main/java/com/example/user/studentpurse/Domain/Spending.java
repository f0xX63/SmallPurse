package com.example.user.studentpurse.Domain;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Date;

public class Spending implements Serializable{
    public static String fileName = "Spending.dat";

    public int Id;
    public String Categories;
    public String SubCategories;
    public Date Date;
    public String Description;
    public double Value;

    public Spending(Spending spending){
        this.Id = spending.Id;
        this.Categories = spending.Categories;
        this.SubCategories = spending.SubCategories;
        this.Date = spending.Date;
        this.Description = spending.Description;
        this.Value = spending.Value;
    }

    public Spending(int id, String categories, String subCategories, Date date, String description, double value){
        this.Id = id;
        this.Categories = categories;
        this.SubCategories = subCategories;
        this.Date = date;
        this.Description = description;
        this.Value = value;
    }
}

