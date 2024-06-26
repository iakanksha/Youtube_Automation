package demo.WrapperActions;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.JavascriptExecutor;

public class WrapperClass {

    public static void sendKeysWrapper(WebDriver driver, By locator, String textToEnter){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement textBox = driver.findElement(locator);
            textBox.click();
            textBox.sendKeys(textToEnter);
            textBox.sendKeys(Keys.ENTER);
        }catch (Exception e) {
            System.out.println("Unable to enter text! " + e.getMessage());
        }
    }

    public static void clickElementWrapper(WebDriver driver, By locator){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement button = driver.findElement(locator);
            button.click();
        }catch (Exception e) {
            System.out.println("Unable to click on element! " + e.getMessage());
        }
    }

    public static String getTextWraper(WebDriver driver, By locator){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement element = driver.findElement(locator);
            String text = element.getText();
            return text;
        }catch (Exception e) {
                System.out.println("Exception Occured! " + e.getMessage());
                return "";
        }
    }

    public static void getTextAndPrint(WebDriver driver, By locator){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
            WebElement element = driver.findElement(locator);
            String text = element.getText();
            System.out.println(text);
        }catch (Exception e) {
                System.out.println("Exception Occured! " + e.getMessage());
        }
    }


    public static void scrollToRight(WebDriver driver, By locator){
        try{
            WebElement element = driver.findElement(locator);
            while(element.isDisplayed())
            element.click();
        }catch(Exception e) {
            System.out.println("Exception Occured! " + e.getMessage()); 
        }
    }

    public static void waitForElement(WebDriver driver, By locator){
        try{
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
            wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        }catch(Exception e){
            System.out.println("Element not loading "+ e.getMessage());
        }
    }


    public static int getLikeCount(WebDriver driver, By locator){
        try{
            WebElement element = driver.findElement(locator);
            if(element.isDisplayed()){
                return Integer.parseInt(element.getText());
            }
            else
                return 0;
        }catch(Exception e){
            System.out.println("Exception occured!"+e.getMessage());
            return -1;
        }
    }

    public static long getViewCount(WebDriver driver, WebElement element){
        try{
            String[] views = element.getText().split(" ");
            String view = views[0];
            long multiplier= 1;
            switch(view.charAt(view.length()-1)){
                case 'K':
                    multiplier = 1000;
                    break;
                case 'M':
                    multiplier = 1000000;
                    break;
                case 'B':
                    multiplier = 1000000000;
                    break;
            }   
            return Long.parseLong(view.substring(0, view.length()-1));
        }catch(Exception e){
            System.out.println("Exception occured!"+e.getMessage());
            return -1;
        }
    }
    
    public static int getNoOfTracks(WebDriver driver, By locator){
        try{
            WebElement element = driver.findElement(locator);
            String[] tracks = element.getText().split(" ");
            return Integer.parseInt(tracks[0]);
        }catch(Exception e){
            System.out.println("Unable to retieve tarck count"+e.getMessage());
            return -1;
        }
    }
}
    

