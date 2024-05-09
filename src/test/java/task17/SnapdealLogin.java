package task17;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class SnapdealLogin {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.driver.chrome", "E:\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.snapdeal.com/");
		
		driver.manage().window().maximize();
		
		// Moving the cursor to the sign In button
		WebElement button = driver.findElement(By.xpath("//span[text()=\"Sign In\"]"));
		
		// Initialize Action class
		Actions action = new Actions(driver);
		
		// Move the mouse to the button to hover it
		action.moveToElement(button).perform();
		
		Thread.sleep(2000);
		
		//Clicking Login button
		driver.findElement(By.xpath("//a[text()=\"login\"]")).click();
		Thread.sleep(1000);
		
		//Switching to iframe using iframe id
		//WebElement iframeelement = driver.findElement(By.id("loginIframe"));
		//driver.switchTo().frame(iframeelement);
		
		//Switching to iframe using iframe index
		driver.switchTo().frame(0);
		Thread.sleep(2000);
		
		driver.findElement(By.id("userName")).sendKeys("karthikaam1306@gmail.com");
		driver.findElement(By.id("checkUser")).click();
		
		Thread.sleep(10000);
		
		driver.findElement(By.id("loginUsingOtp")).click();
		
		Thread.sleep(4000);
		
		// Verifying logged in through UserName
		
		String loggedInUser = driver.getCurrentUrl();
		System.out.println(loggedInUser);
		if(loggedInUser.equals("https://www.snapdeal.com/?loginSuccess=success&"))
		{
			
			System.out.println("Login successful. Logged in user: " + loggedInUser);
		}
		else 
		{
			 System.out.println("Login failed.");
		}
		
		Thread.sleep(2000);
		
		
		
		
		
		driver.close();
		
		
		

	}

}
