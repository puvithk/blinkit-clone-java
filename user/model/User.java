package user.model;

import java.time.LocalDate;

public class User {
    private int id ;

    // phone number
    private String phoneNumber;

    // User name
    private String username;

    // Email id
    private String email;

    // Date of birth
    private LocalDate dob;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public User(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public User(int id) {
        this.id = id;
    }

    public User(String username, String email, LocalDate dob) {


        this.username = username;
        this.email = email;
        this.dob = dob;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", dob=" + dob +
                '}';
    }
}
