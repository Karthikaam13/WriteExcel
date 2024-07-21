package finalProjects;





import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogEntries;
import org.openqa.selenium.logging.LogEntry;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class Project1 {
	 private WebDriver driver;
	 
    
    

    @Before
    // Open a webbroswer and navigate to the Bestbuy e-com website
    public void setUp() {
        // Set the path to the ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "E:\\ChromeDriver\\chromedriver-win64\\chromedriver.exe");
        // Initialize the ChromeDriver
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    
        // Open the URL to the account creation page
        driver.get("https://www.bestbuy.com/?intl=nosplash");
        driver.manage().window().maximize();
        handleAlert();
     
       
    }
    private WebElement getHomeButton() {
        return driver.findElement(By.cssSelector("a[title=\"BestBuy.com\"]"));
     
    }
    private WebElement continueShoppingButton() {
        return driver.findElement(By.cssSelector("button[class=\"c-button-link continue-shopping\"]"));
    }
    
    private WebElement menuIcon() {
    	return driver.findElement(By.cssSelector("button[class=\"c-button-unstyled hamburger-menu-button\"]"));
    }
    private void openMenu(String menuName) {
    	// Locate and click the menu icon to open the side menu
        WebElement menuIcon = driver.findElement(By.cssSelector("button[class=\"c-button-unstyled hamburger-menu-button\"]"));
        menuIcon.click();
        
     // Wait for the menu to be visible
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".hamburger-menu-flyout")));
        
     // Click the specific menu item
        WebElement menuButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(), '" + menuName + "')]")));
        menuButton.click();
    }

    private List<WebElement> getMenuItems() {
        return driver.findElements(By.cssSelector(".hamburger-menu-flyout-list-item"));
    }

    private void openDealsMenu() {
        openMenu("Deals");
    }

    private void openSupportsServicesMenu() {
    	 driver.navigate().refresh();
        openMenu("Support & Services");
    }
    private void openBrandsMenu() {
    	 openMenu("Brands");
    }
    
   @Test
   
    // 2) Code to validate the Link is broken
    public void validateLinks() throws InterruptedException, IOException {
      Thread.sleep(1000);

      String urlString = "https://www.bestbuy.com/?intl=nosplash";
      driver.manage().window().maximize();
      
    
      boolean isValid = isLinkValid(urlString);
      Assert.assertTrue(urlString + " should be a valid link.", isValid);
    }

    // New method to check if a single link is broken or not
    private boolean isLinkValid(String urlString) throws IOException {
    	 URL url = new URL(urlString);
         HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
         httpConn.setConnectTimeout(3000); // Set timeout to 3 seconds
         httpConn.setRequestMethod("HEAD"); // Use HEAD method to fetch only headers

         int responseCode = httpConn.getResponseCode();
         boolean isValid = responseCode >= 200 && responseCode < 400; 
      // Print the response code and message
         if (isValid) {
             System.out.println("The link " + urlString + " is valid. Status code: " + responseCode);
         } else {
             System.out.println("The link " + urlString + " is broken. Status code: " + responseCode);
         }

         return isValid;
     }
    

    // 3) Code for Signup Functionality 
    //create an account
    @Test
    
    public void createAccount() throws InterruptedException {
    	
    	WebElement account = driver.findElement(By.xpath("//span[text()=\"Account\"]"));
    	account.click();
    	WebElement createAcct = driver.findElement(By.xpath("//a[text()=\"Create Account\"]"));
    	createAcct.click();
    	
    	// CreateAccount page
    	WebElement selectGoogleAcc =driver.findElement(By.id("google-button"));
    	selectGoogleAcc.click();
    	
    	String defaultWindowHandle = driver.getWindowHandle();
    	Set<String> windowHandles = driver.getWindowHandles();
    	
    	for(String windowHandle:windowHandles) 
    	{
    		driver.switchTo().window(windowHandle);
    		Thread.sleep(1000);
    		
    		if (!windowHandle.equals(defaultWindowHandle)) {
       		 System.out.println("New window tittle:" + driver.getTitle());
       		 driver.close();
       		
       	}
    	}
    	driver.switchTo().window(defaultWindowHandle);
    	Thread.sleep(2000);
    	
    	WebElement submitButton = driver.findElement(By.xpath("//button[text()=\"Create an Account\"]"));
    	submitButton.click();
    	  // Wait for and verify the validation messages
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        // Verify "Please enter your first name."
        WebElement firstNameValidation = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='firstName-text']/p"))
        );
        String firstNameMessage = firstNameValidation.getText();
        assertEquals("Please enter your first name.", firstNameMessage);
        System.out.println("Validation message verified successfully: " + firstNameMessage);

        // Verify "Please enter your last name."
        WebElement lastNameValidation = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='lastName-text']/p"))
        );
        String lastNameMessage = lastNameValidation.getText();
        assertEquals("Please enter your last name.", lastNameMessage);
        System.out.println("Validation message verified successfully: " + lastNameMessage);

        // Verify "Please enter a valid email address."
        WebElement emailValidation = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='email-text']/p"))
        );
        String emailMessage = emailValidation.getText();
        assertEquals("Please enter a valid email address.", emailMessage);
        System.out.println("Validation message verified successfully: " + emailMessage);

        // Verify "Please enter a strong password."
        WebElement passwordValidation = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='fld-p1-text']/p"))
        );
        String passwordMessage = passwordValidation.getText();
        assertEquals("Please enter a strong password.", passwordMessage);
        System.out.println("Validation message verified successfully: " + passwordMessage);

        // Verify "Please reenter your password."
        WebElement reenterPasswordValidation = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='reenterPassword-text']/p")));
        
        
        String reenterPasswordMessage = reenterPasswordValidation.getText();
        assertEquals("Please reenter your password.", reenterPasswordMessage);
        System.out.println("Validation message verified successfully: " + reenterPasswordMessage);

        // Verify "Please enter a valid mobile phone number."
        WebElement phoneValidation = wait.until(
            ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='phone-text']/p"))
        );
        String phoneMessage = phoneValidation.getText();
        assertEquals("Please enter a valid mobile phone number.", phoneMessage);
        System.out.println("Validation message verified successfully: " + phoneMessage);
        Thread.sleep(1000);    
        // Fill in the first name field
        WebElement firstName = driver.findElement(By.id("firstName"));
    	firstName.sendKeys("Karthikaa");
    	submitButton.click();
    	WebElement lastName = driver.findElement(By.id("lastName"));
    	lastName.sendKeys("Mohan");
    	
    	// List of invalid email formats to test
        String[] invalidEmails = { "kar@", "kar@com", "kar.com", "@kar.com", "kar@.com" };

        WebElement emailField = driver.findElement(By.id("email"));

        for (String email : invalidEmails) {
        	 emailField.sendKeys(Keys.CONTROL + "a"); // Select all text in the field
             emailField.sendKeys(Keys.DELETE); // Delete the selected text
             emailField.sendKeys(email);
            //emailField.clear();
           
            Thread.sleep(1000);
            submitButton.click();

            // Verify validation message
            String emailMessage1 = emailValidation.getText();
            assertEquals("Please enter a valid email address.", emailMessage1);
            System.out.println("Validation message verified successfully for " + email + ": " + emailMessage1);
        }
        

        // Test with a valid email
        emailField.sendKeys(Keys.CONTROL + "a"); // Select all text in the field
        emailField.sendKeys(Keys.DELETE); // Delete the selected text
        emailField.sendKeys("karthikaa13@gmail.com");
        submitButton.click();

        // Ensure no validation message is shown for the correct email format
        List<WebElement> emailValidationMessages = driver.findElements(By.xpath("//div[@id='email-text']/p"));
        assertEquals(0, emailValidationMessages.size());
        System.out.println("Valid email format accepted successfully.");
        
        
     // List of invalid password formats to test
        
        String[] invalidpwds = { "kar@", "kar@1", "kar.com12", "@karom", "ka123*" };
        WebElement pwdField = driver.findElement(By.id("fld-p1"));
        for(String pwds:invalidpwds) {
        	
        	pwdField.sendKeys(Keys.CONTROL + "a");
        	pwdField.sendKeys(Keys.DELETE);
        	pwdField.sendKeys(pwds);
        	submitButton.click();
        	
     // Verify password validation message
       String passwordMessage1 = passwordValidation.getText();
       assertEquals("Please enter a strong password.", passwordMessage1);
       System.out.println("Validation message verified successfully: " + passwordMessage1);
       driver.findElement(By.id("show-hide-password-toggle")).click();
       }
        // Test with a valid email
        pwdField.sendKeys(Keys.CONTROL + "a"); // Select all text in the field
        pwdField.sendKeys(Keys.DELETE); // Delete the selected text
        pwdField.sendKeys("karthikaa@123 *m");
        submitButton.click();
        
        // Ensure no validation message is shown for the correct password format
        List<WebElement> pwdValidationMessages = driver.findElements(By.xpath("//div[@id='fld-p1-text']/p"));
        assertEquals(0, pwdValidationMessages.size());
        System.out.println("Valid password format accepted successfully.");
        
        
         // List of invalid  Confirm password formats to test
        
        String[] invalidConfirmpwds = { "kar@", "kar@1", "kar.com12", "@karom", "karthi123*" };
        WebElement pwdConfirmField = driver.findElement(By.id("reenterPassword"));
        for(String pwds:invalidConfirmpwds) {
        	
        	pwdConfirmField.sendKeys(Keys.CONTROL + "a");
        	pwdConfirmField.sendKeys(Keys.DELETE);
        	pwdConfirmField.sendKeys(pwds);
        	submitButton.click();
        	
     // Verify re-enter password validation message
        	
       String passwordConfirmMessage1 =  reenterPasswordValidation.getText();
       assertEquals("Passwords do not match.", passwordConfirmMessage1);
       System.out.println("Validation message verified successfully: " + passwordConfirmMessage1);
       driver.findElement(By.id("show-hide-reenter-password-toggle")).click();
      
       }
        // Test with a valid email
        pwdConfirmField.sendKeys(Keys.CONTROL + "a"); // Select all text in the field
        pwdConfirmField.sendKeys(Keys.DELETE); // Delete the selected text
        pwdConfirmField.sendKeys("karthikaa@123 *m");
        submitButton.click();
        
        // Ensure no validation message is shown for the correct password format
        List<WebElement> pwdConfirmValidationMessages = driver.findElements(By.xpath("//div[@id='reenterPassword-text']/p"));
        assertEquals(0, pwdConfirmValidationMessages.size());
        System.out.println("Valid confirm password format accepted successfully.");
        
        
     // List of invalid mobile formats to test
        
        String[] invalidMob = {"1234546", "7845120", "987", "00"};
        WebElement mobField = driver.findElement(By.id("phone"));
        
        for(String mobNums: invalidMob)  {
        	
        	mobField.sendKeys(Keys.CONTROL + "a");
        	mobField.sendKeys(Keys.DELETE);
        	mobField.sendKeys(mobNums);
        	submitButton.click();
        	
       // verify the validation message
       String phoneMessage1 = phoneValidation.getText();
       assertEquals("Please enter a valid mobile phone number.", phoneMessage1);
       System.out.println("Validation message verified successfully: " + phoneMessage1);
        
        	
        }
        
       mobField.sendKeys(Keys.CONTROL +"a");
       mobField.sendKeys(Keys.DELETE);
       mobField.sendKeys("9874512300");
       
       
       // Ensure no validation message is shown for the correct mobile num format
       List<WebElement> mobNumValidationMessages = driver.findElements(By.xpath("//div[@id='phone-text']/p"));
       assertEquals(0, mobNumValidationMessages.size());
       System.out.println("Valid mobile number format accepted successfully.");
       
       
       // checkbox selection for Account Recovery
       WebElement checkbox = driver.findElement(By.cssSelector("input[id=\"is-recovery-phone\"]"));
       checkbox.click();
       Thread.sleep(1000);
       submitButton.click();
       Thread.sleep(1000);
       
       // Return to previou page
       
       WebElement clickPreviousPage = driver.findElement(By.xpath("//a[text()=\"Return to previous page\"]"));
       clickPreviousPage.click();
       Thread.sleep(2000);
       System.out.println("Page redirected to Home page successfully");
    
    
    }
    // 3) Code for Login functionality
    @Test
    
    public void signInAccount() throws InterruptedException  {
   
   
    	driver.navigate().refresh();
    	WebElement account = driver.findElement(By.xpath("//span[text()=\"Account\"]"));
    	account.click();
    	Thread.sleep(1000);
    	WebElement logIn =driver.findElement(By.xpath("//a[text()=\"Sign In\"]"));
    	logIn.click();
    	Thread.sleep(1000);
    	
    	// sigin through Apple Id
    	driver.findElement(By.cssSelector("button[class=\"c-button c-button-outline c-button-lg c-button-block c-button-icon c-button-icon-leading svg-button social-button apple-button  font-weight-normal\"]")).click();
    	driver.navigate().back();
    	Thread.sleep(1000);
    	
    	// signin through Google account
    	driver.findElement(By.id("google-button")).click();
    	String defaultWindowHandle = driver.getWindowHandle();
    	Set<String> windowHandles = driver.getWindowHandles();
    	
    	for(String windowHandle:windowHandles) 
    	{
    		driver.switchTo().window(windowHandle);
    		Thread.sleep(1000);
    		
    		if (!windowHandle.equals(defaultWindowHandle)) {
       		 System.out.println("New window tittle:" + driver.getTitle());
       		 driver.close();
       		
       	}
    	}
    	driver.switchTo().window(defaultWindowHandle);
    	Thread.sleep(2000);
    	
    	
    	
    	
    	 // Signin the account
       WebElement signIn = driver.findElement(By.xpath("//button[text()=\"Sign In\"]"));
    	
    	/*String[] emailIds = {"karthi@gmail.com", " kar@123.com", "karhi@gmail.com"};
         String[] passwords = {"123", "BestBuy@123", "1234567890", "ValidPass1!"};

         // Iterate through email and password combinations
         for (String email : emailIds) {
             for (String pwd : passwords) {
                 // Locate and clear email and password fields
                 WebElement emailField = driver.findElement(By.id("fld-e"));
                 emailField.sendKeys(Keys.CONTROL + "a");
                 emailField.sendKeys(Keys.DELETE);
                 emailField.sendKeys(email);

                 WebElement passwordField = driver.findElement(By.id("fld-p1"));
                 passwordField.sendKeys(Keys.CONTROL + "a");
                 passwordField.sendKeys(Keys.DELETE);
                 passwordField.sendKeys(pwd);

                
                  signIn.click();
                  Thread.sleep(1000);
                 

                 // Wait for validation messages
                 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
                 WebElement errorMessageElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='c-alert-content rounded-r-100 flex-fill v-bg-pure-white p-200 pl-none']/strong/div")));
                 String errorMessage = errorMessageElement.getText();
                 System.out.println("Error message is: " + errorMessage); 

                
             }
         }  */
         
         Thread.sleep(2000);

         // Test with correct email and password
         String correctEmail = "karthikaam1306@gmail.com";
         String correctPassword = "Bestbuy#1954*6m";

         WebElement emailField = driver.findElement(By.id("fld-e"));
         emailField.sendKeys(Keys.CONTROL + "a");
         emailField.sendKeys(Keys.DELETE);
         emailField.sendKeys(correctEmail);

         WebElement passwordField = driver.findElement(By.id("fld-p1"));
         passwordField.sendKeys(Keys.CONTROL + "a");
         passwordField.sendKeys(Keys.DELETE);
         passwordField.sendKeys(correctPassword);

         // Submit the form
         signIn.click();
        
         Thread.sleep(5000);
         
      // Add a wait to verify successful sign-in
         try {
        	 WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
             WebElement userProfileElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("account-menu-account-button")));
             Assert.assertTrue("Sign in failed. User profile element is not displayed.", userProfileElement.isDisplayed());
             System.out.println("Signed in successfully.");
         } catch (Exception e) {
             System.out.println("Sign in failed: " + e.getMessage());
         }
         Thread.sleep(3000);
        

         
     }
    // 4) Code for navigation of all Menu and validation of title in each page
    
   // Open the Deals menu initially to get the list of menu items
    private void verifyMenuItems(Map<String, String> expectedTitles, String menuName) throws InterruptedException {
             List<WebElement> menuItems = getMenuItems();
             for (int i = 0; i < menuItems.size(); i++) {
                 WebElement menuItem = menuItems.get(i);
                 String menuItemText = menuItem.getText();

                 if (expectedTitles.containsKey(menuItemText)) {
                     menuItem.click();
                     String actualTitle = driver.getTitle();
                     String expectedTitle = expectedTitles.get(menuItemText);
                     assertEquals("Page title does not match for menu item: " + menuItemText, expectedTitle, actualTitle);
                     //driver.navigate().back();

                     // Re-open the menu after navigating back
                     openMenu(menuName);
                     Thread.sleep(3000); // Ensure the menu is fully loaded
                     menuItems = getMenuItems();
                 }
             }
    }
            
         
    @Test 
    
    
    public void navigationOfAllMenu() throws InterruptedException {
    	   
    	    WebElement topDeals = driver.findElement(By.xpath("//a[text()=\"Top Deals\"]"));
    	    topDeals.click();
    	    String expectedTitle = "Top Deals and Featured Offers on Electronics - Best Buy";
    	    String actualTitle = driver.getTitle();
    	    Assert.assertEquals(actualTitle, expectedTitle);
    	    // Validate the page title
            if (actualTitle.equals(expectedTitle)) {
                System.out.println("Validation passed: The title is as expected.");
            } else {
                System.out.println("Validation failed: The title is not as expected.");
                System.out.println("Expected Title: " + expectedTitle);
                System.out.println("Actual Title: " + actualTitle);
            }
            
    		// Support & Services and its submenus
           
        	Map<String, String> supportServicesExpectedTitles = new HashMap<>();
             supportServicesExpectedTitles.put("Visit our Support Center", "Best Buy Support & Customer Service");
             supportServicesExpectedTitles.put("Repair & Tech Services", "Schedule a Service - Best Buy"); 
             supportServicesExpectedTitles.put("Remote Support", "Remote Support: Geek Squad - Best Buy");
             supportServicesExpectedTitles.put("Manage an Appointment", "Appointment Finder - Best Buy");
             supportServicesExpectedTitles.put("Trade In Your Device", "Trade-In - Best Buy");
             supportServicesExpectedTitles.put("Shop with an Expert", "Shop with an Expert - Best Buy");
             supportServicesExpectedTitles.put("Check Repair Status", "Track Your Repair - Best Buy");
             supportServicesExpectedTitles.put("Free Consultation", "Best Buy Home: Virtual and In-Home Consultation - Best Buy");
             supportServicesExpectedTitles.put("Best Buy Brands Support", "Best Buy Brands Support");
             supportServicesExpectedTitles.put("Computer & Tablet Services", "Computer, Laptop & Tablet Repairs & Services by Geek Squad - Best Buy"); 
             supportServicesExpectedTitles.put("TV & Home Theater Services", "TV & Home Theater Services: Geek Squad - Best Buy");
             supportServicesExpectedTitles.put("Appliance Services", "Appliance Services: Geek Squad - Best Buy"); 
             supportServicesExpectedTitles.put("Cell Phone Services", "Cell Phone Services and Repair – Best Buy");
             supportServicesExpectedTitles.put("Apple Authorized Repair", "Apple Repair Services: Apple Authorized Service Provider - Best Buy");
             supportServicesExpectedTitles.put("Smart Home Services", "Smart Home Services: Installation, Set Up & Support - Best Buy");
             supportServicesExpectedTitles.put("Car Electronics Professional Installation", "Car Electronics Professional Installation - Best Buy");
             supportServicesExpectedTitles.put("Fitness Equipment Services", "Fitness Equipment Services - Best Buy");
             supportServicesExpectedTitles.put("EV Charger Installation", "EV Charger Installation - Best Buy");
             supportServicesExpectedTitles.put("All Services", "Services - Best Buy"); 
             supportServicesExpectedTitles.put("My Best Buy Total Membership", "Best Buy Total™ – Best Buy");
             supportServicesExpectedTitles.put("Geek Squad Protection", "Geek Squad Protection - Best Buy"); 
             supportServicesExpectedTitles.put("AppleCare+", "AppleCare & AppleCare+ - Best Buy");
             supportServicesExpectedTitles.put("Best Buy Protection", "Product Protection with Total- Best Buy");
             supportServicesExpectedTitles.put("Haul Away & Recycling", "Electronics and Appliances Recycling at Best Buy");
             supportServicesExpectedTitles.put("Subscriptions", "Streaming Services: Subscriptions – Best Buy");

             
           
             
             // Method Calling for Support & Services menu
             openMenu("Support & Services");
             verifyMenuItems(supportServicesExpectedTitles, "Support & Services");
             
             //Click the Close button in the Support & Services menu
             ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 900);");
             WebElement supportandServices = driver.findElement(By.xpath("//li/button[@class='c-button-unstyled close-button-sidecar']"));
             supportandServices.click();
             Thread.sleep(1000); 
             
             // Brands and its submenus
             
             Map<String, String> brandsExpectedTitles = new HashMap<>();
             brandsExpectedTitles.put("Apple", "Apple – Best Buy");
             brandsExpectedTitles.put("Samsung", "Samsung Store – Best Buy");
             brandsExpectedTitles.put("PlayStation", "Sony PlayStation 5: PS5 - Best Buy"); 
             brandsExpectedTitles.put("Sony", "Sony Store: Sony Electronics & Entertainment - Best Buy");
             brandsExpectedTitles.put("LG", "LG Store - Best Buy"); 
             brandsExpectedTitles.put("Microsoft", "Microsoft Brand Store - Best Buy"); 
             brandsExpectedTitles.put("HP", "HP - Best Buy"); 
             brandsExpectedTitles.put("Nintendo", "Nintendo Switch: Console, Games & Accessories - Best Buy"); 
             brandsExpectedTitles.put("Lenovo", "Lenovo Computers - Best Buy"); 
             brandsExpectedTitles.put("ASUS", "ASUS Computers: Gaming Laptops, VivoBook, Zenbook - Best Buy"); 
             brandsExpectedTitles.put("Dell", "Dell Computers - Best Buy");
             brandsExpectedTitles.put("Tesla", "Tesla  - Best Buy");
             brandsExpectedTitles.put("All Brands", "Name Brands - Best Buy");
             brandsExpectedTitles.put("Best Buy essentials", "Best Buy Essentials - Best Buy");
             brandsExpectedTitles.put("Insignia", "Insignia - Best Buy");
             brandsExpectedTitles.put("Rocketfish", "Rocketfish - Best Buy");
             brandsExpectedTitles.put("Yardbird", "Yardbird – Best Buy");
             
             
             // Method Calling for brand
             openMenu("Brands");
             verifyMenuItems(brandsExpectedTitles, "Brands");
             
             //Click the Close button in the brands menu
             ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, 900);");
             WebElement brands = driver.findElement(By.xpath("//li/button[@class='c-button-unstyled close-button-sidecar']"));
             brands.click();
             Thread.sleep(1000); 
              } 
    
    // 5 i) Validate the bottom links for Order & Purchases  on the homepage
    
    @Test
    
    
    public void validateBottomLinks() throws InterruptedException {
        validateBottomLinks("Order & Purchases");
        validateBottomLinks("Support & Services");
        validateBottomLinks("Partnerships");
        validateBottomLinks("Payment Options");
        validateBottomLinks("Rewards & Membership");
        validateBottomLinks("About Best Buy");
    }
        
    public void validateBottomLinks(String sectionHeading) throws InterruptedException {
        // Locate the <h3> element for the specified section
        WebElement headingElement = driver.findElement(By.xpath("//h3[contains(text(), '" + sectionHeading + "')]"));

        // Retrieve and print the text of the <h3> element
        String headingText = headingElement.getText();
        System.out.println("Heading Text: " + headingText);

        // Locate the <ul> element containing the links for the specified section
        WebElement bottomLinksList = driver.findElement(By.xpath("//h3[contains(text(), '" + sectionHeading + "')]/following-sibling::ul"));

        // Get all <a> elements within the <ul>
        List<WebElement> links = bottomLinksList.findElements(By.tagName("a"));

        for (int i = 0; i < links.size(); i++) {
            // Re-fetch the list of links after navigating back
            bottomLinksList = driver.findElement(By.xpath("//h3[contains(text(), '" + sectionHeading + "')]/following-sibling::ul"));
            links = bottomLinksList.findElements(By.tagName("a"));

            WebElement link = links.get(i);
            String linkText = link.getText();
            String href = link.getAttribute("href");

            // Log the link text before clicking
            System.out.println("Clicking link: " + linkText);
            
            // Skip the first link or any specific links if needed
            /*if (i == 0 || href.contains("sign-in-page-url")) {
                System.out.println("Skipping link: " + linkText);
                continue;
            } */

            // Validate the link presence
            Assert.assertNotNull("Link not found: " + linkText, link);

            // Click the link
            link.click();

            // Wait for the new page to load
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.tagName("body")));

            // Navigate back to the homepage
            driver.navigate().back();

            // Wait for the homepage to load
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h3[contains(text(), '" + sectionHeading + "')]/following-sibling::ul")));
        }
    }
    
   //6 Search for an item and an item to the shopping cart
    @Test
    
    
    
    public void addItemShoppingCart() throws InterruptedException {
    	
    	
    	// Search an item in search bar
    	
    	WebElement search = driver.findElement(By.id("gh-search-input"));
    	search.sendKeys("hamilton electric grill");
    	search.sendKeys(Keys.ENTER);
    	Thread.sleep(2000);
    	
    	 // Wait until the list of "Add to Cart" buttons is visible
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> addToCartButtons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("button.add-to-cart-button")));

        // Ensure we have at least one button found
        if (!addToCartButtons.isEmpty()) {
            // Click the first "Add to Cart" button
            WebElement firstAddToCartButton = addToCartButtons.get(0);
            wait.until(ExpectedConditions.elementToBeClickable(firstAddToCartButton)).click();

            // Add any additional steps needed after clicking the button
            System.out.println("Clicked the first 'Add to Cart' button successfully.");
        } else {
            System.out.println("No 'Add to Cart' buttons found.");
        }
        Thread.sleep(3000);
    	// skip this step
    	
    	List<WebElement> skipStepElements = driver.findElements(By.xpath("//span[text()=\"Skip this Step\"]"));
        if (!skipStepElements.isEmpty() && skipStepElements.get(0).isDisplayed()) {
            skipStepElements.get(0).click();
        } else {
            continueShoppingButton().click();
        }
    	// Use the homeButton variable
        getHomeButton().click();
    	Thread.sleep(2000);
    	
    	
    	}
    
    // 7) Select an item and adding to the cart from Menu(shop by department)
    
    @Test
    
   
    public void addingItemFromMenu() throws InterruptedException {
	   
	    menuIcon().click();
	    //driver.findElement(By.cssSelector("button[data-lid='ubr_cp']"));
	    WebElement computersAndTabletsButton = driver.findElement(By.xpath("//button[text()=\"Computers & Tablets\"]"));
	    computersAndTabletsButton.click();
	    WebElement laptopsAndDesktops = driver.findElement(By.xpath("//button[text()=\"Laptops & Desktops\"]"));
	    laptopsAndDesktops.click();
	    WebElement laptops = driver.findElement(By.xpath("//a[text()=\"Laptops\"]"));
	    laptops.click();
	    Thread.sleep(2000);
	    
	    // Shop by Operating System
	    // Find the "Shop now" link for Windows OS laptops
	    WebElement shopNowLink = driver.findElement(By.xpath("//h3[text()='Windows OS']/following-sibling::ul//a[text()='Shop now']"));
	
	    // Click on the "Shop now" link
	    shopNowLink.click();
	    Thread.sleep(3000);
	    
	    // Wait until the list of "Add to Cart" buttons is visible
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	    List<WebElement> addToCartButtons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("button.add-to-cart-button")));
	
	    // Ensure we have at least one button found
	    if (!addToCartButtons.isEmpty()) {
	        // Click the first "Add to Cart" button
	        WebElement firstAddToCartButton = addToCartButtons.get(0);
	        wait.until(ExpectedConditions.elementToBeClickable(firstAddToCartButton)).click();
	
	        // Add any additional steps needed after clicking the button
	        System.out.println("Clicked the first 'Add to Cart' button successfully.");
	    } else {
	        System.out.println("No 'Add to Cart' buttons found.");
	    }
		     Thread.sleep(3000);
			  continueShoppingButton().click();
			// Use the homeButton variable
			    getHomeButton().click();
			    Thread.sleep(2000);
	    
	    }
    
//  8) Select an item and adding to the cart from Menu(Brands select any brand) 
    @Test
    
   
    public void addItemfromBrand() throws InterruptedException {
    	
    	 menuIcon().click();
    	 WebElement brand = driver.findElement(By.xpath("//button[text()=\"Brands\"]"));
    	 brand.click();
    	 Thread.sleep(2000);
    	// Find all the brand links
         List<WebElement> brandLinks = driver.findElements(By.cssSelector(".hamburger-menu-flyout-list-item"));

         // Specify the index of the item you want to click
         int specificIndex = 3; 

         // Ensure the index is within the bounds of the list
         if (specificIndex >= 0 && specificIndex < brandLinks.size()) {
             // Click the specific brand
             WebElement selectedBrand = brandLinks.get(specificIndex);
             selectedBrand.click();
         } else {
             System.out.println("Index out of bounds");
         }
         Thread.sleep(1000);
         
         // Locate the "Sony OLED and LED TVs" link by its link text and click it
          //WebElement sonyOledLedTvsLink = driver.findElement(By.linkText("Sony OLED and LED TVs"));
          WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
          WebElement sonyTvLink = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(), 'Sony OLED and LED TVs')]")));
          
          // Check if the element is found and then click
          if(sonyTvLink !=null && sonyTvLink.isDisplayed()) {
        	  sonyTvLink.click();
              System.out.println("Navigated to Sony OLED and LED TVs page successfully.");
          } else {
              System.out.println("Sony OLED and LED TVs link not found.");
          }
           
          int sonyTvIndexToClick = 0;
          
       // Locate all tv link elements
          Thread.sleep(2000);
          List<WebElement> tvLinks = driver.findElements(By.xpath("//div[@class=\"list-wrapper top-border\"]/ol/li//a"));

       // Check if the specified index is within the range of TV links
          System.out.println("Number of TV links found: " + tvLinks.size());
          if (!tvLinks.isEmpty() && sonyTvIndexToClick >= 0 && sonyTvIndexToClick < tvLinks.size()) {
              // Click the specific TV link
        	  Thread.sleep(2000);
              tvLinks.get(sonyTvIndexToClick).click();
              System.out.println("Clicked on Sony TV at index " + sonyTvIndexToClick);
            
          } else {
        	  if (tvLinks.isEmpty()) {
        	        System.out.println("No Sony TVs found on the page.");
        	  }
              System.out.println("Invalid laptop index specified.");
          }
          // Wait until the element is located and visible
          /*List<WebElement> elements = driver.findElements(By.cssSelector("ul.c-carousel-list li button span p"));

          // Iterate over the elements to find the one with the text '75"'
          for (WebElement element : elements) {
              if (element.getText().equals("75\"")) {
                  // Click the button if the text matches
                  element.click();
                  break;
              }
          }*/
	          Thread.sleep(3000);
	          // Define the index of the button you want to click
	          int buttonIndex = 3; // Change this to the index you need (0-based index)

	          // Locate all 'Add to Cart' buttons
	          List<WebElement> buttons = driver.findElements(By.cssSelector(".add-to-cart-button"));

	          if (buttonIndex >= 0 && buttonIndex < buttons.size()) {
	              // Click the button at the specified index
	              WebElement buttonToClick = buttons.get(buttonIndex);
	              wait.until(ExpectedConditions.elementToBeClickable(buttonToClick)).click();
	              System.out.println("Clicked the 'Add to Cart' button at index: " + buttonIndex);
	          } else {
	              System.out.println("Invalid button index: " + buttonIndex);
	          }
	       
	       // Find all checkboxes within the parent <div>
	      	List<WebElement> checkboxes = driver.findElements(By.cssSelector("div.warranty-list input[type='checkbox']"));

	      	// Check if there are any checkboxes found
	      	if (!checkboxes.isEmpty()) {
	      	    // Get the first checkbox and click on it
	      	    WebElement firstCheckbox = checkboxes.get(0);
	      	    firstCheckbox.click();
	      	} else {
	      	    System.out.println("No checkboxes found.");
	      	}
	      	
	      	Thread.sleep(2000);
	      	int buttonIndex2 = 0; // Change this to the index you need (0-based index)

	          // Locate all 'Add to Cart' buttons
	          List<WebElement> button2 = driver.findElements(By.cssSelector(".add-to-cart-button"));

	          if (buttonIndex2 >= 0 && buttonIndex2 < button2.size()) {
	              // Click the button at the specified index
	              WebElement buttonToClick = button2.get(buttonIndex);
	              wait.until(ExpectedConditions.elementToBeClickable(buttonToClick)).click();
	              System.out.println("Clicked the 'Add to Cart' button successfully");
	          } else {
	              System.out.println("Invalid button index: " + buttonIndex);
	          }
	         Thread.sleep(2000);
	    	continueShoppingButton().click();
	    	
	    	// Use the homeButton variable
	        getHomeButton().click();
	      
	      
          }
    
    // 9 Navigate to Checkout page and fill out form with dummy payment
    @Test
   
    public void checkoutPagePayment() throws InterruptedException { 
    	
    	WebElement search = driver.findElement(By.id("gh-search-input"));
    	search.sendKeys("hamilton electric grill");
    	search.sendKeys(Keys.ENTER);
    	Thread.sleep(2000);
    	
    	 // Wait until the list of "Add to Cart" buttons is visible
    	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        List<WebElement> addToCartButtons = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector("button.add-to-cart-button")));

        // Ensure we have at least one button found
        if (!addToCartButtons.isEmpty()) {
            // Click the first "Add to Cart" button
            WebElement firstAddToCartButton = addToCartButtons.get(0);
            wait.until(ExpectedConditions.elementToBeClickable(firstAddToCartButton)).click();

            // Add any additional steps needed after clicking the button
            System.out.println("Clicked the first 'Add to Cart' button successfully.");
        } else {
            System.out.println("No 'Add to Cart' buttons found.");
        }
        Thread.sleep(3000);
        
        WebElement goToCart = driver.findElement(By.xpath("//a[text()=\"Go to Cart\"]"));
        goToCart.click();
        Thread.sleep(2000);
        WebElement checkout = driver.findElement(By.xpath("//button[text()=\"Checkout\"]"));
        checkout.click();
        driver.findElement(By.xpath("//button[text()=\"Continue as Guest\"]")).click();
        Thread.sleep(2000);
        // Continue to Payment Information
        WebElement emailAdd= driver.findElement(By.cssSelector("input[id=\"user.emailAddress\"]"));
        emailAdd.sendKeys("karthik123@gmail.com");
        Thread.sleep(1000);
        WebElement phoneNum = driver.findElement(By.cssSelector("input[id=\"user.phone\"]"));
        phoneNum.sendKeys(" (800) 5551212 ");
        Thread.sleep(1000);
        driver.findElement(By.xpath("//span[text()=\"Continue to Payment Information\"]")).click();
        Thread.sleep(2000);
        
     // Define the fields and their invalid/valid values
        validateField(By.id("number"), new String[]{"1234", "4111111111111111", "	4000056655665556"}, By.id("cardError"));
        validateField(By.id("first-name"), new String[]{"", "John", "Steve"}, By.id("firstNameError"));
        validateField(By.id("last-name"), new String[]{"", "Doe", "Carl"}, By.id("lastNameError"));
        validateField(By.id("address-input"), new String[]{"", "123 Main St", "Lincoln Street 2nd floor"}, By.id("addressError"));
        validateField(By.id("city"), new String[]{"", "New York", "meta"}, By.id("cityError"));
        validateField(By.id("state"), new String[]{"", "NY", "SanFransico"}, By.id("stateError"));
        validateField(By.id("postalCode"), new String[]{"123", "100016", "62500"}, By.id("zipCodeError"));

        // Click the submit button
        WebElement submitButton = driver.findElement(By.id("place-order"));
        submitButton.click();

        // Assert the result based on the error message
        try {
            WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='c-alert-content rounded-r-100 flex-fill v-bg-pure-white p-200 pl-none']/p")));
            String errorText = errorMessage.getText();
            System.out.println("Error message displayed: " + errorText);
            assertEquals("Oops, that's not a valid credit card number. Please double-check it and try again.", errorText);
        } catch (Exception e) {
            fail("Error message not displayed as expected.");
        }
    }

    private void validateField(By fieldLocator, String[] values, By errorLocator) {
        WebElement element = driver.findElement(fieldLocator);
        for (String value : values) {
            element.clear();
            element.sendKeys(value);
            element.sendKeys(Keys.TAB); // Move to next field to trigger validation

            try {
            	WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                WebElement errorMessage = wait.until(ExpectedConditions.visibilityOfElementLocated(errorLocator));
                System.out.println("Error for value '" + value + "': " + errorMessage.getText());
            } catch (Exception e) {
                System.out.println("No error for value '" + value + "'");
            }
        }
    }
        
    
    
   @After
    public void tearDown() {
        // Close the browser
        if (driver != null) {
            driver.quit();
        }
    }
    
 // Method to handle the alert
    private void handleAlert() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.alertIsPresent());
            Alert alert = driver.switchTo().alert();
            alert.dismiss(); // Clicks the "No, thanks" button to cancel the alert
        } catch (Exception e) {
            // No alert present, or another issue
            System.out.println("No alert found or error occurred: " + e.getMessage());
        }
    }
}

    
