package com.ftr.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class VFO_WorkList {

	WebDriver driver;
	private Actions actions;

	//By Ticket = By.id("menu1");
	By CreateTroubleReport = By.id("menuItemHilite48");
	By useTemplate = By.id("selectedTemplateId");
	By next = By.cssSelector("input.button");
	By serviceId = By.id("serviceId");

	// Constructor
	public VFO_WorkList(WebDriver driver) {

		this.driver = driver;
		this.actions = new Actions(driver);

	}

	public void clickCreateTroubleReport(){
		
		driver.switchTo().frame("mainFrame");	
		WebElement Ticket = driver.findElement(By.id("menu1"));
		WebElement Ticket1 = driver.findElement(By.id("menuItemHilite48"));
		actions.moveToElement(Ticket).build().perform();
		actions.moveToElement(Ticket1).build().perform();
		driver.findElement(CreateTroubleReport).click();
		
		
	}
	
	public void troubleReportInitiation(String template){
		
		new Select(driver.findElement(useTemplate)).selectByVisibleText(template);
		driver.findElement(serviceId).clear();
		driver.findElement(serviceId).sendKeys("G4/KFGS/541348/ /CZUC/");
	
		
	}
	
	public void clickNext(){
		
		driver.findElement(next).click();
		
	}
	
}
