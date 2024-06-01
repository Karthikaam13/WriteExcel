package task21;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FrameSwitches {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver driver chrome", "E:\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://the-internet.herokuapp.com/nested_frames");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// Switch to the top frame
		
		driver.switchTo().frame("frame-top");
		
		// verify there are three frames on the page
		
		List<WebElement> frames = driver.findElements(By.xpath("//frame"));
		if(frames.size()==3) 
		{ System.out.println("There are three frames on the page.");
        } else {
            System.out.println("The number of frames on the page is: " + frames.size());
			
		}	
		
		 // Switch to the left frame
        driver.switchTo().frame("frame-left");
        
        // Verify that the left frame has the text "LEFT"
        WebElement leftFrame = driver.findElement(By.tagName("body"));
        
        String leftFrameText = leftFrame.getText();
        
        if(leftFrameText.equals("LEFT")) {
        	
        	 System.out.println("The left frame has the text: LEFT");
        } else {
            System.out.println("The left frame text is: " + leftFrameText);
        }
        
        // Switch back to the default content before switching to another frame
        driver.switchTo().defaultContent();
         	
       // switch back to the top frame 
        driver.switchTo().frame("frame-top");
       
       // Switch to the middle frame
       driver.switchTo().frame("frame-middle");
       
       WebElement middleFrame =  driver.findElement(By.tagName("body"));
       
       String middleFrameText = middleFrame.getText();
       
       if(middleFrameText.equals(middleFrameText)) 
       {
    	   System.out.println("The Middle frame has the text: MIDDLE");
       } else {
           System.out.println("The Middle frame text is: " + middleFrameText);
    	   
       }
      // Switch back to the default content before switching to another frame
       driver.switchTo().defaultContent();
        	
      // switch back to the top frame 
       driver.switchTo().frame("frame-top");
       

       // Switch to the right frame
       driver.switchTo().frame("frame-right");
       
       WebElement rightFrame = driver.findElement(By.tagName("body"));
       
       String rightFrameText = rightFrame.getText();
       
       if(rightFrameText.equals("RIGHT")) {

    	   System.out.println("The Right frame has the text: Right");
       } else {
           System.out.println("The Right frame text is: " + rightFrameText);
    	   
    	   
       }
       // Switch back to the default content before switching to another frame
       driver.switchTo().defaultContent();
  
      
       // switch back to the top frame 
       driver.switchTo().frame("frame-top");
       
       // switch to frame bottom
       WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
      
       
      
       driver.switchTo().frame("frame-bottom");
       Thread.sleep(2000);
       
       WebElement bottomFrameName = driver.findElement(By.tagName("body"));
       
       String bottomFrameText = bottomFrameName.getText();
       
       if(bottomFrameText.equals("BOTTOM")) {

    	   System.out.println("The Bottom frame has the text: Bottom");
       } else {
           System.out.println("The Bottom frame text is: " + bottomFrameText);
    	   
    	   
       } 
        driver.switchTo().defaultContent();
   	
       // switch back to the top frame 
        driver.switchTo().frame("frame-top"); 
       
       // Get the title of the current web page
       String pageUrl = driver.getTitle();
       System.out.println(pageUrl);
       
       if(pageUrl.equals("https://the-internet.herokuapp.com/nested_frames")) {
    	   
    	   System.out.println(" Page Current Url is matches correct");
       }else {
    	   System.out.println(" Page Current Url is matches incorrect");
       }
       
       
       
       driver.quit();
		
       

	}

}
