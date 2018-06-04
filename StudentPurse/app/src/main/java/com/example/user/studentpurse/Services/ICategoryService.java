package com.example.user.studentpurse.Services;

import com.example.user.studentpurse.Domain.Categories;

import java.io.IOException;
import java.util.List;

public interface ICategoryService {
    void addCategory(Categories categories) throws IOException;
    List<Categories> getAllCategoties() throws IOException;
    List<String> getAllCategotiesString() throws IOException;
    Categories getCategoryByName(String name) throws IOException;
}
