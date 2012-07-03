package com.fortysix.seleniumTests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Workday
 * User: johnwelsh
 * Date: 7/2/12
 * Time: 10:08 PM
 */
public class SeleniumTestRunner {
    private Logger logger = Logger.getLogger("testRunner");
    private ArrayList<FortysixSeleniumTest> tests = new ArrayList<FortysixSeleniumTest>();
    private ArrayList<WebDriver> drivers = new ArrayList<WebDriver>();

    public SeleniumTestRunner() {
        FirefoxDriver firefox = new FirefoxDriver();
        HtmlUnitDriver htmlUnit = new HtmlUnitDriver();
        drivers.add(firefox);
        drivers.add(htmlUnit);
    }
    
    public void addTestToRunner(FortysixSeleniumTest test) {
        tests.add(test);
    }
    
    public void addTestsToRunner(Collection<FortysixSeleniumTest> tests) {
        tests.addAll(tests);
    }
    
    private Boolean runTestWithDriver(FortysixSeleniumTest test, WebDriver driver) {
        logger.log(Level.INFO, "Running test: " + test.getTestName());
        Boolean result = test.runTestWithDriver(driver);
        if (result) {
            logger.log(Level.INFO, "Test succeeded!");
        } else {
            logger.log(Level.INFO, "Test failed :(");
        }
        return result;
    }
    
    public void runTests() {
        int totalSuccesses = 0;
        int totalFailures = 0;
        for (WebDriver driver : drivers) {
            int successes = 0;
            int failures = 0;
            logger.log(Level.INFO, "Running tests with driver: " + driver.toString());
            for (FortysixSeleniumTest test : tests) {
                Boolean result = runTestWithDriver(test, driver);
            }
            totalFailures += failures;
            totalSuccesses += successes;

            logger.log(Level.INFO, "Successes: " + successes + "   Failures: " + failures);
        }
        logger.log(Level.INFO, "Total successes: " + totalSuccesses + "   Total failures: " + totalFailures);
    }
}
