package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class ContactsPage extends TestBase {
	
	public ContactsPage(){
		super();
		PageFactory.initElements(driver, this);
	}

	TestUtil util = new TestUtil();

	By newContactOption = By.xpath("//input[@value='New Contact']");
	public WebElement newContactOption(){
		return driver.findElement(newContactOption);
	}
	
	By firstName = By.name("first_name");
	public WebElement firstName(){
		return driver.findElement(firstName);
	}
	

	By surName = By.name("surname");
	public WebElement surName(){
		return driver.findElement(surName);
	}
	
	By companyName = By.xpath("//input[@name = 'client_lookup' and @type = 'text']");
	public WebElement companyName(){
		return driver.findElement(companyName);
	}
	
	By submitForm = By.xpath("//input[@value='Save' and @type='submit']");
	public WebElement submitForm(){
		return driver.findElement(submitForm);
	}
	
	/*
	 * Method to return New Contact Option Text for Page Validation
	 */
	/*	public boolean newContactOptionCheck(){
		boolean b = false;
		if(util.frameCheck(newContactOption())==true){
			util.changeFrame("mainpanel"); // Changing frame
			b= newContactOption().isDisplayed();
		}
		return b;
			}*/

	public boolean newContactOptionCheck() throws InterruptedException{
		util.changeFrame("mainpanel");
		Thread.sleep(6000);
		return newContactOption().isDisplayed();
	}
	

	/*
	 * Method to fill new Contact Form on Contacts Page
	 */
	public void fillNewContactForm(String title,String firstName,String lastName,String company){
		Select select = new Select(driver.findElement(By.name("title")));
		select.selectByVisibleText(title);
		firstName().sendKeys(firstName);
		surName().sendKeys(lastName);
		companyName().sendKeys(company);
		submitForm().click();
		
	}
}


