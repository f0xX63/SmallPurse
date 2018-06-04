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
    private static Balance[] Balances = new Balance[]{
            new Balance(100,"Налиичные")
    };

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


    public static void FillFiles(Context context) throws Exception {
        SmallPurseParameters parameters;
        try {
            parameters = JSONHelper.importFromJSON(context);
            if (!parameters.FirstLaunch){
                parameters = new SmallPurseParameters(Balances, Categories, Spending);
                parameters.FirstLaunch = false;
            }
        } catch (Exception e){
            parameters = new SmallPurseParameters(Balances, Categories, Spending);
            parameters.FirstLaunch = false;
        }
        try {
            JSONHelper.exportToJSON(context, parameters);
        } catch (IOException e) {
            throw new Exception("Не удалось загрузить начальные данные приложения");
        }
    }
}

