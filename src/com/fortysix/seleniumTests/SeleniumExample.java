package com.fortysix.seleniumTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Workday
 * User: johnwelsh
 * Date: 7/2/12
 * Time: 8:46 PM
 */
public class SeleniumExample {
    WebDriver driver;
    Logger logger = Logger.getLogger("exampleLogger");
    
    public SeleniumExample(WebDriver driver) {
        this.driver = driver;
    }

    public void testGoogleSearch() {
        // And now use this to visit Google
        driver.get("http://www.google.com");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));

        // Enter something to search for
        element.sendKeys("Cheese!");

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        logger.log(Level.INFO, "Page title is: " + driver.getTitle());

        // Google's search is rendered dynamically with JavaScript.
        // Wait for the page to load, timeout after 10 seconds
        (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver d) {
                return d.getTitle().toLowerCase().startsWith("cheese!");
            }
        });

        // Should see: "cheese! - Google Search"
        logger.log(Level.INFO, "Page title is: " + driver.getTitle());

        //Close the browser
        driver.quit();
    }
}
