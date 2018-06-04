package com.example.user.studentpurse.Domain;

public class SmallPurseParameters {
    public Balance[] balances;
    public Categories[] categories;
    public Spending[] spendings;

    public SmallPurseParameters(Balance[] Balances, Categories[] Categories, Spending[] Spendings){
        balances = Balances;
        categories = Categories;
        spendings = Spendings;
    }

    public SmallPurseParameters(SmallPurseParameters parameters){
        balances = parameters.balances;
        categories = parameters.categories;
        spendings = parameters.spendings;
    }
}
