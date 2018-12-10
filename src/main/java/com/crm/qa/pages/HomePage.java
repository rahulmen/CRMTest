package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;
import com.crm.qa.util.TestUtil;

public class HomePage extends TestBase {
	
	//Creating Object of TestUtil class
	TestUtil util = new TestUtil();
	
	@FindBy(xpath="//div[@id='navmenu']/ul/li[4]")
	WebElement contactsLink;
	
	@FindBy(xpath="//a[contains(text(),'New Contact')]")
	WebElement newContactLink;
	
	@FindBy(xpath="//a[contains(text(),'Tasks')]")
	WebElement tasksLink;
	
	By userHeader = By.xpath("//td[contains(text(),'User: Rahul Mendiratta')]");
	public WebElement returnUserHeader(){
		return driver.findElement(userHeader);
	}
	
	/*
	 * HomePage Constructor to Initialize PageFactory 
	 */
	public HomePage(){
		super();
		PageFactory.initElements(driver, this);
	}

	
	/*
	 * This method will Click on ContactsLink present on HomePage and return the Contacts Link Object 
	 */
/*	public ContactsPage clickContactsLink(){
		if(util.frameCheck(contactsLink)==true){
			util.changeFrame("mainpanel");
			contactsLink.click();
			return new ContactsPage();
		}
		else{
			return null;
		}
		}*/
	
	public ContactsPage clickContactsLink(){
		
			util.changeFrame("mainpanel");
			contactsLink.click();
			driver.switchTo().defaultContent();
			return new ContactsPage();
		
		}
	
	/*
	 * This method will Click on ContactsLink present on HomePage and return the Contacts Link Object 
	 */
	public TasksPage clickTaskLink(){
		tasksLink.click();
		return new TasksPage();
	}
	
	/*
	 * This method is to return title of HomePage
	 */
	public String returnTitle(){
		return driver.getTitle();
	}
	
	public String returnUserName(){
		util.changeFrame("mainpanel");
		return returnUserHeader().getText();
	}
	
	/*
	 * Method to mouse hover on New Contact in Contacts Tab
	 */
	
	public void peformClickonNewContact(){
		util.changeFrame("mainpanel");
		Actions action = new Actions(driver);
		action.moveToElement(contactsLink).build().perform();
		newContactLink.click();
	}
	
}
