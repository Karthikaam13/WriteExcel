package task21;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SwitchWindowHandles {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver driver chrome", "E:\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/windows");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		  System.out.println("First tab title: " + driver.getTitle());
		
		// Clicking the click button
		driver.findElement(By.xpath("//a[text()=\"Click Here\"]")).click();
		Thread.sleep(1000);
		
		// Store the handle of the default window
		String defaultWindowHandle = driver.getWindowHandle();
		
		// Get handles of all windows
        Set<String> windowHandles = driver.getWindowHandles();
        
        // Loop through each handle
        for(String windowHandle:windowHandles) 
        {// Switch to the new window 
        	driver.switchTo().window(windowHandle);
        	Thread.sleep(1000);
        	
        	// Close the window if it's not the default window
             if (!windowHandle.equals(defaultWindowHandle)) {
            	 System.out.println("New window tittle:" + driver.getTitle());
                driver.close();
            }
          }
          // Switch back to the default window
          driver.switchTo().window(defaultWindowHandle);
          Thread.sleep(1000);
          
        // verify that the original window is active
          String currentWindow =  driver.getTitle();
          System.out.println("Title of the current window: " + currentWindow);

          if (currentWindow.equals("The Internet")) 
          {
        	  System.out.println("Original window is active.");
          } else {
              System.out.println("Original window is not active.");
          
          }
          
        driver.quit();
          
        	
        }
	
}


