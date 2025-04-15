package sit707_week2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class SeleniumOperations {

    // Sleep method to wait for a certain time
    public static void sleep(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // Main function for automating the registration process on the given site
    public static void officeworks_registration_page(String url) {
        // Step 1: Locate chrome driver folder in the local drive.
        System.setProperty("webdriver.chrome.driver", "E:\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        // Step 2: Use the ChromeDriver to open a Chromium browser.
        System.out.println("Fire up Chrome browser.");
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        System.out.println("Driver info: " + driver);

        // Use WebDriverWait with seconds instead of Duration
        WebDriverWait wait = new WebDriverWait(driver, 10); // Fixed constructor

        // Load the webpage in the Chromium browser.
        driver.get(url);

        // Wait for the first name field to be visible and interact with it.
        WebElement fName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("firstname")));
        System.out.println("Found element: " + fName);
        fName.sendKeys("John");

        // Locate the input field for "Last Name" and enter data.
        WebElement lName = driver.findElement(By.id("lastname"));
        System.out.println("Found element: " + lName);
        lName.sendKeys("Doe");

        // Locate the input field for "Phone Number" and enter data.
        WebElement pNumber = driver.findElement(By.id("phoneNumber"));
        System.out.println("Found element: " + pNumber);
        pNumber.sendKeys("0123456789");

        // Locate the input field for "Email" and enter data.
        WebElement email = driver.findElement(By.id("email"));
        System.out.println("Found element: " + email);
        email.sendKeys("john.doe@example.com");

        // Locate the input field for "Password" and enter data.
        WebElement password = driver.findElement(By.id("password"));
        System.out.println("Found element: " + password);
        password.sendKeys("John@12345");

        // Locate the input field for "Confirm Password" and enter data.
        WebElement cPassword = driver.findElement(By.id("confirmPassword"));
        System.out.println("Found element: " + cPassword);
        cPassword.sendKeys("John@12345");

        // Locate and click the "Create Account" button to register the user using the data-testid
        WebElement button = driver.findElement(By.cssSelector("[data-testid='account-action-btn']"));
        button.click();

        // Wait for the registration page to load and take a screenshot
        wait.until(ExpectedConditions.urlContains("create-account"));

        // Take a screenshot after registration.
        takeScreenshot(driver);

        sleep(2);

        // Close the driver.
        driver.close();
    }

    // Take a screenshot and save it
    private static void takeScreenshot(WebDriver driver) {
        // Ensure the WebDriver is casting correctly to TakesScreenshot
        if (driver instanceof TakesScreenshot) {
            TakesScreenshot screenshotDriver = (TakesScreenshot) driver;
            File scrFile = screenshotDriver.getScreenshotAs(OutputType.FILE);

            try {
                // Ensure the file path exists, if not, create it
                File destinationFile = new File("E://T1 2025//ss_registration.png");
                FileUtils.copyFile(scrFile, destinationFile);
                System.out.println("Screenshot saved successfully at: " + destinationFile.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("Error saving screenshot: " + e.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        // URL of the registration page
        String url = "https://www.officeworks.com.au/app/identity/create-account";
        officeworks_registration_page(url);
    }
}
