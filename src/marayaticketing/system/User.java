/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package marayaticketing.system;


public abstract class User {

    // attributes 
    int userID;
    String name;
    String email;
    String phone;
    private static int nextID = 1;//static attribute to assign a value to userID
    String password;

    // parameterized constructor 
    public User(String name, String email, String phone, String password) {
        this.userID = nextID++;//incrementation of nextID so a unique userID is assigned
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    //empty constructor
    public User() {
    }

    // getters and setters
    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        if (!phone.matches("05\\d{8}")) {
            throw new IllegalArgumentException("Phone number must start with 05 and be 10 digits");
        }
        this.phone = phone;
    }

    public static int getNextID() {
        return nextID;
    }

    public static void setNextID(int nextID) {
        User.nextID = nextID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password.length() < 8
                || !password.matches(".*[A-Z].*")
                || !password.matches(".*[a-z].*")
                || !password.matches(".*\\d.*")
                || !password.matches(".*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?].*")) {
            throw new IllegalArgumentException("Password does not meet requirements");
        }
        this.password = password;
    }

    // a method to display the user interface(menu)
    public abstract void showMenu();

    //method to update user information
    public void updateInfo(String email, String phone) {
        this.email = email;//assigns new email value
        this.phone = phone;//assigns new phone value
        System.out.println("Info updated");
    }

// a to string method that prints the user's information 
    @Override
    public String toString() {
        return "ID: " + userID + " | Name:" + name + " | Email:" + email + " | Phone:" + phone;
    }

}
