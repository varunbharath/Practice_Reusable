package pom;


	import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import reusable_methods.Base_class;
	public class loginpage extends Base_class{
	
	@FindBy(xpath = "(//input[@id='user'])[1]")
	public static WebElement usernamefield;
	@FindBy(xpath = "(//input[@id='pass'])[1]")
	public static WebElement passwordfield;
	@FindBy(xpath = "(//input[@id='pass'])[1]")
	public static WebElement loginfield;

public loginpage(WebDriver driver) {
	super(driver);
	PageFactory.initElements(driver,this);
	
	
}
//login method
public void login(String username,String password) {
	enterText(usernamefield, username);
	enterText(passwordfield, password);
	click(loginfield);
}
	}



