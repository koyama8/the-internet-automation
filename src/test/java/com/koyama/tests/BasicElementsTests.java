package com.koyama.tests;

import static org.testng.Assert.assertEquals;

import java.time.Duration;
import java.util.Iterator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

import org.testng.Assert;

public class BasicElementsTests {

    protected WebDriver driver;
    protected WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    
    
    @Test
    public void shouldOpenABTestingPageAndDisplayParagraph(){
    	
        driver.get("https://the-internet.herokuapp.com/");

        By linkABTesting  = By.linkText("A/B Testing");
        By paragrafoABTesting  = By.xpath("//div[@id='content']//div[@class='example']/p");
        
        wait.until(ExpectedConditions.elementToBeClickable(linkABTesting)).click();
        wait.until(ExpectedConditions.urlContains("/abtest"));
        
        
        WebElement textoParagrafo = wait.until(
        		ExpectedConditions.visibilityOfElementLocated(paragrafoABTesting)
        		);
        
        System.out.print(textoParagrafo.getText());     
              
    }
    
    @Test
    public void shouldAddMultipleElementsSuccessfully() throws InterruptedException{
    	
      	driver.get("https://the-internet.herokuapp.com/");
      	
      	By linkAddRemoveElements = By.linkText("Add/Remove Elements");
      	By buttonaAdd = By.cssSelector("button[onclick='addElement()']");
      	
      	wait.until(ExpectedConditions.elementToBeClickable(linkAddRemoveElements)).click();
      	wait.until(ExpectedConditions.urlContains("/add_remove_elements/"));
      	
      	int n = 5;
      	
      	
      	for(int i = 0; i <= n; i++) {
      		wait.until(ExpectedConditions.elementToBeClickable(buttonaAdd)).click();
      		Thread.sleep(1500);
      	}
      	  
    }
    
    @Test
    public void shouldOpenBrokenImagesPageSuccessfully(){
    	
      	driver.get("https://the-internet.herokuapp.com/");

      	By brokenImages = By.linkText("Broken Images");
      	By textBroken = By.xpath("//div[@id='content']//div[@class='example']/h3");
      	
      	
      	wait.until(ExpectedConditions.elementToBeClickable(brokenImages)).click();
        wait.until(ExpectedConditions.urlContains("/broken_images"));
        
        WebElement h3 = wait.until(ExpectedConditions.visibilityOfElementLocated(textBroken));
        
        System.out.println(h3.getText());

        Assert.assertEquals("Broken Images", h3.getText());
    }
    
    @Test
    public void shouldAddMultipleElementsChallengingDOM()  throws InterruptedException {
      	  	   
    	   driver.get("https://the-internet.herokuapp.com/");
       By challenging = By.linkText("Challenging DOM");
       By buttonqux = By.cssSelector("a.button.success");
       By text = By.cssSelector("p");
       
       wait.until(ExpectedConditions.elementToBeClickable(challenging)).click();
       wait.until(ExpectedConditions.urlContains("/challenging_dom"));
       
       int n = 5;
       
       for(int i = 0; i < n; i++) {
    	      wait.until(ExpectedConditions.elementToBeClickable(buttonqux)).click();
    	      Thread.sleep(1500);
       }
                       
       String texto = "The hardest part in automated web testing is finding the best locators (e.g., ones that well named, unique, and unlikely to change). It's more often than not that the application you're testing was not built with this concept in mind. This example demonstrates that with unique IDs, a table with no helpful locators, and a canvas element.";
       WebElement p = wait.until(ExpectedConditions.visibilityOfElementLocated(text));
       
        Assert.assertEquals(texto, p.getText());
       
    }
    
    @Test
    public void shouldcheckboxes() throws InterruptedException{
    	
 	   driver.get("https://the-internet.herokuapp.com/");

 	   By checkboxes = By.linkText("Checkboxes");
 	   By input = By.cssSelector("#checkboxes input[type='checkbox']");
 	   
 	   wait.until(ExpectedConditions.elementToBeClickable(checkboxes)).click();
 	   wait.until(ExpectedConditions.urlContains("/checkboxes"));
 	   	
       List<WebElement> list = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(input)); 

       int n = 2;
       for(int i = 0; i < n; i++) {
    	     
    	    list.get(i).click();
    	    Thread.sleep(2000);
       }
    	
    }
    
    @Test
    public void shouldContextMenu() throws InterruptedException{
    	
  	   driver.get("https://the-internet.herokuapp.com/");
  	   
  	   By menu = By.linkText("Context Menu");
  	   By text = By.cssSelector("#content p:nth-of-type(1)");
  	   
  	   wait.until(ExpectedConditions.elementToBeClickable(menu)).click();
  	   wait.until(ExpectedConditions.urlContains("/context_menu"));
  	   
  	   WebElement p = wait.until(ExpectedConditions.visibilityOfElementLocated(text));
  	   
  	   System.out.println(p.getText());
  	   
  	   String context = "Context menu items are custom additions that appear in the right-click menu";
  	   
  	   Assert.assertEquals(context, p.getText());

    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}