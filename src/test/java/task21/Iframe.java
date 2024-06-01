package task21;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


public class Iframe {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.driver.chrome","E:\\ChromeDriver\\chromedriver-win64\\chromedriver.exe\\");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/iframe");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		WebElement closeWarning = driver.findElement(By.cssSelector("button[class=\"tox-notification__dismiss tox-button tox-button--naked tox-button--icon\"]"));
		closeWarning.click();
		
		// Switch to iframe 
		WebElement iframeElement = driver.findElement(By.cssSelector("iframe.tox-edit-area__iframe"));
		driver.switchTo().frame(iframeElement);
		
		WebElement insideIframe= driver.findElement(By.id("tinymce"));

	    Actions actions = new Actions(driver);
	    Thread.sleep(2000);
	    actions.moveToElement(insideIframe).doubleClick(insideIframe).sendKeys("Hello People").perform();
		Thread.sleep(1000);
	
		//insideIframe.sendKeys("Hello People");
		System.out.println("Text inside iframe:" +insideIframe.getText());
		Thread.sleep(2000);
		
		driver.close();
		
		
		

	}

}
