package com.example.user.studentpurse.Services;

import com.example.user.studentpurse.Domain.Categories;

import java.io.IOException;

public interface ICategoryService {
    void addCategory(Categories categories) throws IOException;
    Categories[] getAllCategoties() throws IOException;
}
