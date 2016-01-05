package com.ftr.app;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class REMEDY_Login {

	WebDriver driver;

	// Constructor
	public REMEDY_Login(WebDriver driver) {

		this.driver = driver;
		new Actions(driver);

	}

	

}