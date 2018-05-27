
import org.openqa.selenium.By;		
import org.openqa.selenium.WebDriver;		
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;

import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;


public class Bot {

public void fillForm(WebDriver driver) {
 int MINUTES = 2; // The delay in minutes
 Timer timer = new Timer();
 timer.schedule(new TimerTask() {
 @Override
 public void run() { // Function runs every MINUTES minutes.
  String baseUrl = "https://burghquayregistrationoffice.inis.gov.ie/Website/AMSREG/AMSRegWeb.nsf/AppSelect";
  driver.get(baseUrl);
	       
  // Select Category
  new Select(driver.findElement(By.id("Category"))).selectByVisibleText("Work");
	     
  // Select Sub Category
  new Select(driver.findElement(By.id("SubCategory"))).selectByVisibleText("Work Permit Holder");
	      
  // Select GNIB Confirmation
  new Select(driver.findElement(By.id("ConfirmGNIB"))).selectByVisibleText("Yes");
	      
  // Enter GNIB Number 
  driver.findElement(By.id("GNIBNo")).clear();
  driver.findElement(By.id("GNIBNo")).sendKeys("747284");
	                    
  // GNIB Expiry Date calendar
  WebElement ClickGNIBExp  = driver.findElement(By.xpath("//*[@id=\"GNIBExDT\"]"));
  Actions a = new Actions(driver);
  a.moveToElement(ClickGNIBExp).click().build().perform();
  WebElement selectGNIBYear  =driver.findElement(By.xpath("//div[3]/table/tbody/tr/td/span[10]"));
  a.moveToElement(selectGNIBYear).click().build().perform();
  WebElement selectGNIBMonth  =driver.findElement(By.xpath("//span[5]"));
  a.moveToElement(selectGNIBMonth).click().build().perform();
  WebElement selectGNIBDay  =driver.findElement(By.xpath("//tr[4]/td[4]"));
  a.moveToElement(selectGNIBDay).click().build().perform();
	     
  // Click user declaration check box
  driver.findElement(By.id("UsrDeclaration")).click();
	      
  // Enter First Name
  driver.findElement(By.id("GivenName")).clear();
  driver.findElement(By.id("GivenName")).sendKeys("Siddharth");
	     
  // Enter Last Name
  driver.findElement(By.id("SurName")).clear();
  driver.findElement(By.id("SurName")).sendKeys("Sharma");
	                    
  // Enter DOB 
  WebElement selectDOB  = driver.findElement(By.xpath("//*[@id=\"DOB\"]"));
  Actions a1 = new Actions(driver);
  a1.moveToElement(selectDOB).click().build().perform();
  WebElement findDOBYear1  =driver.findElement(By.xpath("//div[3]/table/thead/tr/th[1]"));
  a1.moveToElement(findDOBYear1).click().build().perform();
  WebElement findDOBYear2  =driver.findElement(By.xpath("//div[3]/table/thead/tr/th[1]"));
  a1.moveToElement(findDOBYear2).click().build().perform();
  WebElement selectDOBYear  =driver.findElement(By.xpath("//div[3]/table/tbody/tr/td/span[5]"));
  a1.moveToElement(selectDOBYear).click().build().perform();
  WebElement selectDOBMonth  =driver.findElement(By.xpath("//span[8]"));
  a1.moveToElement(selectDOBMonth).click().build().perform();
  WebElement selectDOBDay  =driver.findElement(By.xpath("//tr[2]/td[5]"));
  a1.moveToElement(selectDOBDay).click().build().perform();
	     
  // Select Nationality
  new Select(driver.findElement(By.id("Nationality"))).selectByVisibleText("India, Republic of");
	     
  // Enter Email
  driver.findElement(By.id("Email")).clear();
  driver.findElement(By.id("Email")).sendKeys("siddharthsharma0508@gmail.com");
	     
  // Confirm Email
  driver.findElement(By.id("EmailConfirm")).clear();
  driver.findElement(By.id("EmailConfirm")).sendKeys("siddharthsharma0508@gmail.com");
	      
  // Select Family Application drop down 
  new Select(driver.findElement(By.id("FamAppYN"))).selectByVisibleText("No");
	     
  // Passport drop down check
  new Select(driver.findElement(By.id("PPNoYN"))).selectByVisibleText("Yes");
	     
  // Enter Passport Number
  driver.findElement(By.id("PPNo")).clear();
  driver.findElement(By.id("PPNo")).sendKeys("Z4503219");
	      
  // Click button "Look For Appointments" to submit details
  driver.findElement(By.id("btLook4App")).click();
 
  // To check if there are any errors in the entered information
  if(driver.findElement(By.xpath("//*[@id=\"dvMsgs\"]")).isDisplayed()) {
	  String innerT = driver.findElement(By.xpath("//*[@id=\"dvMsgs\"]")).getText();
	  System.out.println(innerT);
	  timer.cancel();
	  timer.purge();
  }
  
  // Choose type of appointment search
  new Select(driver.findElement(By.id("AppSelectChoice"))).selectByVisibleText("closest to today");
	     
  // Click "Search For Appointments" button
  driver.findElement(By.id("btSrch4Apps")).click();
	                    
  // Retrieve message from final button click
  String title =driver.findElement(By.xpath("//*[@id=\"dvAppLst\"]")).getText();
  String innerText = driver.findElement(By.xpath("//*[@id=\"dvAppOptions\"]")).getText();
  System.out.println(innerText);

  //driver.close();                
  
  }
 }, 0, 1000 * 60 * MINUTES); // 1000 milliseconds in a second * 60 per minute * the MINUTES variable. 
}

 public static void main(String[] args) {
 // TODO Auto-generated method stub
 WebDriver driver;
 try {
	System.setProperty("webdriver.chrome.driver","//Users//samrudhsharma//chromedriver");
	 driver = new ChromeDriver();
	 driver.manage().window().maximize();
	 Bot b = new Bot();
	 b.fillForm(driver);
 } catch (Exception e) {
	e.printStackTrace();
 }		        
 }
}
