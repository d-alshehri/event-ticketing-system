/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marayaticketing.system;

import java.util.*;
//extends User parent class, so it has to implement showMenu()

public class EventGoer extends User {

    //static integer to keep count of canceled bookings
    private static int canceledBookings = 0;

    //parameterized constructor, includes inhereted attributes
    public EventGoer(String name, String email, String phone, String password) {
        super(name, email, phone, password);
    }

    //getter to return the amount of canceled bookings
    public static int getCanceledBookings() {
        return canceledBookings;
    }

    //overridden showMenu method
    @Override
    public void showMenu() {
        Scanner input = new Scanner(System.in);
        boolean running = true;
        //while running is true, the eventGoer menu will keep popping up
        while (running) {
            System.out.println("\n--- Event Goer Menu ---");
            System.out.println("1. Browse Events");
            System.out.println("2. Book Ticket");
            System.out.println("3. View My Bookings");
            System.out.println("4. Cancel Booking");
            System.out.println("5. Suggest New Event");
            System.out.println("6. Update Personal Information");
            System.out.println("7. Exit");
            System.out.print("Choose: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1://if evengoer selects 1, case 1 will call the browseEvents method, same goes for the other numbers
                    browseEvents();
                    break;
                case 2:
                    bookTicket();
                    break;
                case 3:
                    viewMyBookings();
                    break;
                case 4:
                    cancelBooking();
                    break;
                case 5:
                    suggestEvent();
                    break;
                case 6://when evengoer enters 6, the updateInfo (from user class)
                    //is called and they can update email or phone
                    System.out.print("New email: ");
                    String email = input.nextLine();
                    System.out.print("New phone: ");
                    String phone = input.nextLine();
                    updateInfo(email, phone);
                    break;
                case 7:
                    running = false;//when 7 is selected(to exit) running is false
                    //and the loop stops (menu stops appearing)
                    break;
                default:
                    System.out.println("Invalid option");

            }

        }
    }

    //method that calls the events Static ArrayList from the SystemData class
    //if it is not empty, it uses a for loop to print the events
    private void browseEvents() {
        System.out.println("\n--- Available Events ---");
        if (SystemData.events.isEmpty()) {
            System.out.println("No events available");
            return;
        }
        for (Event e : SystemData.events) {
            System.out.println(e);
        }
    }

    //method that allows user to book an event
    private void bookTicket() {
        Scanner input = new Scanner(System.in);
        if (SystemData.events.isEmpty()) {
            System.out.print("No events to book.");
            return;
        }
        browseEvents();
        System.out.print("Enter Event ID to book: ");
        int id = input.nextInt();
        input.nextLine();

        Event selected = null;
        for (Event e : SystemData.events) {
            if (e.getEventID() == id) {
                selected = e;
                break;
            }
        }

        if (selected == null) {
            System.out.println("Event not found");
            return;
        }

        if (selected.getAvailableTickets() <= 0) {
            System.out.println("No tickets available.");
            return;
        }
        Booking booking = new Booking(this.userID, selected);//takes the selected event
        //and the user's ID and uses them to create booking object
        SystemData.pendingBookings.add(booking);//booking object is added to the pending bookings
        //static ArrayList in SystemData class
        System.out.println("Booking request submitted. Waiting for approval.");
    }

    //method to view bookings
    private void viewMyBookings() {
        System.out.println("\n--- My Bookings ---");
        for (Booking b : SystemData.bookings) {//for each loop to print bookings stored
            //in the bookings static arraylist 
            if (b.getUserID() == this.userID) {//if the booking object's User ID matches
                //the one from the user that selected the option to view booking, then it is printed
                System.out.println(b);
            }
        }

    }

    //method to remove booking from the Static booking ArrayList
    private void cancelBooking() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter Booking ID to cancel: ");
        int id = input.nextInt();
        Booking toRemove = null;
        for (Booking b : SystemData.bookings) {
            if (b.getBookingID() == id && b.getUserID() == this.userID) {
                toRemove = b;
                break;
            }

        }
        if (toRemove != null) {
            //&& toRemove.isApproved()
            toRemove.getEvent().increaseAvailableTickets(1);//ticket amount goes up since it is no longer taken
            SystemData.bookings.remove(toRemove);//the remove method is
            //used to remove the booking from the ArrayList
            System.out.println("Booking canceled.");
            canceledBookings++;//for statistics
        } else {
            System.out.println("Booking not found or not yours.");
        }
    }

    //method added to suggest new events
    private void suggestEvent() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your event suggestion: ");
        String suggestion = input.nextLine();
        System.out.print("Thank you! Your suggestion has been recieved: " + suggestion);
    }
}
