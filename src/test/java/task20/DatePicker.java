package task20;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class DatePicker {

	public static void main(String[] args) throws InterruptedException {
		
		
		System.setProperty("webdriver.driver.chrome", "E:\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");   
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/datepicker/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		// Initialize WebDriverWait (explicit wait)
        //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		
		//Switching to iframe using iframe index
		driver.switchTo().frame(0);
		Thread.sleep(2000);
		 // Locate the datepicker element and click on it to open the datepicker
        WebElement datepicker = driver.findElement(By.id("datepicker")); 
        datepicker.click();
		
		//Selecting next month from date picker
		WebElement nextButton = driver.findElement(By.xpath("//a[@data-handler=\"next\"]"));
		nextButton.click();
		
		// Locate the specific day (22) and click on it
		WebElement day22 = driver.findElement(By.xpath("//a[text()=\"22\"]"));
        day22.click();
        
        Thread.sleep(2000);
        
        // Retrieve the selected date from the datepicker input field
        String selectedDate = datepicker.getAttribute("value");

        // Print the selected date to the console
        System.out.println("Selected Date: " + selectedDate);

        // Close the current browser window
        driver.close();

        // Quit the WebDriver session, closing all windows
        driver.quit();
		
	}

}
