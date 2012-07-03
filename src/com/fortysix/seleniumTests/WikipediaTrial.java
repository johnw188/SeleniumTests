package com.fortysix.seleniumTests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Workday
 * User: johnwelsh
 * Date: 7/2/12
 * Time: 8:46 PM
 */
public class WikipediaTrial {
    WebDriver driver;
    Logger logger = Logger.getLogger("exampleLogger");

    public WikipediaTrial(WebDriver driver) {
        this.driver = driver;
    }

    public void testWikipediaSearch() {

        //User input for search field
        System.out.print("Enter wikipedia search term: ");
        String searchFor = "";
        BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
        try {
            searchFor = userInput.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(searchFor);

        driver.get("http://www.wikipedia.org");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("search"));

        // Enter something to search for
        element.sendKeys(searchFor);

        // Now submit the form. WebDriver will find the form for us from the element
        element.submit();

        // Check the title of the page
        logger.log(Level.INFO, "Page title is: " + driver.getTitle());

        List<WebElement> elements = driver.findElements(By.tagName("p"));

        WebElement firstParagraph = null;
        for (WebElement webElement : elements) {
            //logger.log(Level.INFO, webElement.getText());
            if (webElement.findElements(By.tagName("span")).size() == 0) {
                firstParagraph = webElement;
                break;
            }
        }

        if (firstParagraph != null) {
            logger.log(Level.INFO, "First paragraph is: " + firstParagraph.getText());
        } else {
            logger.log(Level.INFO, "Failed to find first paragraph");
        }


        //Close the browser
        driver.quit();
    }
}
