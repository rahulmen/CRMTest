package com.crm.qa.test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.crm.qa.base.TestBase;
import com.crm.qa.pages.ContactsPage;
import com.crm.qa.pages.HomePage;
import com.crm.qa.pages.LoginPage;
import com.crm.qa.util.ExceptionHandlingUtility;
import com.crm.qa.util.TestUtil;

public class ContactPageTest extends TestBase{

	LoginPage loginPage;
	HomePage homePage;
	ContactsPage contactsPage = new ContactsPage();
	
	
	@BeforeMethod
	public void initialization(){
		loginPage = new LoginPage();
		homePage = loginPage.login(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	
	@Test(priority=1,dataProvider="provideTestData")
	public void addNewContactTest(String title,String firstName,String lastName,String company){
		homePage.peformClickonNewContact();
		contactsPage.fillNewContactForm(title,firstName,lastName,company);	
	}
	
	@DataProvider
	public Object[][] provideTestData() throws ExceptionHandlingUtility{
		 return TestUtil.getExcelData("Contact");
	}
	
	@AfterMethod
	public void tearDown(){
		driver.close();
	}

	
}
