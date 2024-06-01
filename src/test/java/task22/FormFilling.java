package task22;
import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.io.Files;

public class FormFilling {

	public static void main(String[] args) throws InterruptedException, IOException {
		
		System.setProperty("webdriver driver chrome", "E:\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		driver.get("https://phptravels.com/demo/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		// Define an explicit wait
        WebDriverWait wait = new WebDriverWait(driver,  Duration.ofSeconds(10)); // 10 seconds wait

        // Example: Wait for the heading to be visible
        WebElement heading = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class='text-start fs-4']")));

        // Retrieve the text of the heading
        String headingText = heading.getText();

        // Verify the heading text
        String expectedHeadingText = "Demo Request Form";
        if (headingText.equals(expectedHeadingText)) {
            System.out.println("Heading text is correct: " + headingText);
        } else {
            System.out.println("Heading text is incorrect. Expected: " + expectedHeadingText + ", but found: " + headingText);
        }
        
        // FirstName element field
        WebDriverWait waitFirst = new WebDriverWait(driver,  Duration.ofSeconds(10));
        WebElement firstName = waitFirst.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name='first_name']")));
        firstName.sendKeys("Karthikaa");
        
        //LastName element field
        WebDriverWait waitLast = new WebDriverWait(driver,  Duration.ofSeconds(10));
        
        WebElement lastName = waitLast.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name=\"last_name\"]")));
        lastName.sendKeys("Mohan");
        
        // BusinessName element field
        WebDriverWait waitBusiness = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        WebElement businessName = waitBusiness.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("input[name=\"business_name\"]")));
        businessName.sendKeys("Karthikaa Travel Agency");
        
        // Email element field
        
        WebDriverWait waitEmail = new WebDriverWait(driver, Duration.ofSeconds(10));
        
        WebElement emailName = waitEmail.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class=\"form-floating mb-3\"]>input[name=\"email\"]")));
        emailName.sendKeys("Guvi@gmail.com");
        
        driver.findElement(By.id("demo")).click();
        
        //Accept Alert
        
        Alert alert =driver.switchTo().alert();
        alert.accept();
        
        //Addition of numbers in captcha
        WebElement num1Element = driver.findElement(By.id("numb1"));
        
        //Retrieving the text values from the elements
        String num1Text = num1Element.getText();
        
        
 
        WebElement num2Element = driver.findElement(By.id("numb2"));
        
        String num2Text = num2Element.getText();
        
        //Converting the text values into integers
        
        int num1 = Integer.parseInt(num1Text);
        int num2 = Integer.parseInt(num2Text);
        
        //Adding num1 and num2
        int sum = num1+num2;
        
         // Passing the result in the Result input field
        WebElement resultElement = driver.findElement(By.id("number"));
        resultElement.sendKeys(String.valueOf(sum));
        Thread.sleep(2000);
        
        driver.findElement(By.id("demo")).click();
        Thread.sleep(3000);
        // Wait for the thank you message to be visible
        WebDriverWait waitMsg = new WebDriverWait(driver, Duration.ofSeconds(10)); 
        WebElement thankYouMessage = waitMsg.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h2[@class=\"text-center cw mt-3\"]/strong[text()=\" Thank you!\"]")));

        // Verify the thank you message
        String expectedMessage = "Thank you!";
        String actualMessage = thankYouMessage.getText().trim();
        
        if (actualMessage.contains(expectedMessage)) {
            System.out.println("Form submitted successfully: " + actualMessage);
        } else {
            System.out.println("Form submission failed. Expected: " + expectedMessage + ", but found: " + actualMessage);
        }
        
        // Taking screenshot of the page
        
        File  f = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);   
        Files.copy(f,new File("E:\\GUVI\\eclipse-workspace\\seleniumtasks\\src\\test\\java\\task22\\phptravels.jpg"));
       
        
        // Close the browser
        driver.quit();

	}

}
