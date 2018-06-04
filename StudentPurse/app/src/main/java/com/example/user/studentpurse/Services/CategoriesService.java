package com.example.user.studentpurse.Services;

import android.content.Context;

import com.example.user.studentpurse.Domain.Categories;
import com.example.user.studentpurse.Domain.SmallPurseParameters;
import com.example.user.studentpurse.WorkOfFile.JSONHelper;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class CategoriesService implements ICategoryService{

    public Context context;
    public CategoriesService(Context context){
        this.context = context;
    }
    @Override
    public void addCategory(Categories categories) throws IOException {
        SmallPurseParameters parameters = JSONHelper.importFromJSON(context);
        List<Categories> categoriesList = Arrays.asList(parameters.categories);
        categoriesList.add(categories);
        parameters.categories = (Categories[]) categoriesList.toArray();
        JSONHelper.exportToJSON(context, parameters);
    }

    @Override
    public Categories[] getAllCategoties() throws IOException {
        SmallPurseParameters parameters = JSONHelper.importFromJSON(context);
        return parameters.categories;
    }
}
