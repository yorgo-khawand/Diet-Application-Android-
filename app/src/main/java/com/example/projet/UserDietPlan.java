package com.example.projet;

public class UserDietPlan {
    private String username,diary_date,food_name,food_type,serving_size,mealtime;

    public UserDietPlan(String username,String diary_date, String food_name, String food_type,String serving_size,String mealtime){
        this.username = username;
        this.diary_date = diary_date;
        this.food_name = food_name;
        this.food_type = food_type;
        this.serving_size = serving_size;
        this.mealtime = mealtime;
    }

    public String getUsername() {
        return username;
    }
    public String getDate() {
        return diary_date;
    }

    public String getMealtime() {
        return mealtime;
    }

    public String getServing_size() {
        return serving_size;
    }

    public String getMeal() {
        return food_name;
    }

    public String getType() {
        return food_type;
    }
}
