package com.crm.qa.test;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.TestUtil;

public class HomePageTest extends TestBase {
	
	
	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage;
	TestUtil util;
	
	@BeforeMethod
	public void initialization(){
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test(priority=1)
	public void validateHomePageTitleTest(){
		String title = homePage.returnTitle();
		Assert.assertEquals(title, "CRMPRO","Page Title is not correct");
	}
	
	@Test(priority=2)
	public void validateClickContactsLinkTest() throws InterruptedException{
		contactsPage = homePage.clickContactsLink();
		Assert.assertTrue(contactsPage.newContactOptionCheck(),"Element Not Displayed");
	}
	
	@AfterMethod
	public void tearDown(){
		driver.close();
	}

}
