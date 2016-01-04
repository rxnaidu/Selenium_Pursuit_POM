package com.ftr.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class VFO_CreateTroubleReportRequest {

	WebDriver driver;
	public static String ID = null;
	public static String TroubleReportID = null;

	By submit = By.cssSelector("img[title='Submit']");
	By IDValue = By.cssSelector("td.data");
	By TroubleReportIDValue = By.cssSelector("a.normal");

	// Constructor
	public VFO_CreateTroubleReportRequest(WebDriver driver) {

		this.driver = driver;
		new Actions(driver);

	}

	public void submit() throws InterruptedException {

		driver.findElement(submit).click();
		Thread.sleep(2000);

	}

	public void getTroubleReportID() {

		ID = driver.findElement(IDValue).getText();
		TroubleReportID = driver.findElement(TroubleReportIDValue).getText();
		// System.out.println("ID - " + ID);
		// System.out.println("TroubleReportID - " + TroubleReportID);

	}

}