package com.ftr.app;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import com.ftr.app.REPORTER.*;

public class Setup_Teardown {

	// Date Variables
	private String vDatetype8 = "E_MMM_dd_yyyy_HH_mm_ss_a_z";
	private long vScriptStartTime = Calendar.getInstance().getTimeInMillis();

	public static WebDriver driver = null;

	Properties p = new Properties();
	FileInputStream fi = null;

	REPORTER rp = new REPORTER();

	// Constructor
	public Setup_Teardown() {

		// To read the global.properties file
		try {
			fi = new FileInputStream(System.getProperty("user.dir")
					+ "/src/main/java/com/ftr/app/global.properties");

			try {
				p.load(fi);
			} catch (IOException e) {

				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {

			e.printStackTrace();
		}
		// Load global.properties file

	}

	@BeforeTest
	public void setUp(ITestContext ic) throws IOException {

		rp.createHTMLResultTemplate(
				ic.getCurrentXmlTest().getName().toString(),
				getDateFormat(vDatetype8));

		System.out.println(p.getProperty("browser"));

		// Get Browser Name from global.properties file
		if (p.getProperty("browser").equalsIgnoreCase("firefox")) {
			driver = new FirefoxDriver();
		} else if (p.getProperty("browser").equalsIgnoreCase("chrome")) {
			// System.setProperty("webdriver.chrome.driver", "value");
			driver = new ChromeDriver();
		} else if (p.getProperty("browser").equalsIgnoreCase("ie")) {
			// System.setProperty("webdriver.chrome.driver", "value");
			driver = new InternetExplorerDriver();
		}

		// Code to get the Browser Name and Version
		Capabilities caps = ((RemoteWebDriver) driver).getCapabilities();

		// UPDATE HTML REPORT WITH THE BROWSER NAME
		rp.updateReports(updateValue.bName, caps.getBrowserName().toString()
				.toUpperCase()
				+ " / " + caps.getVersion().toString(), "");
		// UPDATE HTML REPORT WITH THE Env
		rp.updateReports(updateValue.Env, p.getProperty("Env"), "");

		driver.get(p.getProperty("VFO_url_cv01"));
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
	}

	@AfterTest
	public void killDriver(ITestContext ic) throws IOException {

		String finalScriptStatus;

		System.out.println("---------------------"
				+ ic.getFailedTests().toString());
		String sTemp = ic.getFailedTests().toString();

		rp.updateReports(updateValue.tEndTime, "", "");
		rp.updateReports(
				updateValue.execTime,
				rp.formatIntoHHMMSS(
						Calendar.getInstance().getTimeInMillis()
								- vScriptStartTime).toString(), "");
		rp.updateReports(updateValue.totalSteps, "", "");

		if (!sTemp.contains("FAILURE")) {
			finalScriptStatus = "PASS";
			rp.updateReports(updateValue.execStatus, "", finalScriptStatus);
			rp.updateReports(updateValue.failedStepNo, "", finalScriptStatus);

		} else {
			finalScriptStatus = "FAIL";
			rp.updateReports(updateValue.execStatus, "", finalScriptStatus);
			rp.updateReports(updateValue.failedStepNo, "", finalScriptStatus);
		}

	}

	public static String getDateFormat(String vDateFormat) {

		Date vDate = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat(vDateFormat);
		return sdf.format(vDate);

	}

	@AfterSuite
	public void AtAfterSuite(ITestContext ic) throws Exception {
		// String methodName =
		// Thread.currentThread().getStackTrace()[1].getMethodName();

		// Close Driver
		driver.close();
		
		// Quit Driver
		driver.quit();


	}

}
