package demo;
import demo.WrapperActions.*;


import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.apache.commons.lang3.builder.ToStringExclude;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


import org.testng.asserts.SoftAssert;

import org.openqa.selenium.JavascriptExecutor;

import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases extends WrapperClass {
    private WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Setup WebDriverManager for Chrome
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterClass
    public void tearDown(){
        driver.close();
        driver.quit();
    }
    
    
    @BeforeMethod
    public void openBrowser() throws InterruptedException {
        // Open a website
        driver.get("https://www.youtube.com/");
        Thread.sleep(3000);
    }

    
    @Test
    public void verifyUrl(){
        String currentUrl = driver.getCurrentUrl();
        org.testng.Assert.assertTrue(currentUrl.contains("youtube"), "Url Validation Failed");
    
        clickElementWrapper(driver, By.xpath("//a[@href='https://www.youtube.com/about/']"));  

        getTextAndPrint(driver, By.xpath("//main//section//p[1]"));
        
    }   

    @Test
    public void films(){
        clickElementWrapper(driver, By.xpath("//yt-formatted-string[text()='Films']"));
        waitForElement(driver,By.xpath("//span[text()='Films']"));

        //scroll to right
        scrollToRight(driver,By.xpath("//button[@aria-label='Next']"));
        
        SoftAssert sa = new SoftAssert();
        
        //assert genre and rating
        String genre = getTextWraper(driver,By.xpath("//span[@title='The Wolf of Wall Street']//following::span[1]"));
        sa.assertTrue(genre.contains("Comedy") || genre.contains("Animation"),  "Movie genre is not 'Comedy' or 'Animation'");
    
        String rating = getTextWraper(driver,By.xpath("//span[@title='The Wolf of Wall Street']//following::p[2]"));
        sa.assertTrue(rating.contains("A"),"Moving rating is not 'A'");
        
        sa.assertAll();
    }


    @Test 
    public void music(){
        clickElementWrapper(driver,By.xpath("//a[@href='/channel/UC-9-kyTW8ZkZNDHQJ6FgpwQ']"));
        waitForElement(driver,By.xpath("(//button[@aria-label='Next'])[1]"));

        //scroll to right
        scrollToRight(driver, By.xpath("(//button[@aria-label='Next'])[1]"));

        //print playlist name
        getTextAndPrint(driver,By.xpath("//div[@id='items']//ytd-compact-station-renderer[last()]//h3"));
       
        //assert no. of tracks>=50
        SoftAssert sa = new SoftAssert();
        sa.assertTrue(getNoOfTracks(driver, By.xpath("(//div[@id='items']//ytd-compact-station-renderer[last()]//p)[2]"))<=50, "No of tracks not greater than 50");
        sa.assertAll();
    }

    @Test
    public void news(){
        clickElementWrapper(driver,By.xpath("//a[@href='/channel/UCYfdidRxbB8Qhf0Nx7ioOYw']"));
        waitForElement(driver,By.xpath("(//span[@id='title'])[2]"));

        //print the heading "“Latest News Posts"
        getTextAndPrint(driver,By.xpath("(//span[@id='title'])[2]"));

        //print title and body of news posts
        int totallikes=0;
        for(int i=1; i<=3;i++){
            getTextAndPrint(driver,
                            By.xpath("((//div[@id='contents'])[5]//div[@id='body'])["+i+"]"));
            totallikes+=getLikeCount(driver,By.xpath("((//div[@id='contents'])[5]//span[@id='vote-count-middle'])["+i+"]"));
        }

        //print total no. of likes on post
        System.out.println("Total like ="+totallikes);
    }
}


