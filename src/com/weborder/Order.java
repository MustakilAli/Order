package com.weborder;

import java.util.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Order {
  public static void main(String[] args) {
    System.setProperty("webdriver.chrome.driver", "/Users/mustakilali/Documents/Selenium Dependencies/drivers/chromedriver");
    WebDriver driver = new ChromeDriver();
    

    //Open browser
    //Go to url http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx (Links to an external site.)Links to an external site.
    driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
    
    //Login using username Tester and password test
    driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
    driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
    driver.findElement(By.id("ctl00_MainContent_login_button")).click();
    
    //Click on Order link
    driver.findElement(By.xpath("//*[@id=\"ctl00_menu\"]/li[3]/a")).click();
    
    //Enter a random quantity between 1 and 100
    driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity")).sendKeys(Integer.toString((int)(1+Math.random()*100)));
    
    //Enter Customer Name: John <middle_name> Smith. Instead of <middle_name> your code should enter a random string every time.
    StringBuilder middle = new StringBuilder();
    middle.append("John ");
    for (int i = 0; i < 8; i++) {
      middle.append((char)(97 + Math.random()*26));
    }
    middle.append(" Smith");
    driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName")).sendKeys(middle.toString());

    //Enter street: 123 Any st
    driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2")).sendKeys("123 Any St");
    
    //Enter City: Anytown
    driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3")).sendKeys("Anytown");
    
    //Enter State: Virginia
    driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox4")).sendKeys("Virginia");
    
    //Enter a random 5 digit number to the zip code field
    Random rnd = new Random();
    driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(Integer.toString(rnd.nextInt(10)));
    driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(Integer.toString(rnd.nextInt(10)));
    driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(Integer.toString(rnd.nextInt(10)));
    driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(Integer.toString(rnd.nextInt(10)));
    driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5")).sendKeys(Integer.toString(rnd.nextInt(10)));
    
    //Select any card type. Every time your code should select a different type.
    int card = (int)(Math.random()*3);
    driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_"+card)).click();
    
    //Enter any card number. If you selected Visa, card number should start with 4. If you selected Master, card number should start with 5. If you selected American Express, card number should start with 3. New card number should be auto generated every time you run the test. Card numbers should be 16 digits for Visa and Master, 15 for American Express.
    switch (card) {
    case 0:
      driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(Integer.toString(4));
      driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(Integer.toString(rnd.nextInt(10)));
      break;
    case 1: 
      driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(Integer.toString(5));
      driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(Integer.toString(rnd.nextInt(10)));
      break;
    case 2: 
      driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(Integer.toString(3)); 
      break;
    }
    
    StringBuilder ccnum = new StringBuilder();
    
    for (int i = 0; i < 14; i++) {
      ccnum.append((int)(Math.random()*10));
    }
    
    driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6")).sendKeys(ccnum.toString());
    
    //Enter any valid expiration date 
    StringBuilder exp = new StringBuilder();
    exp.append(rnd.nextInt(2));
    if (exp.toString().endsWith("0")) {
      exp.append(rnd.nextInt(10));
    } else {
      exp.append(rnd.nextInt(3));
    }
    exp.append("/"+rnd.nextInt(10));
    exp.append(rnd.nextInt(10));
    driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox1")).sendKeys(exp.toString());
    
    //Click on Process
    driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
    
    //Verify than the page contains text New order has been successfully added.
    if(driver.findElement(By.xpath("//*[@id=\"ctl00_MainContent_fmwOrder\"]/tbody/tr/td/div/strong")).getText().contains("New order has been successfully added")) {
      System.out.println("Pass");
    } else {
      System.out.println("Fail");
    }
  }
}

