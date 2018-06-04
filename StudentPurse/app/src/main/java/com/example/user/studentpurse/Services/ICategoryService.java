package com.example.user.studentpurse.Services;

import com.example.user.studentpurse.Domain.Categories;

import java.io.IOException;
import java.util.List;

public interface ICategoryService {
    void addCategory(Categories categories) throws IOException;
    void deleteCategory(Categories categories) throws IOException;
    List<Categories> getAllCategoties() throws IOException;
    void addListCategories(List<Categories> categoriesList) throws IOException;
}
