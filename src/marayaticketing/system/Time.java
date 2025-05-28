/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marayaticketing.system;

public class Time {

    //Attributes
    private int hour;
    private int minutes;

    //parameterized contsttructor with setters for validation
    public Time(int hour, int minutes) {
        setHour(hour);
        setMinutes(minutes);
    }

    //empty constructor
    public Time() {
    }

    //getters and setters with validation
    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        if (minutes > 59 || minutes < 0) {
            System.out.println("Enter a proper amount of minutes.");
        }
        this.minutes = minutes;
    }

    public int getHour() {
        return hour;
    }

    public void setHour(int hour) {
        if (hour > 23 || hour < 0) {
            System.out.println("Enter a valid hour (0-23).");
        }
        this.hour = hour;
    }

    @Override
    public String toString() {
        return hour + ":" + minutes;
    }

    //method to convert String object to Time object
    public static Time valueOf(String timeStr) {
        String[] parts = timeStr.split(":");
        int hour = Integer.parseInt(parts[0]);
        int minutes = Integer.parseInt(parts[1]);
        return new Time(hour, minutes);
    }

}
