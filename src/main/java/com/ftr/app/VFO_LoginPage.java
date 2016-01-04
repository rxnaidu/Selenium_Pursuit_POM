package com.ftr.app;

//import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.Select;

public class VFO_LoginPage {

	WebDriver driver;
	//COMMON_METHODS cm = null;
	//static final Logger log = Logger.getLogger(VFO_LoginPage.class);

	By UserName = By.id("loginName");
	By Password = By.id("password");
	By Module = By.cssSelector("select[name='serviceRequestType']");
	By Login = By.cssSelector("a > img");
	By ChangePassword = By.cssSelector("input[name='changePassword']");

	// Constructor
	public VFO_LoginPage(WebDriver driver) {

		this.driver = driver;
		//cm = new COMMON_METHODS(driver);

	}

	public void setUName(String uName) {

		driver.findElement(UserName).sendKeys(uName);
		//log.info("--------------------------------------");
	}

	public void setPWD(String pwd) {

		driver.findElement(Password).sendKeys(pwd);

	}

	public void setModule(String mod) {

	
		new Select(driver.findElement(By.name("serviceRequestType"))).selectByVisibleText(mod);
		
	}
	
	public void signIN() {

		
		driver.findElement(Login).click();
		
	}
}
