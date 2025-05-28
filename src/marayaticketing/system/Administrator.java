/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marayaticketing.system;

import java.util.Scanner;


public class Administrator extends User {//extends user, so must override showMenu methhod

    //parameterized constructor with inherited attributes
    public Administrator(String name, String email, String phone, String password) {
        super(name, email, phone, password);
    }

    //implemets and overrides showMenu method from parent user class
    @Override
    public void showMenu() {
        Scanner input = new Scanner(System.in);
        boolean running = true;

        //menu pops up as long as running is true
        while (running) {
            System.out.println("\n--- Administrator Menu ---");
            System.out.println("1. View Event Statistics");
            System.out.println("2. Manage Users");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    showStats();
                    break;
                case 2:
                    manageUsers();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void showStats() {
        int totalEvents = SystemData.events.size();//gives amount of events created
        int totalBookings = SystemData.bookings.size();//gives amount of bookings created
        int totalPending = SystemData.pendingBookings.size();//gives amount of pending bookings created
        int canceled = EventGoer.getCanceledBookings();//gives amount of canceled bookings created

        System.out.println("\n--- Event Statistics ---");
        System.out.println("Total Events: " + totalEvents);
        System.out.println("Total Approved Bookings: " + totalBookings);
        System.out.println("Total Pending Bookings: " + totalPending);
        System.out.println("Total Canceled Bookings: " + canceled);
    }

    //menu with options to add, remove, or view users
    private void manageUsers() {
        Scanner input = new Scanner(System.in);
        boolean managing = true;

        while (managing) {
            System.out.println("\n--- User Management ---");
            System.out.println("1. View All Users");
            System.out.println("2. Add User");
            System.out.println("3. Remove User");
            System.out.println("4. Back");
            System.out.print("Choose: ");
            int choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1:
                    viewUsers();
                    break;
                case 2:
                    addUser();
                    break;
                case 3:
                    removeUser();
                    break;
                case 4:
                    managing = false;
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private void viewUsers() {
        System.out.println("\n--- All Users ---");
        for (User user : SystemData.users) {//loops through users ArrayList in SystemData class
            //displays users
            System.out.println(user.getClass().getSimpleName() + ": " + user.getName() + " | Email: " + user.getEmail());
        }
    }

    private void addUser() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = input.nextLine();
        System.out.print("Email: ");
        String email = input.nextLine();
        System.out.print("Phone: ");
        String phone;
        do {
            System.out.print("Phone (must start with 05 and be 10 digits): ");
            phone = input.nextLine();
        } while (!phone.matches("05\\d{8}"));

        String password;
        do {
            System.out.print("Password (min 8 chars, with uppercase, lowercase, digit, and special char): ");
            password = input.nextLine();
        } while (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$"));

        System.out.println("Role (1 = EventGoer, 2 = EventOrganizer): ");
        int role = input.nextInt();

        if (role == 1) {
            //creats EventGoer object using the user inputs and adds it to the 
            //users ArrayList
            SystemData.users.add(new EventGoer(name, email, phone, password));
        } else if (role == 2) {
            //Creates EventOrganizer object and adds it to users ArrayList
            SystemData.users.add(new EventOrganizer(name, email, phone, password));
        } else {
            System.out.println("Invalid role.");
            return;
        }

        System.out.println("User added.");
    }

    private void removeUser() {
        Scanner input = new Scanner(System.in);
        viewUsers();
        System.out.print("Enter name of user to remove: ");
        String name = input.nextLine();

        User toRemove = null;
        for (User user : SystemData.users) {
            if (user.getName().equalsIgnoreCase(name)) {//matches the name of the
                //input to the name of the users in users ArrayList 
                toRemove = user;
                break;
            }
        }

        if (toRemove != null) {
            SystemData.users.remove(toRemove);//removes matched user from the users ArrayList
            System.out.println("User removed.");
        } else {
            System.out.println("User not found.");
        }
    }
}
