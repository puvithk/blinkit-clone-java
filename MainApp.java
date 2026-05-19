import ui.AuthUi;
import ui.HomePageUi;

public class MainApp {
    // Define and initialize all the Object or Bean
    // AuthUi object
    public static final AuthUi authUi = new AuthUi();
    private static  final HomePageUi homepageUi = new HomePageUi();
    public static void main(String[] args){
        System.out.println("Hello");
        // Blink it flow
        // Login with phone number -> OTP
        login();
        // Dashboard
       home();
        // Add to cart
        // Payment
        // Track progress




    }
    public static void login(){
        // Direct the Login to Login UI
        authUi.login();
    }
    public static  void home(){
        homepageUi.homePage();
    }
}
