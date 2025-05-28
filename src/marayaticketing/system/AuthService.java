/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marayaticketing.system;

import java.util.Scanner;


public class AuthService {

    public static User currentUser = null;

    public static void showAuthMenu() {
        Scanner input = new Scanner(System.in);
        boolean running = true;

        while (running) {

            System.out.println("\n--- Authentication ---");
            System.out.println("1. Login");
            System.out.println("2. Register");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");
            int choice = input.nextInt();// User puts in what service they need
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    login();
                    if (currentUser != null) {
                        currentUser.showMenu();
                    }
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void login() {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter email: ");
        String email = input.nextLine();
        System.out.print("Enter password: ");
        String password = input.nextLine();

        for (User user : SystemData.users) {
            if (user.getEmail().equals(email) && user.getPassword().equals(password)) {//authenticates email
                currentUser = user;
                System.out.println("Login successful! Welcome, " + user.getName());
                return;
            }
        }

        System.out.println("Invalid email or password.");
    }

    private static void register() {
        Scanner input = new Scanner(System.in);
        System.out.println("\n--- Registration ---");

        System.out.print("Enter your name: ");
        String name = input.nextLine();

        System.out.print("Enter your email: ");
        String email = input.nextLine();

        // Check if email already exists
        for (User user : SystemData.users) {
            if (user.getEmail().equals(email)) {
                System.out.println("Email already registered.");
                return;
            }
        }
        //if account doesn't already exist, it creates one
        String phone;
        do {
            System.out.print("Enter your phone number (must start with 05 and be 10 digits): ");
            phone = input.nextLine();
        } while (!isValidPhoneNumber(phone));

        String password;
        do {
            System.out.print("Enter a password (min 8 chars, with uppercase, lowercase, digit, and special char): ");
            password = input.nextLine();
        } while (!isValidPassword(password));

        System.out.println("Select your role (1 = Event Goer, 2 = Event Organizer, 3 = Administrator): ");
        int role = input.nextInt();//user chooses thier role
        input.nextLine(); // Consume newline

        User newUser;
        if (role == 1) {
            //creates EventGoer object
            newUser = new EventGoer(name, email, phone, password);
        } else if (role == 2) {
            //creates EventOrganizer object
            newUser = new EventOrganizer(name, email, phone, password);
        } else if (role == 3) {
            //creates Administrator object
            newUser = new Administrator(name, email, phone, password);
        } else {
            System.out.println("Invalid role selection.");
            return;
        }
        //adds the created object to the users ArrayList 
        SystemData.users.add(newUser);
        System.out.println("Registration successful! You can now login.");

    }

    private static boolean isValidPassword(String password) {
        // Minimum 8 characters
        if (password.length() < 8) {
            System.out.println("Password must be at least 8 characters long.");
            return false;
        }

        // At least one uppercase letter
        if (!password.matches(".*[A-Z].*")) {
            System.out.println("Password must contain at least one uppercase letter.");
            return false;
        }

        // At least one lowercase letter
        if (!password.matches(".*[a-z].*")) {
            System.out.println("Password must contain at least one lowercase letter.");
            return false;
        }

        // At least one digit
        if (!password.matches(".*\\d.*")) {
            System.out.println("Password must contain at least one digit.");
            return false;
        }

        // At least one special character
        if (!password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            System.out.println("Password must contain at least one special character.");
            return false;
        }

        return true;
    }

    private static boolean isValidPhoneNumber(String phone) {
        // Check if starts with 05 and has exactly 10 digits
        if (!phone.matches("05\\d{8}")) {
            System.out.println("Invalid phone number. Must start with 05 and be 10 digits long.");
            return false;
        }
        return true;
    }
}
