package task18;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class FacebookSignup {

	public static void main(String[] args) throws InterruptedException  {
		
		System.setProperty("webdriver.driver.chrome", "\"E:\\ChromeDriver\\chromedriver-win64\\chromedriver.exe\"");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.facebook.com");
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		String currentUrl= driver.getCurrentUrl();
		
		if(currentUrl.equals("https://www.facebook.com/")) 
		{
			System.out.println("Successfully redirected to Facebook homepage: " + currentUrl);
		}
		else 
		{
			System.out.println("Redirection to Facebook homepage failed");
		}
		// clicking create new button
		driver.findElement(By.xpath("//a[text()=\"Create new account\"]")).click();
		
		 // FirstName
		WebElement firstName = driver.findElement(By.cssSelector("input[name= \"firstname\"]"));
		firstName.sendKeys("Test");
		
		//LastNAme
		WebElement lastName = driver.findElement(By.cssSelector("input[name= \"lastname\"]"));
		lastName.sendKeys("User");
		
		//EmailAddress
		WebElement emailID =driver.findElement(By.cssSelector("input[name= \"reg_email__\"]"));
		emailID.sendKeys("testuser@test.com");
		
		// Password field
		WebElement pwd = driver.findElement(By.cssSelector("input[name= \"reg_passwd__\"]"));
		pwd.sendKeys("Testuser@123");
		
		Thread.sleep(2000);
		
		// select date from date picker
		WebElement dropdownDate = driver.findElement(By.id("day"));
		dropdownDate.click();
		Select dayDropdown = new Select(driver.findElement(By.cssSelector("select[name='birthday_day']")));
		dayDropdown.selectByVisibleText("11");
		
		//select month from date picker
		WebElement dropdownMonth = driver.findElement(By.id("month"));
		dropdownMonth.click();
		Select monthDropdown = new Select(driver.findElement(By.cssSelector("select[name='birthday_month']")));
		monthDropdown.selectByVisibleText("May");
		
		// select year from date picker
		
		WebElement dropdownYear = driver.findElement(By.id("year"));
		dropdownYear.click();
		Select yearDropdown = new Select(driver.findElement(By.cssSelector("select[name='birthday_year']")));
	    yearDropdown.selectByVisibleText("1985");
	    
	    //clicking female radio button
	    driver.findElement(By.xpath("//label[text()=\"Female\"]")).click();
	    Thread.sleep(1000);
	    
	    // Clicking signup button
	    driver.findElement(By.cssSelector("button[name=\"websubmit\"]")).click();
	    Thread.sleep(10000);
		
	    //Verifying that user is successfully registered
	    String accounturl = "https://www.facebook.com/?sk=welcome";
	    String currenturl = driver.getCurrentUrl();	
	    
	    if(currenturl.equals(accounturl))
	    {
	    	
	    	System.out.println("User successfully registered and redirected to Facebook homepage: " + currentUrl);
	    	
	    // Check for elements unique to the homepage to ensure redirection
	    	Thread.sleep(1000);
	    	
	    	if (driver.findElement(By.cssSelector("div[aria-label='Create']")).isDisplayed()) {
	            System.out.println("Homepage elements are present. Registration and redirection verified.");
	        } else {
	            System.out.println("Homepage elements are not present. Verification failed.");
	        }
	    	
	    	
	    	
	    }
	    else 
	    {
	    	System.out.println("Registration or redirection to Facebook homepage failed");
	    }
	    
		driver.close();
		
		

	}

}
