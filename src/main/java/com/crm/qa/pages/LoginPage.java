package com.crm.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.crm.qa.base.TestBase;

public class LoginPage extends TestBase {

	//PageFactory
	@FindBy(name = "username")
	WebElement username;

	By submit = By.xpath("//div[@class='input-group-btn']/input");

	public WebElement submit(){
		return driver.findElement(submit);
	}

	@FindBy(xpath="//font[contains(text(),'Sign Up')]")
	WebElement signUp;

	@FindBy(xpath="//img[contains(@class,'img-responsive')]")
	WebElement crlLogo;

	By loginPassword = By.name("password");

	public WebElement returnLoginPassword(){
		return driver.findElement(loginPassword);
	}


	//Initializing the Page Objects
	public  LoginPage(){
		super();
		intialize();
		PageFactory.initElements(driver, this); 
	}

	//Actions :
	public String validatePageTitile(){
		return driver.getTitle();
	}

	public boolean validateCRMImage(){

		return crlLogo.isDisplayed();
	}

	public HomePage login(String un,String pwd){
		username.sendKeys(un);
		returnLoginPassword().sendKeys(pwd);
		//explicitWait().until(ExpectedConditions.elementToBeClickable(submit()));
		submit().submit();
		
		return new HomePage();
	}






}
