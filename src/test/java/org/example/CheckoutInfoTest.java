package org.example;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CheckoutInfoTest {
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




        // Proceed to checkout
        WebElement checkoutButton = driver.findElement(By.id("checkout"));
        checkoutButton.click();

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

        // You can add assertions here to verify that the user has successfully moved to the next checkout step
        WebElement checkoutOverviewTitle = driver.findElement(By.className("title"));
        assertEquals("Checkout: Overview", checkoutOverviewTitle.getText(), "User should be on the Checkout Overview page.");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
