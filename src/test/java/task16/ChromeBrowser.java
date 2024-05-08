package task16;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ChromeBrowser {

	public static void main(String[] args) {
		
		
		System.setProperty("webdriver.driver.chrome", "E:\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
		
		WebDriver driver = new ChromeDriver();
		
		driver.get("https://www.demoblaze.com/");
		driver.manage().window().maximize();
		
		String title = driver.getTitle();
		System.out.println("The website title is :" + title);
		
		if (title.equals("STORE")) 
		{
			System.out.println("Page landed on correct website");
			
		}
		else 
		{
			System.out.println(" Page not landed on correct website");
			
		}
		
		driver.close();
		
		
	}

}
