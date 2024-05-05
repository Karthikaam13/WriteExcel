package task15;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class SimpleWebdriverScript {

	public static void main(String[] args) throws InterruptedException {
		
		// Set the path to the ChromeDriver executable
		System.setProperty("webdriver.driver.chrome","E:\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
		
		
		// Initialize ChromeDriver
		WebDriver driver = new ChromeDriver();
		
		// open a Website
		driver.get("https://www.facebook.com");
		
		// maximize the window
		driver.manage().window().maximize();
		
		Thread.sleep(2000);
		
		//Get and print the page title
		String pageTitle = driver.getTitle();
		System.out.println(pageTitle);
		
		// close the browser
		driver.quit();
		
		
		



	}

}
