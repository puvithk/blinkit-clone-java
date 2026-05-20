package ui;

import java.time.LocalDate;

public class Validation {
    public  static boolean validateEmail(String email){
        return email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
    }
    public static boolean validateDob(LocalDate dateOfBirth){

        if(dateOfBirth == null){
            return false;
        }
        // Validate the Date of Birth parameter
        return dateOfBirth.isBefore(LocalDate.now());

    }
}
