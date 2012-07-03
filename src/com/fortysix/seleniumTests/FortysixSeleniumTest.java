package com.fortysix.seleniumTests;

import org.openqa.selenium.WebDriver;

/**
 * Workday
 * User: johnwelsh
 * Date: 7/2/12
 * Time: 10:09 PM
 */
public interface FortysixSeleniumTest {
    public String getTestName();
    public Boolean runTestWithDriver(WebDriver driver);
}
