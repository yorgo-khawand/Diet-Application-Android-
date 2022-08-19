package com.example.projet;

public class UserInfo {
    String username,birthday,startdate,end_date,doctorname,gender;
    int height,weight,body_fat;

    public UserInfo(String doctorname,String username,String birthday,int height,int weight,int body_fat,String gender,String startdate,String end_date){
      this.doctorname=doctorname;
        this.username = username;
        this.birthday = birthday;
        this.height = height;
        this.weight = weight;
        this.body_fat = body_fat;
       this.gender=gender;
        this.startdate = startdate;
        this.end_date = end_date;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnd_date() {
        return end_date;
    }

    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getBody_fat() {
        return body_fat;
    }

    public void setBody_fat(int body_fat) {
        this.body_fat = body_fat;
    }

}
