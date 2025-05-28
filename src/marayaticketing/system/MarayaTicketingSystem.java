/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marayaticketing.system;

import java.util.Scanner;


public class MarayaTicketingSystem {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean running = true;

        // Load data from files at startup
        FileHandler.loadAll();

        // Add default admin with password
        if (SystemData.users.isEmpty()) {
            SystemData.users.add(new Administrator("Admin", "admin@system.com", "0559999999", "admin123"));
        }

        // Show authentication menu
        AuthService.showAuthMenu();

        // create user objects and add them to the ArrayList
        SystemData.users.add(new EventGoer("Alice", "alice@mail.com", "0551000000", "Password@123"));
        SystemData.users.add(new EventOrganizer("Bob", "bob@org.com", "0552000000", "Password@123"));
        SystemData.users.add(new Administrator("Admin", "admin@system.com", "0559999999", "Password@123"));
        Date d1 = new Date(3, 3, 2025);
        Time t1 = new Time(2, 33);

        //sample events to test the methods
        Event e1 = new Event("theatre", d1, t1, "Music and acting", 5, "vip", 12.5);
        Event e2 = new Event("Acting", d1, t1, "Music and acting", 6, "standard", 14.5);
        SystemData.events.add(e1);
//sample bookings to test the methods
        SystemData.events.add(e2);
        Booking b1 = new Booking(1, e1);
        Booking b2 = new Booking(1, e1);
        SystemData.pendingBookings.add(b1);
        SystemData.pendingBookings.add(b2);
        //saves data before before the project is turned off
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            FileHandler.saveAll();
            System.out.println("Data saved successfully.");
        }));

        input.close();
    }

//generic method to login the user based on the user's role
    public static void loginUser(Class<?> userType) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = input.nextLine();

        for (User user : SystemData.users) {
            if (user.getName().equalsIgnoreCase(name) && userType.isInstance(user)) {
                user.showMenu();
                return;
            }
        }

        System.out.println("User not found or role mismatch.");
    }

}
