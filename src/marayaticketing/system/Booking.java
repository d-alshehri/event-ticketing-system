/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marayaticketing.system;


public class Booking {

    //attributes
    private static int nextID = 1;
    int bookingID;

    private int userID;
    private Event event;
    boolean approved;

    //constructor
    public Booking(int userID, Event event) {
        this.bookingID = nextID++;
        this.userID = userID;
        this.event = event;
        this.approved = false;
    }

    public Booking() {
    }

    //getters and setters
    public static int getNextID() {
        return nextID;
    }

    public static void setNextID(int nextID) {
        Booking.nextID = nextID;
    }

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    //a method to approve a booking by organizer
    public void approve() {
        approved = true;
    }

    //to string method
    @Override
    public String toString() {
        return "Booking ID: " + bookingID + " | User ID: " + userID + " | Event: " + event;
    }

}
