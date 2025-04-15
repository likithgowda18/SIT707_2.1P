package sit707_week2;

public class Main {

    public static void main(String[] args) {
        // Set the URL for the registration page
        String url = "https://www.officeworks.com.au/app/identity/create-account"; 
        SeleniumOperations.officeworks_registration_page(url);
    }
}
