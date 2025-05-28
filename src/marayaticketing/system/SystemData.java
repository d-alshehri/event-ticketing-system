/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marayaticketing.system;

import java.util.*;

public class SystemData {

    //static array lists for data consistency 
    public static ArrayList<User> users = new ArrayList<>();
    public static ArrayList<Event> events = new ArrayList<>();
    public static ArrayList<Booking> bookings = new ArrayList<>();
    public static ArrayList<Booking> pendingBookings = new ArrayList<>();
    public static User currentUser = null;

}
