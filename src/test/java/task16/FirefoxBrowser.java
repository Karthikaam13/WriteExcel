package task16;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class FirefoxBrowser {

	public static void main(String[] args) 
	{
		
		
		System.setProperty("webdriver.driver.firefox","E:\\FirefoxDriver\\geckodriver.exe"); 
		
		WebDriver driver = new FirefoxDriver();
		
		// Maximizing the browser
		driver.manage().window().maximize();
				
		// Navigating to google website
		driver.get("https://www.google.com");
		
		// Printing the url of the current page
		String url =  driver.getCurrentUrl();
		System.out.println(url);
		
		// Reloading the page
		driver.navigate().refresh();
		
		// closing the browser
		
		driver.close();
		
		
		
	
	}

}
