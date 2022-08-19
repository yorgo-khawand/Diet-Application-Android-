package com.example.projet;

public class AppointmentModel {
    private String doctorname,patientName,date,day,time;

    public AppointmentModel(String doctorname,String patientName, String date, String day, String time) {
        this.doctorname = doctorname;
        this.patientName = patientName;
        this.date = date;
        this.day = day;
        this.time = time;
    }

    public String getPatientName() {
        return patientName;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
