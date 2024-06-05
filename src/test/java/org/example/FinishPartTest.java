package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FinishPartTest {

    private WebDriver driver;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test
    public void testAddToCartAndVerifyItems() {
        // Open the website
        driver.get("https://www.saucedemo.com/");


        // Enter user information on checkout page
        testCheckout();
    }

    public void testCheckout() {
        // Fill out the user information
        WebElement firstNameField = driver.findElement(By.id("first-name"));
        WebElement lastNameField = driver.findElement(By.id("last-name"));
        WebElement postalCodeField = driver.findElement(By.id("postal-code"));
        WebElement continueButton = driver.findElement(By.id("continue"));

        firstNameField.sendKeys("John");
        lastNameField.sendKeys("Doe");
        postalCodeField.sendKeys("12345");
        continueButton.click();

        // Verify the user has moved to the Checkout Overview page
        WebElement checkoutOverviewTitle = driver.findElement(By.className("title"));
        assertEquals("Checkout: Overview", checkoutOverviewTitle.getText(), "User should be on the Checkout Overview page.");

        // Continue to checkout complete page
        testCheckoutStepTwo();
    }

    public void testCheckoutStepTwo() {
        // Click the finish button on the checkout overview page
        WebElement finishButton = driver.findElement(By.id("finish"));
        finishButton.click();

        // Verify the user has moved to the Checkout Complete page
        WebElement checkoutCompleteTitle = driver.findElement(By.className("title"));
        assertEquals("Checkout: Complete!", checkoutCompleteTitle.getText(), "User should be on the Checkout Complete page.");

        // Click the back home button to return to the home page
        WebElement backHomeButton = driver.findElement(By.id("back-to-products"));
        backHomeButton.click();

        // Verify the user has returned to the home page
        WebElement inventoryTitle = driver.findElement(By.className("title"));
        assertEquals("Products", inventoryTitle.getText(), "User should be on the Inventory page (home page).");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
