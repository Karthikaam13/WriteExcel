package task16;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class Wikipedia {

	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.driver.chrome", "E:\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.wikipedia.org/");
		driver.manage().window().maximize();
		
		// Searching for the query "Artificial Intelligence"
		driver.findElement(By.id("searchInput")).sendKeys("Artificial Intelligence");
		
		 // Pressing the "Enter" key
        driver.findElement(By.id("searchInput")).sendKeys(Keys.ENTER);
        Thread.sleep(1000);
        
        // Clicking view history icon
        driver.findElement(By.cssSelector("a[title=\'Past revisions of this page [alt-shift-h]\']")).click();
        Thread.sleep(2000);
        
        String title = driver.getTitle();
        System.out.println(title);
        
        driver.close();
		

	}

}
