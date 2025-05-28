/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marayaticketing.system;

public class Date {

    //attributes
    private int day;
    private int month;
    private int year;

    //parameterized consctructor with setters for validation
    public Date(int day, int month, int year) {
        setDay(day);
        setMonth(month);
        this.year = year;
    }

    //empty constructor
    public Date() {
    }

    //getters and settings with validation if needed
    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        year = 2025;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        if (day <= 0) {
            System.out.println("Please enter a valid day");
        }
        this.day = day;
    }

    public int getMonth() {

        return month;
    }

    public void setMonth(int month) {
        if (month > 12 || month <= 0) {
            System.out.println("Please enter a valid month");
        }
        this.month = month;
    }

    @Override
    public String toString() {
        return day + "/" + month + "/" + year;
    }

    //method to convert String object a Date object
    public static Date valueOf(String dateStr) {
        String[] parts = dateStr.split("/");
        int day = Integer.parseInt(parts[0]);
        int month = Integer.parseInt(parts[1]);
        int year = Integer.parseInt(parts[2]);
        return new Date(day, month, year);
    }

}
