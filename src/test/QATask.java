/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

/**
 *
 * @author dobri
 */
public class QATask {

    public static void main(String[] args) throws InterruptedException {
        System.setProperty(ConfigurationFile.FIREFOX_DRIVER, ConfigurationFile.FIREFOX_DRIVER_LOCATION);
        WebDriver driver = new FirefoxDriver();
        driver.get(ConfigurationFile.SEARCH_ENGINE_LOCATION);
        verifySearchTextInTextbox(driver);
        Thread.sleep(1000,0);
        clickGoogleSearchButton(driver);
        checkDoSiteExistInResults(driver);
        Thread.sleep(1000,0);
        driver.quit();
    }

    public static void verifySearchTextInTextbox(WebDriver driver){
        try {
            WebElement googleTextbox = driver.findElement(By.name(ConfigurationFile.GOOGLE_TEXTBOX_NAME_ATTRIBUTE));
            googleTextbox.sendKeys(ConfigurationFile.WORD_TO_GOOGLE);
            String expectedText = ConfigurationFile.WORD_TO_GOOGLE;
            String textInTextBox = googleTextbox.getAttribute(ConfigurationFile.ATTRIBUTE_VALUE);
            
            if(expectedText.equals(textInTextBox)){
                System.out.println("Verify search text in textbox test PASSED");
            } else {
                System.out.println("Verify search text in textbox test FAILED");
            }
        } catch (Exception e) {
            System.out.println("Google textbox not found!");
        }
    }
    
    public static void clickGoogleSearchButton(WebDriver driver){
        try {
            WebElement googleSearchButton = driver.findElement(By.name(ConfigurationFile.GOOGLE_BUTTON_NAME_ATTRIBUTE));
            googleSearchButton.click();
            System.out.println("Click Google search button test PASSED");
        } catch (Exception e) {
            System.out.println("Google search button not found!");
        }
    }
    
    public static boolean checkDoSiteExistInResults(WebDriver driver){
        List<WebElement> rows = driver.findElements(By.tagName(ConfigurationFile.GOOGLE_SITE_CONTAINER));
        for(WebElement element : rows){
            if(element.getAttribute(ConfigurationFile.ATTRIBUTE_INNER_HTML).contains(ConfigurationFile.SITE_NAME_TO_MATCH_ON_GOOGLE)){
                System.out.println("Check do site exist in results test PASSED");
                return true;
            }
        }
        System.out.println("Check do site exist in results test FAILED");
        return false;
    }
}
