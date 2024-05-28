package task18;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class DragandDrop {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.driver.chrome", "\"E:\\\\ChromeDriver\\\\chromedriver-win64\\\\chromedriver.exe\\\"");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://jqueryui.com/droppable/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		//Switching to iframe using iframe index
		driver.switchTo().frame(0);
		Thread.sleep(2000);
		
		WebElement sourceEle= driver.findElement(By.id("draggable"));
		sourceEle.click();
		Thread.sleep(1000);
		
		WebElement targetEle = driver.findElement(By.id("droppable"));
		targetEle.click();
		Thread.sleep(1000);
		
		// Verification
        String dragstr = sourceEle.getText();
        System.out.println("Source element text: " + dragstr);

        String dropstr = targetEle.getText();
        System.out.println("Target element text : " + dropstr);

		
		 // Performing drag and drop
        Actions actions = new Actions(driver);
        actions.dragAndDrop(sourceEle, targetEle).build().perform();
        Thread.sleep(2000);
        

        // Verifying the Css property of the target element
        String colorProperty = targetEle.getCssValue("background-color");
        System.out.println("Color property of target element after drop: " + colorProperty);
        Thread.sleep(2000);
        
        // Verify the color property of the target element
        if (colorProperty.equals("rgba(255, 250, 144, 1)")) {
            System.out.println("Verification Passed: Color property of target element is as expected after drop operation");
        } else {
            System.out.println("Verification Failed: Color property of target element is not as expected after drop operation");
        }
        
        
        // getting the dropped element text
        
        String dropstr2 = targetEle.getText();
        System.out.println("Target element text after drop: " + dropstr2);

        
     // Verify the text of the target element
        if (dropstr2.equals("Dropped!")) {
            System.out.println("Verification Passed: Text of target element is 'Dropped' after drop operation");
        } else {
            System.out.println("Verification Failed: Text of target element is not 'Dropped' after drop operation");
        }
        
        Thread.sleep(1000);
        driver.close();
		
		}

}
