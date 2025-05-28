/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marayaticketing.system;

import java.io.*;
import java.util.*;

public class FileHandler {

    //creates files for the static ArrayLists
    private static final String DATA_DIR = "data/";
    private static final String USERS_FILE = DATA_DIR + "users.txt";
    private static final String EVENTS_FILE = DATA_DIR + "events.txt";
    private static final String BOOKINGS_FILE = DATA_DIR + "bookings.txt";
    private static final String PENDING_BOOKINGS_FILE = "pending_bookings.txt";

    // makes sure the files exists otherwise it'll make one
    private static void ensureFileExists(String filePath) {
        File file = new File(filePath);
        try {
            if (!file.exists()) {
                file.createNewFile();
                System.out.println("Created missing file: " + filePath);
            }
        } catch (IOException e) {
            System.err.println("Failed to create file: " + filePath);
        }
    }

    // method to save all the data stored in all the static ArrayLists
    public static void saveAll() {
        createDataDir();
        saveUsers();
        saveEvents();
        saveBookings();
    }

    // method to load all data stored in all the files
    public static void loadAll() {
        // Ensure all files exist before loading
        ensureFileExists(USERS_FILE);
        ensureFileExists(EVENTS_FILE);
        ensureFileExists(BOOKINGS_FILE);
        ensureFileExists(PENDING_BOOKINGS_FILE);

        SystemData.users = loadUsers();
        SystemData.events = loadEvents();
        SystemData.bookings = loadBookings();
    }

    // creates a directory for the files
    private static void createDataDir() {
        File dir = new File(DATA_DIR);
        if (!dir.exists()) {
            dir.mkdir();
        }
    }

    // uses printWriter to write data into the file for users
    private static void saveUsers() {
        try (PrintWriter writer = new PrintWriter(USERS_FILE)) {
            for (User user : SystemData.users) {
                writer.println(user.getUserID() + "," + user.getName() + ","
                        + user.getEmail() + "," + user.getPhone() + ","
                        + user.getClass().getSimpleName() + "," + user.getPassword());
            }
        } catch (IOException e) {
            System.err.println("Error saving users: " + e.getMessage());
        }
    }

    // uses printWriter to write data into the file for events
    private static void saveEvents() {
        try (PrintWriter writer = new PrintWriter(EVENTS_FILE)) {
            for (Event event : SystemData.events) {
                writer.println(
                        event.getEventID() + "," + event.getName() + ","
                        + event.getDate() + "," + event.getTime() + ","
                        + event.getDescription() + "," + event.getAvailableTickets() + ","
                        + event.getSeatType() + "," + event.getTicketPrice()
                );
            }
        } catch (IOException e) {
            System.err.println("Error saving events: " + e.getMessage());
        }
    }

    // uses printWriter to write data into the file for bookings
    private static void saveBookings() {
        try (PrintWriter writer = new PrintWriter(BOOKINGS_FILE)) {
            for (Booking booking : SystemData.bookings) {
                writer.println(
                        booking.getBookingID() + "," + booking.getUserID() + ","
                        + booking.getEvent().getEventID() + "," + booking.isApproved()
                );
            }
        } catch (IOException e) {
            System.err.println("Error saving bookings: " + e.getMessage());
        }
    }

    // uses scanner and split to read from the file for users
    private static ArrayList<User> loadUsers() {
        ArrayList<User> users = new ArrayList<>();
        File file = new File(USERS_FILE);
        if (!file.exists()) {
            return users;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];
                String email = data[2];
                String phone = data[3];
                String role = data[4];
                String password = data[5];

                User user;
                try {
                    if (role.equals("EventGoer")) {
                        user = new EventGoer(name, email, phone, password);
                    } else if (role.equals("EventOrganizer")) {
                        user = new EventOrganizer(name, email, phone, password);
                    } else {
                        user = new Administrator(name, email, phone, password);
                    }
                    user.setPhone(phone);
                    user.setPassword(password);
                    user.userID = id;
                    users.add(user);
                } catch (IllegalArgumentException e) {
                    System.err.println("Skipping invalid user data: " + e.getMessage());
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading users: " + e.getMessage());
        }
        return users;
    }

    // uses scanner and split to read from the file for events
    private static ArrayList<Event> loadEvents() {
        ArrayList<Event> events = new ArrayList<>();
        File file = new File(EVENTS_FILE);
        if (!file.exists()) {
            return events;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                int id = Integer.parseInt(data[0]);
                String name = data[1];

                // parseInt to convert String to date
                String[] dateParts = data[2].split("/");
                Date date = new Date(
                        Integer.parseInt(dateParts[0]),
                        Integer.parseInt(dateParts[1]),
                        Integer.parseInt(dateParts[2])
                );

                // parseInt to convert String to time
                String[] timeParts = data[3].split(":");
                Time time = new Time(
                        Integer.parseInt(timeParts[0]),
                        Integer.parseInt(timeParts[1])
                );

                //creates event object from data taken from file
                Event event = new Event(
                        name, date, time, data[4],
                        Integer.parseInt(data[5]), data[6],
                        Double.parseDouble(data[7])
                );
                event.setEventID(id);
                events.add(event);
            }
        } catch (IOException e) {
            System.err.println("Error loading events: " + e.getMessage());
        }
        return events;
    }

    // uses scanner and split to read from the file for bookings
    private static ArrayList<Booking> loadBookings() {
        ArrayList<Booking> bookings = new ArrayList<>();
        File file = new File(BOOKINGS_FILE);
        if (!file.exists()) {
            return bookings;
        }

        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String[] data = scanner.nextLine().split(",");
                int bookingID = Integer.parseInt(data[0]);
                int userID = Integer.parseInt(data[1]);
                int eventID = Integer.parseInt(data[2]);
                boolean approved = Boolean.parseBoolean(data[3]);

                // Find the event
                Event event = null;
                for (Event e : SystemData.events) {
                    if (e.getEventID() == eventID) {
                        event = e;
                        break;
                    }
                }

                if (event != null) {
                    //creates booking object 
                    Booking booking = new Booking(userID, event);
                    booking.bookingID = bookingID;
                    booking.approved = approved;
                    //booking object created is added back into the booking arrayList
                    bookings.add(booking);
                }
            }
        } catch (IOException e) {
            System.err.println("Error loading bookings: " + e.getMessage());
        }
        return bookings;
    }
}
