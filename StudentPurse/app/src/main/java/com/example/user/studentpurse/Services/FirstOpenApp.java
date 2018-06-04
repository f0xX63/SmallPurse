package com.example.user.studentpurse.Services;

import com.example.user.studentpurse.Domain.Balance;
import com.example.user.studentpurse.Domain.Categories;
import com.example.user.studentpurse.WorkOfFile.Repository;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirstOpenApp {
    public static Boolean checkExcistsFiles(String[] fileNames)
    {
        for (String filename: fileNames) {
            File file = new File(filename);
            if (!file.exists())
            {
                return false;
            }
        }
        return true;
    }

    public static void createFiles(String[] fileNames) throws Exception {
        Boolean isExcistsFiles = checkExcistsFiles(fileNames);
        if (!isExcistsFiles) {
            for (String filename : fileNames) {
                File file = new File(filename);
                try {
                    file.createNewFile();
                } catch (IOException e) {
                    throw new Exception("Не удалось создать файлы");
                }
            }
        }
    }



    public static void FillFiles(Categories[] categories) throws Exception {
        Balance balance = new Balance(0);
        IBalanceService balanceService = new BalanceService(new Repository<Balance>(Balance.fileName));
        try {
            balanceService.setBalance(balance);
        } catch (IOException e) {
            throw new Exception("Не удалось загрузить начальные данные приложения");
        }
        ICategoryService categoryService = new CategoriesService(new Repository<Categories>(Categories.fileName));
        categoryService.addListCategories(Arrays.asList(categories));
    }
}

