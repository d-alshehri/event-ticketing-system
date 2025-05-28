/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marayaticketing.system;


public class Event {
    
    //attributes 
    private static int nextID = 1; //counter to assign event id
    private int eventID;
    private String name;
    private Date date;
    private Time time;
    private String description;
    private int availableTickets;
    private String seatType;
    private double ticketPrice;

    //constructor with parameters
    public Event(String name, Date date, Time time, String description, int availableTickets, String seatType, double ticketPrice) {
        this.eventID = nextID++;
        this.name = name;
        this.date = date;
        this.time = time;
        this.description = description;
        this.availableTickets = availableTickets;
        this.seatType = seatType;
        this.ticketPrice = ticketPrice;
    }
    //empty constructor
    public Event() {
    }

    //getters and setters
    public static int getNextID() {
        return nextID;
    }

    public static void setNextID(int nextID) {
        Event.nextID = nextID;
    }

    public int getEventID() {
        return eventID;
    }

    public void setEventID(int eventID) {
        this.eventID = eventID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAvailableTickets() {
        return availableTickets;
    }

    public void setAvailableTickets(int availableTickets) {
        this.availableTickets = availableTickets;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(double ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    //method to reduce tickets when Booking object is created
    public void reduceAvailableTickets(int count) {
        this.availableTickets -= count;
    }
    //method to increase ticket count when needed
    public void increaseAvailableTickets(int count) {
        this.availableTickets += count;
    }

    //to string method for easily understandable output
    @Override
    public String toString() {
        return "Event ID: " + eventID + "\nEvent Name: " + name + "\nEvent Date: " + date + "\nEvent Time: " + time + "\nEvent Description: " + description + "\nEvent Available Tickets: " + availableTickets + "\nSeat Type: " + seatType + "\nTicket Price: " + ticketPrice + "\n-----------";
    }

}


