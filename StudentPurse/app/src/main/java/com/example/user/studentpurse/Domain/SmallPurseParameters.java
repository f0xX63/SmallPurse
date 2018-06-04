package com.example.user.studentpurse.Domain;

public class SmallPurseParameters {
    public Balance balance;
    public Categories[] categories;
    public Spending[] spendings;

    public SmallPurseParameters(Balance Balance, Categories[] Categories, Spending[] Spendings){
        balance = Balance;
        categories = Categories;
        spendings = Spendings;
    }

    public SmallPurseParameters(SmallPurseParameters parameters){
        balance = parameters.balance;
        categories = parameters.categories;
        spendings = parameters.spendings;
    }
}
