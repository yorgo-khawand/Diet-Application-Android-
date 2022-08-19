package com.example.projet;

public class UserAppointment {
    private String username,date,day,time;

    public UserAppointment(String username,String date, String day, String time){
        this.username = username;
        this.date = date;
        this.day = day;
        this.time = time;
    }

    public String getUsername() {
        return username;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public void setDay(String day) {
        this.day = day;
    }
    public void settime(String time) {
        this.time = time;
    }
    public String getDay() {
        return day;
    }
    public String getTime() {
        return time;
    }

}
