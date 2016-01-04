package com.ftr.app;

import java.io.IOException;

import org.openqa.selenium.WebDriver;

public interface COMMON_ACTIONS {
	
	public void navigateTo(String URL);
	
	public void editAField(String TestObject, String Value);
	
	public void editPasswordField(String TestObject, String Value);
	
	public void editAField_1(String TestObject, String Value);
		
	public void clickElement(String TestObject);
	
	/*public void clickElement1(String TestObject);*/
	
/*	public void clickButton(String TestObject);
	
	public void clickImage(String TestObject);
	
	public void clickLink(String TestObject);*/
	
	public void clearField(String TestObject);
	
	public void getText(String TestObject);
	
	public void selectRadioButton(String TestObject);
	
	/*public void selectCheckBox(String TestObject);
	
	public void unSelectCheckBox(String TestObject);*/
	
	public void checkBox(String TestObject, String status) throws IOException;
	
	public void getAttribute(String TestObject, String Attribute);
	
/*	public void selectDropDownByIndex(String TestObject, int Index);
	
	public void selectDropDownByValue(String TestObject, String Value);
	
	public void selectDropDownByText(String TestObject,	String Value); */
	
	public void selectFromDropDown(String TestObject, String Value, String Identifiers) throws IOException;
	
	public void getTitle();
	
	public void getCurrentUrl();
	
/*	public void warningOk();
	
	public void warningCancel();
	
	public void warningMessageText();*/
	
	public String Alert(String Action) throws IOException, InterruptedException;
	
	public void navigateForward();
	
	public void navigateBackward();
	
	public void refreshBrowser();
	
	/*public void selectFrame(int frameIndex);
	
	public void selectFrame(String frameName);*/
	
	public void switchFrame(String frameName);
	
	public void switchMainWindow();
	
	public void startTransaction();
	
	public void endTransaction();
	
	public void mouseOver(String TestObject);
	
	public void highlightElement(String TestObject);
	
	public void selectElement(String TestObject);
	
	public int dropDownCount(String TestObject);

	public void selectAllListBox(String TestObject);
	
	public void deSelectAllListBox(String TestObject);
	
	public void deSelectListBoxByIndex(String TestObject, int Index);
	
	public void deSelectListBoxByValue(String TestObject, String Value);
	
	public void deSelectListBoxByVisibleText(String TestObject, String Text);
	
	public WebDriver switchSecondaryWindow();
	
	public String dbResult(String Host, String Port, String Sid, String username, String password, String Query, int ColumnIndex);
	
	public String dbResult(String Host, String Port, String Sid, String username, String password, String Query, String ColumnName);
				

}
