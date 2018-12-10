package com.crm.qa.test;


import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;



public class LoginPageTest extends TestBase {
	
LoginPage loginPage;
 HomePage homePage;


/*	public LoginPageTest() {
		super();
	}
*/

	@BeforeMethod
	public void setup(){
	loginPage = new LoginPage();
		
	}
	
	@Test(priority=1)
	public void loginPageTitleTest(){
		Assert.assertEquals(loginPage.validatePageTitile(),"#1 Free CRM software in the cloud for sales and service");
	}
	
	@Test(priority=2)
	public void crmLogoImageTest(){
		Assert.assertTrue(loginPage.validateCRMImage());
	}
	
	@Test(priority=3)
	public void loginTest(){
	homePage =	loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@AfterMethod(enabled=true)
	public void tearDown(){
		driver.quit();
	}


}
