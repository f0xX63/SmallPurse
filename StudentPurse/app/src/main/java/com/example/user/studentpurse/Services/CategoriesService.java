package com.example.user.studentpurse.Services;

import com.example.user.studentpurse.Domain.Categories;
import com.example.user.studentpurse.WorkOfFile.Repository;
import java.io.IOException;
import java.util.List;

public class CategoriesService implements ICategoryService {
    Repository<Categories> repository;

    public CategoriesService(Repository<Categories> repository)
    {
        this.repository = repository;
    }

    @Override
    public void addCategory(Categories categories) throws IOException {
        List<Categories> categoriesArrayList = repository.getAllData();
        categoriesArrayList.add(categories);
        repository.saveAllData(categoriesArrayList);
    }

    @Override
    public void deleteCategory(Categories categories) throws IOException {
        List<Categories> categoriesArrayList = repository.getAllData();
        categoriesArrayList.remove(categories);
        repository.saveAllData(categoriesArrayList);
    }

    @Override
    public List<Categories> getAllCategoties() throws IOException {
        try {
            return repository.getAllData();
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }

    @Override
    public void addListCategories(List<Categories> categoriesList) throws IOException {
        List<Categories> categoriesArrayList = repository.getAllData();
        categoriesArrayList.addAll(categoriesArrayList);
        repository.saveAllData(categoriesList);
    }
}
