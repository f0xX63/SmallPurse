package com.example.user.studentpurse.Services;

import android.content.Context;

import com.example.user.studentpurse.Domain.Categories;
import com.example.user.studentpurse.Domain.SmallPurseParameters;
import com.example.user.studentpurse.WorkOfFile.JSONHelper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CategoriesService implements ICategoryService{

    public Context context;
    public CategoriesService(Context context){
        this.context = context;
    }
    @Override
    public void addCategory(Categories categories) throws IOException {
        SmallPurseParameters parameters = JSONHelper.importFromJSON(context);
        List<Categories> categoriesList = new ArrayList<Categories>();
        for (Categories cat: parameters.categories){
            categoriesList.add(cat);
        }
        categoriesList.add(categories);
        Categories[] categoriesArr = new Categories[categoriesList.size()];
        categoriesArr = categoriesList.toArray(categoriesArr);
        parameters.categories = categoriesArr;
        JSONHelper.exportToJSON(context, parameters);
    }

    @Override
    public List<Categories> getAllCategoties() throws IOException {
        SmallPurseParameters parameters = JSONHelper.importFromJSON(context);
        List<Categories> categoriesList = new ArrayList<Categories>();
        for (Categories cat: parameters.categories) {
            categoriesList.add(cat);
        }
        return categoriesList;
    }

    @Override
    public List<String> getAllCategotiesString() throws IOException {
        SmallPurseParameters parameters = JSONHelper.importFromJSON(context);
        List<String> result = new ArrayList<String>();
        for (Categories cat: parameters.categories){
            result.add(cat.Name);
        }
        return result;
    }

    public Categories getCategoryByName(String name) throws IOException
    {
        SmallPurseParameters parameters = JSONHelper.importFromJSON(context);
        for (Categories cat: parameters.categories){
            if (cat.Name.equals(name))
            {
                return cat;
            }
        }
        return null;
    }
}
