package task20;
import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class GuviSignupLogin {

	public static void main(String[] args) throws InterruptedException   {
System.setProperty("webdriver.driver.chrome", "\"E:\\\\ChromeDriver\\\\chromedriver-win64\\\\chromedriver.exe\\\"");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.guvi.in");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// clicking Signup button 
		driver.findElement(By.xpath("//li[@class=\"nav-item python-signup-btn\"]/a[text()=\"Sign up\"]")).click();
		Thread.sleep(1000);
		
		
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
        email.sendKeys("VilvaamDass123@gmail.com");
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
      
      // Get the text of an Mobile alert message
      String mobAlertText = mobAlert.getText();
      System.out.println("Mobile Alert Text :" +mobAlertText);
      
      // Verify the text of the email alert message
      String expectedmobText = "Hmm...that doesnt look like an mobile number. Try again.";
      if(mobAlertText.equals(expectedmobText)) 
      {
      	System.out.println("Verification Passed : Alert message for invalid Mob Num is correct");
      } else 
      {
      	System.out.println("Verification Failed	: Alert message for invalid Mob Num is incorrect");
      }
      mobNum.clear();
      mobNum.sendKeys("6389745410");
      Thread.sleep(1000);
      
      driver.findElement(By.id("signup-btn")).click();
      Thread.sleep(2000);
      
      //Select Current Profile
	 WebElement currentProfile = driver.findElement(By.id("profileDrpDwn"));
	 currentProfile.click();
	 Select profileIT =  new Select(currentProfile);
	 
	 // Select the option "Working professional in IT" by visible text
	 profileIT.selectByVisibleText("Working professional in IT");
    
     
     //  verify the Profile selection
     WebElement selectedOption = profileIT.getFirstSelectedOption();
     String selectedText = selectedOption.getText();
     System.out.println("Selected option: " + selectedText);
     
     // Select Degree
     WebElement degree = driver.findElement(By.id("degreeDrpDwn"));
     degree.click();
     Select anyGraduation = new Select(degree);
     
     // Select the optipn "B.E. / B.Tech. Computer Science" by visible  text
     anyGraduation.selectByVisibleText("B.E. / B.Tech. Computer Science");
     
     // verify the degree selection
     WebElement degreeSelection = anyGraduation.getFirstSelectedOption();
     String selectedDegree = degreeSelection.getText();
     System.out.println("Selected Degree is:" + selectedDegree);
     
     // Enter Year of Passing
     WebElement yop = driver.findElement(By.id("year"));
     yop.sendKeys("2014");
     
     // Click Submit button
     driver.findElement(By.id("details-btn")).click();
     Thread.sleep(2000);
     
     // Check for a successful registration message or redirection
     WebElement successMessage = driver.findElement(By.xpath("//h1[text()=\"Almost Done! Check Your Inbox!\"]"));
     if(successMessage.isDisplayed()) {
    	 System.out.println("Registration successful. Message: " + successMessage.getText());
     } else {
         System.out.println("Registration failed. No success message found.");
     }
     // Refresh the browser window
     driver.navigate().refresh();
     Thread.sleep(2000); 
     driver.get("https://www.guvi.in");
     Thread.sleep(2000); 
     
     // Login the Account
     driver.findElement(By.xpath("//li[@class=\"nav-item python-login-btn\"]/a[text()=\"Login\"]")).click();
     WebElement emailAddress = driver.findElement(By.id("email"));
     emailAddress.sendKeys("karthikaam13@gmail.com");
     
     WebElement password = driver.findElement(By.id("password"));
     password.sendKeys("Ommuruga81772716*");
     
     //Visibility icon check
     driver.findElement(By.id("togglePassword")).click();
     
     driver.findElement(By.id("login-btn")).click();
     
     Thread.sleep(2000);
     // Check for a successful registration message or redirection
     String titleofPage = driver.getTitle();
     System.out.println(titleofPage);
     
     String expectedSuccessTitle = "GUVI | courses";
     
     if(titleofPage.equals(expectedSuccessTitle)) {
    	 
    	 System.out.println("Registration successful. Redirected to: " + titleofPage);
     } else {
         System.out.println("Registration failed or unexpected redirection. Current URL: " + titleofPage);
     } 
     
     driver.close();
		
		
		
		

	}

}
