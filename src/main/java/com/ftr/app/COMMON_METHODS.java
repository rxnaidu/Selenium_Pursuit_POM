package com.ftr.app;



import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.Keyboard;
import org.openqa.selenium.interactions.Mouse;
import org.openqa.selenium.support.ui.Select;

import com.ftr.app.INITIALIZE.TestStatus;




/* ***************************************************************************************
 PURPOSE: Common Actions performed on Web Application
 RETURN VALUE: None
 INPUT(s): Defined in individual Subclass Methods
 AUTHOR: Ravi Naidu (rnaidu)
 CREATION DATE: Jan 01 2012
 *************************************************************************************** */
public class COMMON_METHODS extends REPORTER{

	private static int Index = 0;
	WebDriver driver;

	private String mainpage;
	public WebDriver popup;

	public enum Sel {
		id, name
	};


	
	
	// String used for reporting purpose
	String actionName = null;
	String[] sTemp = null;

	private static Actions DriverActions;
	@SuppressWarnings("unused")
	private Actions KeyboardActions;
	@SuppressWarnings("unused")
	private Actions KeyboardMouseActions;
	private Keyboard keyboard;
	private Mouse mouse;

	// Get the class name and store it in a variable
	@SuppressWarnings("unused")
	private String className = this.getClass().getSimpleName().toString()
			.toUpperCase();

	@SuppressWarnings("unused")
	private long startTransactionTime;
	@SuppressWarnings("unused")
	private long endTransactionTime;

	@SuppressWarnings("unused")
	private int startTransactionStep;
	@SuppressWarnings("unused")
	private int endTransactionStep;

	public static String sHandleBefore = "";

	public static String highlightObj;

	public enum SeleniumIdentifiers

	{
		className, cssSelector, id, linkText, name, partialLinkText, tagName, xpath, byIndex, byValue, byVisibleText
	}

	public static String baseWindow;

	@SuppressWarnings("static-access")
	public COMMON_METHODS(WebDriver driver) {

		// highlightObj = onoff;

		this.driver = driver;
		this.DriverActions = new Actions(driver);
		this.KeyboardActions = new Actions(keyboard);
		this.KeyboardMouseActions = new Actions(keyboard, mouse);
		// selenium.windowMaximize();
		this.driver.manage().window().maximize();
		mainpage = driver.getWindowHandle();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);

	}

	/**
	 * @author Ravi Naidu
	 * @param TestObject
	 *            The Test Object Value Read From The Data Table (XLS)
	 * @return Is an Array returned as sTemp
	 * @throws IOException
	 * 
	 */
	String[] splitTestObject(String TestObject) throws IOException {
		// String methodName =
		// Thread.currentThread().getStackTrace()[1].getMethodName();
		// Reporter.writeLog(methodName.toString());

		System.out.println("TestObject Value is - " + TestObject);
		//String[] sTemp = (TestObject.split(",", 4));
		String[] sTemp = (TestObject.split(","));
		//System.out.println(sTemp.length);
		
		for (int i = 0; i < sTemp.length; i++) {
			
			sTemp[i] = new String(sTemp[i].trim());
			System.out.println("sTemp[" + i +"] is " + sTemp[i]);
			
		}
		/*
		 * if (sTemp[0].contains("_")) { String[] sTemp1 = sTemp[0].split("_",
		 * 2); sTemp[0] = new String(sTemp1[1].trim());
		 * //System.out.println("sTemp[0] is " + sTemp[0]); }
		 */
/*		sTemp[0] = new String(sTemp[1].trim());
		System.out.println("sTemp[0] is " + sTemp[0]);
		sTemp[1] = new String(sTemp[1].trim());
		//System.out.println("sTemp[1] is " + sTemp[1]);
		sTemp[2] = new String(sTemp[2].trim());
		//System.out.println("sTemp[2] is " + sTemp[2]);
		sTemp[3] = new String(sTemp[3].trim());
		//System.out.println("sTemp[3] is " + sTemp[3]);
*/
		return sTemp;

	}

	/**
	 * @param TestObject
	 * @return
	 * @throws IOException
	 */
	WebElement checkIdentifier(String objName, String objIdentifier,
			String LogEvent, String Locator, String mName) throws IOException {
		// System.out.println("Inside Method checkIdentifier");
		// Reporter.writeLog(new
		// Exception().getStackTrace()[0].getMethodName().toString());
		SeleniumIdentifiers Identifier = SeleniumIdentifiers
				.valueOf(objIdentifier);
		WebElement Element = null;

		switch (Identifier) {
		case className: {

			Element = driver.findElement(By.className(Locator));
			break;
		}
		case cssSelector: {

			Element = driver.findElement(By.cssSelector(Locator));
			break;
		}
		case id: {

			Element = driver.findElement(By.id(Locator));
			break;
		}
		case linkText: {

			Element = driver.findElement(By.linkText(Locator));
			break;
		}
		case name: {

			Element = driver.findElement(By.name(Locator));
			break;
		}
		case partialLinkText: {

			Element = driver.findElement(By.partialLinkText(Locator));
			break;
		}
		case tagName: {

			Element = driver.findElement(By.tagName(Locator));
			break;
		}
		case xpath: {

			Element = driver.findElement(By.xpath(Locator));
			break;
		}
		default:
			break;

		}
		// System.out.println("Exiting Method checkIdentifier");
		return Element;

	}

	public void navigateTo(String Url) {
		try {

			actionName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			writeLog(actionName + " - " + Url);
			baseWindow = driver.getWindowHandle();
			driver.navigate().to(Url);// launch Browser with the URL

			// Report to Notepad, HTMl and Excel
			LogEvent(
					TestStatus.PASS,
					"Navigate to URL - " + Url,
					"Navigate to URL - " + Url + " - Successfull".toUpperCase(),
					"");

		} catch (Exception e) {

			catchException(e, "Navigate to the URL '" + Url + "'");

		}

		Url = null;
		actionName = null;

	}

	public void editAField(String TestObject, String Value) {
		
		try {

			actionName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			writeLog(actionName + " - " + Value);

			String[] sTemp = splitTestObject(TestObject);

			WebElement we = checkIdentifier(sTemp[0], sTemp[1], sTemp[2],
					sTemp[3], actionName);

			we.clear();
			we.sendKeys(Value);

			LogEvent(TestStatus.PASS, "Enter " + sTemp[2] + " - '" + Value
					+ "'", "Enter " + sTemp[2] + " - '" + Value + "'"
					+ " - Successfull".toUpperCase(), "");

			we = null;

		} catch (IOException e) {
			
			catchException(e, "Enter '" + sTemp[2] + "'");
		}

		actionName = null;
		Value = null;

	}

	public void editPasswordField(String TestObject, String Value) {
		

		try {

			actionName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();
			writeLog(actionName + " - " + Value);

			String[] sTemp = splitTestObject(TestObject);

			WebElement we = checkIdentifier(sTemp[0], sTemp[1], sTemp[2],
					sTemp[3], actionName);

			we.clear();
			we.sendKeys(Value);
			we = null;

			LogEvent(
					TestStatus.PASS,
					"Enter " + sTemp[2] + " - '*******'",
					"Enter " + sTemp[2] + " - '*******'"
							+ " - Successfull".toUpperCase(), "");

		} catch (IOException e) {
			
			catchException(e, "Enter '" + sTemp[2] + "'");
		}

		actionName = null;
		Value = null;

	}

	public void clickElement(String TestObject) {
		

		try {

			actionName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			writeLog(actionName + " - ");

			String[] sTemp = splitTestObject(TestObject);

			WebElement we = checkIdentifier(sTemp[0], sTemp[1], sTemp[2],
					sTemp[3], actionName);

			we.click();
			LogEvent(TestStatus.PASS, "Click '" + sTemp[2] + "'", "Click '"
					+ sTemp[2] + "'" + " - Successfull".toUpperCase(), "");

			we = null;
		} catch (IOException e) {
			
			catchException(e, "Click '" + sTemp[2] + "'");
		}

		actionName = null;
	}

	public void clearField(String TestObject) {
	

		try {

			actionName = Thread.currentThread().getStackTrace()[1]
					.getMethodName();

			String[] sTemp = splitTestObject(TestObject);

			WebElement we = checkIdentifier(sTemp[0], sTemp[1], sTemp[2],
					sTemp[3], actionName);

			we.clear();
			LogEvent(
					TestStatus.PASS,
					"Clear Field'" + sTemp[2] + "'",
					"Clear Field'" + sTemp[2] + "'"
							+ " - Successfull".toUpperCase(), "");

			we = null;
		} catch (IOException e) {
			
			catchException(e, "Clear Field '" + sTemp[2] + "'");
		}

		actionName = null;

	}

	public void getText(String TestObject) {
		
	}

	public void selectRadioButton(String TestObject) {
		actionName = Thread.currentThread().getStackTrace()[1].getMethodName();

		try {

			writeLog(actionName);

			String[] sTemp = splitTestObject(TestObject);

			WebElement we = checkIdentifier(sTemp[0], sTemp[1], sTemp[2],
					sTemp[3], actionName);
			if (!we.isSelected()) {

				we.click();
				LogEvent(TestStatus.PASS, "Select Radio Button '" + sTemp[2]
						+ "'", "Select Radio Button '" + sTemp[2] + "'"
						+ " - Successfull".toUpperCase(), "");
			} else {
				LogEvent(TestStatus.WARNING, "Select the Radio Button '"
						+ sTemp[2] + "'", "Radio Button '" + sTemp[2]
						+ "' has already been selected", "");
			}
		} catch (Exception e) {

			catchException(e, "Select Radio Button '" + sTemp[2] + "'");
		}

		sTemp = null;
		Runtime.getRuntime().gc();

	}

	public void checkBox(String TestObject, String status) throws IOException {

		actionName = Thread.currentThread().getStackTrace()[1].getMethodName();
		writeLog(actionName);

		String[] sTemp = splitTestObject(TestObject);
		WebElement we = checkIdentifier(sTemp[0], sTemp[1], sTemp[2], sTemp[3],
				actionName);
		if (status.equalsIgnoreCase("check")) {

			try {

				if (!we.isSelected()) {

					we.click();
					LogEvent(TestStatus.PASS, "Check Checkbox '" + sTemp[2]
							+ "'", "Check checkbox '" + sTemp[2] + "'"
							+ "' - Successfull".toUpperCase(), "");
				} else
					LogEvent(TestStatus.WARNING, "Check Checkbox '" + sTemp[2]
							+ "'", "Checkbox '" + sTemp[2]
							+ "' has already been checked", "");
			} catch (Exception e) {
				catchException(e, "Check Checkbox '" + sTemp[2] + "'");
			}

		} else if (status.equalsIgnoreCase("uncheck")) {

			try {

				if (we.isSelected()) {

					we.click();
					LogEvent(TestStatus.PASS, "Uncheck Checkbox '" + sTemp[2]
							+ "'", "Uncheck Checkbox '" + sTemp[2] + "'"
							+ "' - Successfull".toUpperCase(), "");
				} else
					LogEvent(TestStatus.WARNING, "Uncheck Checkbox '"
							+ sTemp[2] + "'", "Checkbox '" + sTemp[2]
							+ "' has already been unchecked", "");
			} catch (Exception e) {
				catchException(e, "Uncheck the checkbox '" + sTemp[2] + "'");
			}

		} else {
			LogEvent(TestStatus.FAIL, "Check / Uncheck - Check Box",
					"Wrong Value Passed As Parameter", "");

		}

		sTemp = null;
		status = null;
		Runtime.getRuntime().gc();

	}

	public void getAttribute(String TestObject, String Attribute) {
		

	}

	public void selectFromDropDown(String TestObject, String Identifiers,
			String Value) throws IOException {

		actionName = Thread.currentThread().getStackTrace()[1].getMethodName();
		writeLog(actionName);

		SeleniumIdentifiers Identifier = SeleniumIdentifiers
				.valueOf(Identifiers);

		String[] sTemp = splitTestObject(TestObject);

		Select select = new Select(checkIdentifier(sTemp[0], sTemp[1],
				sTemp[2], sTemp[3], actionName));

		if (Value.startsWith("#")) {

			String[] nValue = Value.split("#");
			// System.out.println("nValue[1]is - " + nValue[1]);
			Index = Integer.parseInt(nValue[1]);
			// System.out.println("Index is - " + Index);
		}

		switch (Identifier) {
		case byIndex: {
			String sTemp1 = null;
			try {

				select.selectByIndex(Index);
				sTemp1 = select.getOptions().get(Index).getText();
				LogEvent(TestStatus.PASS, "Select '" + sTemp1
						+ "' from dropdown '" + sTemp[2] + "'", "Select '"
						+ sTemp1 + "' from dropdown '" + sTemp[2] + "'"
						+ " - Successfull".toUpperCase(), "");
			} catch (Exception e) {
				catchException(e, "Select '" + sTemp1 + "' from dropdown '"
						+ sTemp[2] + "'");
			}
			break;
		}
		case byValue: {
			try {

				select.selectByValue(Value);

				LogEvent(TestStatus.PASS, "Select '" + Value
						+ "' from dropdown '" + sTemp[2] + "'", "Select '"
						+ Value + "' from dropdown '" + sTemp[2] + "'"
						+ " - Successfull".toUpperCase(), "");
			} catch (Exception e) {
				catchException(e, "Select '" + Value + "' from dropdown '"
						+ sTemp[2] + "'");
			}
			break;
		}
		case byVisibleText: {
			try {

				select.selectByVisibleText(Value);

				LogEvent(TestStatus.PASS, "Select '" + Value.toUpperCase()
						+ "' from dropdown List '" + sTemp[2] + "'", "Select '"
						+ Value.toUpperCase() + "' from dropdown List '"
						+ sTemp[2] + " - Successfull".toUpperCase(), "");
			} catch (Exception e) {
				catchException(e, "Select '" + Value + "' from dropdown '"
						+ sTemp[2] + "'");
			}
			break;
		}
		default:
			break;

		}
		select = null;
		sTemp = null;
		Value = null;
		Runtime.getRuntime().gc();

	}

	public void getTitle() {
	
		
		actionName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			writeLog(actionName);
			LogEvent(TestStatus.PASS, "Get the current URL of the page",
					"The current URL of the page is '" + driver.getTitle() + "'",
					"");
		} catch (IOException e) {
			
			e.printStackTrace();
		}


		

	}

	public void getCurrentUrl() {
		

		actionName = Thread.currentThread().getStackTrace()[1].getMethodName();
		try {
			writeLog(actionName);
			
			LogEvent(TestStatus.PASS, "Get the current URL of the page",
					"The current URL of the page is '" + driver.getCurrentUrl()
							+ "'", "");
		} catch (IOException e) {
			
			e.printStackTrace();
		}

		
		

	}

	public String Alert(String Action) throws IOException {
		
		actionName = Thread.currentThread().getStackTrace()[1].getMethodName();
		writeLog(actionName);
		
		String alertText = null;
		
		try {
			
			if ( Action.equalsIgnoreCase("accept") ) {
				
				alertText = driver.switchTo().alert().getText();
				driver.switchTo().alert().accept();	
				
				LogEvent(TestStatus.PASS, "Accept Alert",
						"Accept Alert with Message '" + alertText
								+ "' - Successfull".toUpperCase(), "");

			}
			else if ( Action.equalsIgnoreCase("dismiss") ) {
				
				alertText = driver.switchTo().alert().getText();
				driver.switchTo().alert().dismiss();
				
				LogEvent(TestStatus.PASS, "Dismiss Alert",
						"Dismiss Alert with Message '" + sTemp
								+ "' - Successfull".toUpperCase(), "");

			}
			/*else if ( Action.equalsIgnoreCase("getMsg") ){
				
				alertText = driver.switchTo().alert().getText();
				
				LogEvent(TestStatus.PASS, "Get Alert Text",
						"Alert Text - '" + alertText , "");

			}*/ else {
				
				LogEvent(TestStatus.MSG, "Alert Method",
						"check parameter passed to " + actionName + "Method" , "");
			
			}
			
		} catch (Exception e) {
			
			LogEvent(TestStatus.FAIL, "Dismiss Alert",
					"No Alert message present", "");
		}
		return alertText;
	
	
	}

	public void navigateForward() {
		
		
		try {
			
			driver.navigate().forward();
			
		} catch (Exception e) {
			
		}

	}

	public void navigateBackward() {
		
		try {
			driver.navigate().back();
			
		} catch (Exception e) {
			
		}

	}

	public void refreshBrowser() {
		
		
		try {
			
			driver.navigate().refresh();
			
		} catch (Exception e) {
			
		}

	}

	public void switchFrame(String frameName) {

		try {

			if (frameName.startsWith("#")) {

				String[] nValue = frameName.split("#");
				Index = Integer.parseInt(nValue[1]);
				driver.switchTo().frame(Index);
			} else {

				driver.switchTo().frame(frameName);

			}

		} catch (Exception e) {
			
		}

	}
	
	public void switchMainWindow() {
		
		
		try {
			WebDriver mainWindow = driver.switchTo().window(mainpage);
			this.driver = mainWindow;
			
		} catch (Exception e) {
			
		}

	}

	public void startTransaction() {
		

	}

	public void endTransaction() {

	}

	public void mouseOver(String TestObject) {
		

		try {
			writeLog(actionName + " - ");

			String[] sTemp = splitTestObject(TestObject);

			WebElement we = checkIdentifier(sTemp[0], sTemp[1], sTemp[2],
					sTemp[3], actionName);

			DriverActions.moveToElement(we).build().perform();
			
		} catch (Exception e) {

			
		}
		sTemp = null;

	}

	public void highlightElement(String TestObject) {
		

	}

	public void selectElement(String TestObject) {
		
	}

	public int dropDownCount(String TestObject) {
		
		return 0;
	}

	public void selectAllListBox(String TestObject) {
		

	}

	public void deSelectAllListBox(String TestObject) {
		

	}

	public void deSelectListBoxByIndex(String TestObject, int Index) {
		

	}

	public void deSelectListBoxByValue(String TestObject, String Value) {
		
	}

	public void deSelectListBoxByVisibleText(String TestObject, String Text) {
		

	}

	public WebDriver switchSecondaryWindow() {
		
		return null;
	}

	public String dbResult(String Host, String Port, String Sid,
			String username, String password, String Query, int ColumnIndex) {
		
		return null;
	}

	public String dbResult(String Host, String Port, String Sid,
			String username, String password, String Query, String ColumnName) {
		
		return null;
	}

	public void editAField_1(String TestObject, String Value) {
		
	}

}