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

public class CartListTest {
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



        // Add items to the cart
        List<WebElement> addToCartButtons = driver.findElements(By.xpath("//button[text()='Add to cart']"));
        for (WebElement addToCartButton : addToCartButtons) {
            addToCartButton.click();
        }

        // Go to the cart
        WebElement cartIcon = driver.findElement(By.className("shopping_cart_link"));
        cartIcon.click();

        // Verify the items in the cart
        List<WebElement> cartItems = driver.findElements(By.className("inventory_item_name"));
        assertEquals(addToCartButtons.size(), cartItems.size(), "Number of items in the cart should match the number of items added.");

        // Print out the names of the items in the cart
        for (WebElement cartItem : cartItems) {
            System.out.println(cartItem.getText());
        }
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}
