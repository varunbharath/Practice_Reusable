package execution;

import org.testng.annotations.Test;

import browser_setup.Browser_setup;
import pom.loginpage;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;

import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;

public class Test_execution {
	 public static WebDriver driver;
	 public static Properties prop;
	// Loginpage=new loginpage(driver);
  @Test
  public void f() {
	  loginpage obj=new loginpage(null);
	 // obj.login(null, null);
	  obj.login(null, null);
	  
  }
  @BeforeMethod
  public void beforeClass() {
	 this.driver=Browser_setup.driver;
	 this.prop=Browser_setup.prop;
	  
  }

  @AfterMethod
  public void afterClass() {
  }

}
