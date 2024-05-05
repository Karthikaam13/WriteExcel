package task15;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class LaunchGoogle {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver", "E:\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.google.com");
		
		driver.findElement(By.id("APjFqb")).sendKeys("Selenium Browser Drivers");
	    
		System.out.println("Searched Elements are :");
		Thread.sleep(5000);
		List<WebElement> list = driver.findElements(By.xpath("//ul[@role='listbox']//li"));
		System.out.println(list.size());
		
		for (int i=0 ; i<list.size(); i++) 
		{
			String listitem = list.get(i).getText();
			System.out.println(listitem);
			
			if(listitem.contains("Selenium Browser drivers")) 
			{
				list.get(i).click();
				break;
			}
		}
		
//		for(WebElement ele : list) 
//		{
//			System.out.println(ele.getText());		
//		}
		driver.close();
		

	}

}
