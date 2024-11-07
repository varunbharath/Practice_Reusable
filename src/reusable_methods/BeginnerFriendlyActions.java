package reusable_methods;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import browser_setup.Browser_setup;

//public class Base_class {//extends Browser_setup {
//	
//	protected WebDriverWait wait;
//	protected WebDriver driver;
//	
//	public  Base_class (WebDriver driver) {
//		this.driver=driver;
//		this.wait=new WebDriverWait(driver, Duration.ofSeconds(10));
//	}
//	public void click(WebElement element) {
//        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
//    }
//	// Reusable method to wait and enter text
//	    public void enterText(WebElement element, String text) {
//	        wait.until(ExpectedConditions.visibilityOf(element)).sendKeys(text);
//	    }
//	 // Reusable method to get text from an element
//	    public String getText(WebElement element) {
//	        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
//	    }
	//package reusable_methods;

	import java.io.IOException;
	import java.time.Duration;
	import org.openqa.selenium.*;
	import org.openqa.selenium.interactions.Actions;
	import org.openqa.selenium.support.ui.ExpectedConditions;
	import org.openqa.selenium.support.ui.WebDriverWait;
	import com.aventstack.extentreports.ExtentTest;
	import com.aventstack.extentreports.MediaEntityBuilder;
	import browser_setup.Browser_setup; // Assuming this is required for driver setup

	public class BeginnerFriendlyActions { // Class combines previous methods for beginners
	    
	    protected WebDriver driver;
	    protected WebDriverWait wait;
	    protected Actions actions;

	    // Constructor
	    public BeginnerFriendlyActions(WebDriver driver) {
	        this.driver = driver;
	        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10)); // Default wait
	        this.actions = new Actions(driver); // Initialize actions for user interactions
	    }

	    // Click on a web element
	    public void clickElement(WebElement element) {
	        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
	    }

	    // Enter text into an element
	    public void enterText(WebElement element, String text) {
	        wait.until(ExpectedConditions.visibilityOf(element)).clear();
	        element.sendKeys(text);
	    }

	    // Get the text of an element
	    public String getTextFromElement(WebElement element) {
	        return wait.until(ExpectedConditions.visibilityOf(element)).getText();
	    }

	    // Find element by Xpath
	    private WebElement findElementByXpath(String xpath) {
	        return driver.findElement(By.xpath(xpath));
	    }

	    // Main action handler based on the action type
	    public void performAction(String step, String action, String xpath, String text, ExtentTest test, ExtentTestManager testR) throws InterruptedException, IOException {
	        waitForLoadBar(); // Custom method to handle loading bar

	        switch (action.toLowerCase()) {
	            case "sendkey" -> sendKeysToElement(xpath, text);
	            case "action_sendkey_withclear" -> actionSendKeyWithClear(xpath, text);
	            case "actionsendkeywithtab" -> actionSendKeyWithTab(xpath, text);
	            case "enterdata" -> enterData(xpath, text, step, action, test, testR);
	            case "navigate_to_url" -> navigateToUrl(text);
	            case "pointclick" -> pointAndClick(xpath, text);
	        }
	    }

	    // Reusable method to send keys to an element
	    private void sendKeysToElement(String xpath, String text) {
	        WebElement element = findElementByXpath(xpath);
	        element.clear();
	        element.sendKeys(text);
	    }

	    // Reusable method to clear and send keys with Actions
	    private void actionSendKeyWithClear(String xpath, String text) {
	        WebElement element = findElementByXpath(xpath);
	        try {
	            actions.click(element).doubleClick().sendKeys(Keys.BACK_SPACE).perform();
	            Thread.sleep(1000); // Wait to clear
	            actions.sendKeys(element, text).perform();
	        } catch (Exception e) {
	            retrySendKeysWithActions(xpath, text);
	        }
	    }

	    // Reusable method to send keys with TAB using Actions
	    private void actionSendKeyWithTab(String xpath, String text) {
	        WebElement element = findElementByXpath(xpath);
	        try {
	            actions.click(element).doubleClick().sendKeys(Keys.BACK_SPACE).perform();
	            Thread.sleep(1000); // Wait to clear
	            actions.sendKeys(element, text).sendKeys(Keys.TAB).perform();
	        } catch (Exception e) {
	            retrySendKeysWithActions(xpath, text);
	        }
	    }

	    // Handle data entry with dropdowns, etc.
	    private void enterData(String xpath, String text, String step, String action, ExtentTest test, ExtentTestManager testR) {
	        if (text.contains("|select")) {
	            selectDropdown(xpath, text);
	        } else {
	            WebElement element = findElementByXpath(xpath);
	            element.sendKeys(text);
	        }
	    }

	    // Navigate to a specific URL
	    private void navigateToUrl(String url) {
	        driver.get(url);
	    }

	    // Click a point or element
	    private void pointAndClick(String xpath, String text) {
	        WebElement element = findElementByXpath(xpath);
	        element.click();
	    }

	    // Retry sending keys in case of failure
	    private void retrySendKeysWithActions(String xpath, String text) {
	        actions.sendKeys(findElementByXpath(xpath), text).perform();
	    }

	    // Example: Handle dropdowns (simplified)
	    private void selectDropdown(String xpath, String text) {
	        WebElement dropdown = findElementByXpath(xpath);
	        dropdown.click();
	        WebElement option = findElementByXpath("//li[text()='" + text.split("\\|")[0] + "']");
	        option.click();
	    }

	    // Wait for the loading bar to disappear (dummy implementation)
	    private void waitForLoadBar() {
	        // Implement waiting logic for loading bar if required
	    }
	}

	}


