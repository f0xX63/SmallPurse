package com.example.user.studentpurse.Services;

import android.content.Context;

import com.example.user.studentpurse.Domain.Balance;
import com.example.user.studentpurse.Domain.Categories;
import com.example.user.studentpurse.Domain.SmallPurseParameters;
import com.example.user.studentpurse.Domain.Spending;
import com.example.user.studentpurse.WorkOfFile.JSONHelper;

import java.io.File;
import java.io.IOException;

public class FirstOpenApp {
    private static Categories[] Categories = new Categories[] {
            new Categories(1,"Еда", new String[] {"Магнит", "Пятерочка", "Перекресток", "Перчини"}),
            new Categories(2,"Одежда", new String[] {"Nike", "Adidas", "Ашан", "O'Stin"}),
            new Categories(3,"Развлечения", new String[] {"Кино", "Анти-кафе", "боулинг"}),
    };
    private static Spending[] Spending = new Spending[]{};
    private static Balance Balance = new Balance(0);

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

    /*public static void createFiles(String[] fileNames) throws Exception {
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
    }*/



    public static void FillFiles(Context context) throws Exception {
        SmallPurseParameters parameters = new SmallPurseParameters(Balance, Categories, Spending);
        try {
            JSONHelper.exportToJSON(context, parameters);
        } catch (IOException e) {
            throw new Exception("Не удалось загрузить начальные данные приложения");
        }
    }
}

