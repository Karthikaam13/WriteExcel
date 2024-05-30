package task19;

import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class PossibleLocators {

	public static void main(String[] args) throws InterruptedException {
System.setProperty("webdriver.driver.chrome", "\"E:\\\\ChromeDriver\\\\chromedriver-win64\\\\chromedriver.exe\\\"");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.guvi.in/register");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// Clicking SignUp with Google button using css selector
		driver.findElement(By.cssSelector("div[aria-labelledby=\"button-label\"]")).click();
		Thread.sleep(2000);
		
		// Store the handle of the default window
        String defaultWindowHandle = driver.getWindowHandle();
		
		// Get handles of all windows
        Set<String> windowHandles = driver.getWindowHandles();

        // Loop through each handle
        for (String windowHandle : windowHandles) {
            // Switch to the window with the given handle
            driver.switchTo().window(windowHandle);
        
         driver.manage().window().maximize();
         Thread.sleep(1000);

         // Perform actions in the switched window
          System.out.println("Title of the window: " + driver.getTitle());
       // Close the window if it's not the default window
          if (!windowHandle.equals(defaultWindowHandle)) {
              driver.close();
          }
        }
        // Switch back to the default window
        driver.switchTo().window(defaultWindowHandle);
        
        WebElement fullName = driver.findElement(By.id("name"));
		fullName.sendKeys("Karthikaa");
		
		 // Locate the alert message element
        WebElement alertMessage = driver.findElement(By.cssSelector("small[class=certificates]"));

        // Get the text of the alert message
        String alertText = alertMessage.getText();
        System.out.println("Alert Message Text: " + alertText);

        // Verify the text of the alert message
        String expectedText = "*This name will appear in certificates";
        if (alertText.equals(expectedText)) {
            System.out.println("Verification Passed: Alert message text is correct");
        } else {
            System.out.println("Verification Failed: Alert message text is incorrect");
        }
        
        WebElement email =  driver.findElement(By.id("email"));
        email.sendKeys("nsvkbjh123.com");
        Thread.sleep(2000);
        
        // Locate the email alert message
        WebElement emailAlert = driver.findElement(By.xpath("//div[text()=\" Hmm...that doesnt look like an email address. Try again.\"]"));  
        
        // Get the text of an email alert message
        String emailAlertText = emailAlert.getText();
        System.out.println("Email Alert Text :" +emailAlertText);
        
        // Verify the text of the email alert message
        String expectedEmailText = "Hmm...that doesnt look like an email address. Try again.";
        if(emailAlertText.equals(expectedEmailText)) 
        {
        	System.out.println("Verification Passed : Alert message for invalid email is correct");
        } else 
        {
        	System.out.println("Verification Failed	 : Alert message for invalid email is incorrect");
        }
        Thread.sleep(1000);
        email.clear();
        email.sendKeys("karthikaa@gmail.com");
        Thread.sleep(1000);
        
        WebElement pwd = driver.findElement(By.id("password"));
        pwd.sendKeys("123456789");
        
     // Locate the password alert messages
        List<WebElement> pwdAlerts = driver.findElements(By.cssSelector("ul.password-cond > li"));

        // Define expected texts for each alert message
        String[] expectedPwdTexts = {
            "Atleast 6 characters",
            "Atleast 1 numeral",
            "Atleast 1 alphabet"
        };

        // Initialize a flag for verification
        boolean verificationPassed = true;

        // Get the text of each password alert message and verify it
        for (int i = 0; i < pwdAlerts.size(); i++) {
            String pwdAlertText = pwdAlerts.get(i).getText();
            System.out.println("Pwd Alert Text" + (i + 1) + ": " + pwdAlertText);
            
            // Verify the text of the password alert message
            if (!pwdAlertText.equals(expectedPwdTexts[i])) {
                verificationPassed = false;
                break; // Exit the loop if verification fails for any message
            }
        }

        // Print verification result
        if (verificationPassed) {
            System.out.println("Verification Passed: All alert messages for invalid password are correct");
        } else {
            System.out.println("Verification Failed: At least one alert message for invalid password is incorrect");
        }
       Thread.sleep(1000);
       pwd.clear();
       pwd.sendKeys("Guvi@123");
       
      WebElement mobNum = driver.findElement(By.id("mobileNumber"));
      mobNum.sendKeys("12345");
      Thread.sleep(1000);
   // Locate the email alert message
      WebElement mobAlert = driver.findElement(By.xpath("//div[text()=\" Hmm...that doesnt look like an mobile number. Try again.\"]"));  
      
      // Get the text of an email alert message
      String mobAlertText = emailAlert.getText();
      System.out.println("Email Alert Text :" +emailAlertText);
      
      // Verify the text of the email alert message
      String expectedmobText = " Hmm...that doesnt look like an mobile number. Try again.";
      if(emailAlertText.equals(expectedEmailText)) 
      {
      	System.out.println("Verification Passed : Alert message for invalid Mob Num is correct");
      } else 
      {
      	System.out.println("Verification Failed	 : Alert message for invalid Mob Num is incorrect");
      }
      mobNum.clear();
      mobNum.sendKeys("9874125620");
      Thread.sleep(1000);
      
      driver.findElement(By.tagName("a")).click();
      Thread.sleep(2000);
      
      
       
       driver.quit();
		
		
		
	

	}

}
