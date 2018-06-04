package com.example.user.studentpurse.Domain;


import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class Categories implements Serializable{
    public static String fileName = "Categories.dat";

    public int Id;
    public String Name;
    public String[] SubCategories;

    public Categories(int id, String name, String[] subCategories) {
        this.Id = id;
        this.Name = name;
        this.SubCategories = subCategories;
    }

    public Categories(Categories category) {
        this.Id = category.Id;
        this.Name = category.Name;
        this.SubCategories = category.SubCategories;
    }
}
