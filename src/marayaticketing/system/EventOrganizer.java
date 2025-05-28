/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marayaticketing.system;


import java.util.*;
//inherents showMenu() method

public class EventOrganizer extends User {

    //parameterized constructor
    public EventOrganizer(String name, String email, String phone, String password) {
        super(name, email, phone, password);
    }

    //implemets method from parent class User
    @Override
    public void showMenu() {
        Scanner input = new Scanner(System.in);
        boolean running = true;
        //while running is true, the menu will keep popping up
        while (running) {
            System.out.println("\n--- Event Organizer Menu ---");
            System.out.println("1. View All Events");
            System.out.println("2. Approve/Deny Pending Bookings");
            System.out.println("3. Add New Event");
            System.out.println("4. Update Event Details");
            System.out.println("5. Cancel Event");
            System.out.println("6. Exit");
            System.out.print("Choose: ");

            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    viewEvents();
                    break;
                case 2:
                    handlePendingBookings();
                    break;
                case 3:
                    addNewEvent();
                    break;
                case 4:
                    updateEvent();
                    break;
                case 5:
                    cancelEvent();
                    break;
                case 6:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private void viewEvents() {
        System.out.println("\n--- Events ---");
        if (SystemData.events.isEmpty()) {
            System.out.println("No events available.");
        } else {
            for (Event e : SystemData.events) {
                System.out.println(e);
            }
        }
    }

    private void handlePendingBookings() {
        Scanner input = new Scanner(System.in);
        if (SystemData.pendingBookings.isEmpty()) {
            System.out.println("No pending bookings.");
            return;
        }

        for (Booking b : SystemData.pendingBookings) {
            System.out.println(b);
            System.out.print("Approve (1) or Deny (2)? ");
            int decision = input.nextInt();
            if (decision == 1) {
                if (b.getEvent().getAvailableTickets() > 0) {
                    b.approve();
                    b.getEvent().reduceAvailableTickets(1);
                    SystemData.bookings.add(b);
                } else {
                    System.out.println("No tickets left. Booking denied.");
                }
            }
        }

        SystemData.pendingBookings.clear(); // Clear pending list after review
    }

    //method to add new event
    private void addNewEvent() {
        Scanner input = new Scanner(System.in);

        System.out.print("Event name: ");
        String name = input.nextLine();

        System.out.print("Description: ");
        String description = input.nextLine();

        System.out.print("Seat type (e.g., VIP, Standard): ");
        String seatType = input.nextLine();

        System.out.print("Available tickets: ");
        int tickets = input.nextInt();

        System.out.print("Ticket price: ");
        double price = input.nextDouble();

        System.out.print("Event day: ");
        int day = input.nextInt();
        System.out.print("Month: ");
        int month = input.nextInt();
        System.out.print("Year: ");
        int year = input.nextInt();
        Date date = new Date(day, month, year);

        System.out.print("Hour (0-23): ");
        int hour = input.nextInt();
        System.out.print("Minutes: ");
        int minutes = input.nextInt();
        Time time = new Time(hour, minutes);
        //Event object is created using the user inputs
        Event event = new Event(name, date, time, description, tickets, seatType, price);
        //The created event object gets added to the events ArrayList in SystemData class
        SystemData.events.add(event);

        System.out.println("Event created successfully.");
    }

    //method to update event object
    private void updateEvent() {
        Scanner input = new Scanner(System.in);

        System.out.print("Enter Event ID to update: ");//to know which event object to update
        int id = input.nextInt();
        input.nextLine();

        Event target = null;
        for (Event e : SystemData.events) {
            if (e.getEventID() == id) {
                target = e;
                break;
            }
        }

        if (target == null) {
            System.out.println("Event not found.");
            return;
        }

        System.out.println("1. Change Name");
        System.out.println("2. Change Date");
        System.out.println("3. Change Time");
        System.out.println("4. Change Description");
        System.out.println("5. Change Ticket Price");
        System.out.print("Choose what to update: ");
        int choice = input.nextInt();
        input.nextLine();
        //depending on the int the user enters, a setter is used to set the user input

        switch (choice) {
            case 1:
                System.out.print("New name: ");
                String name = input.nextLine();
                target.setName(name);
                break;
            case 2:
                System.out.print("New day: ");
                int day = input.nextInt();
                System.out.print("New month: ");
                int month = input.nextInt();
                System.out.print("New year: ");
                int year = input.nextInt();
                target.setDate(new Date(day, month, year));
                break;
            case 3:
                System.out.print("New hour: ");
                int hour = input.nextInt();
                System.out.print("New minutes: ");
                int minutes = input.nextInt();
                target.setTime(new Time(hour, minutes));
                break;
            case 4:
                System.out.print("New description: ");
                String desc = input.nextLine();
                target.setDescription(desc);
                break;
            case 5:
                System.out.print("New ticket price: ");
                double price = input.nextDouble();
                target.setTicketPrice(price);
                break;
            default:
                System.out.println("Invalid choice.");
        }

        System.out.println("Event updated.");
    }

    //method to cancel event
    private void cancelEvent() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter Event ID to cancel: ");
        int id = input.nextInt();

        Event toRemove = null;
        for (Event e : SystemData.events) {
            if (e.getEventID() == id) {
                toRemove = e;
                break;
            }
        }

        if (toRemove != null) {
            SystemData.events.remove(toRemove);//if the id matches and exists, the remove\
            //method is used to remove the event object from the events ArrayList
            System.out.println("Event canceled.");
        } else {
            System.out.println("Event not found.");
        }
    }
}
