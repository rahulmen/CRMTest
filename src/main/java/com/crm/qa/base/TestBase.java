/*
 * This is the Base Class and each and every class will extend this class and this class will basically contains functions which we can use in both 
 * Java PageFactory Classes and Test Classes like Selecting Browser loading the config Property File and Explicit Wait etc
 */




package com.crm.qa.base;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.crm.qa.util.TestUtil;
import com.crm.qa.util.WebEventListener;

public abstract class TestBase {

	public static WebDriver driver;
	public static Properties prop;
	
	private static DesiredCapabilities capability;



	public TestBase() {

		try{
			prop = new Properties();
			FileInputStream file = new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\com\\crm\\qa\\config\\config.properties");
			prop.load(file);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}

	}

	/*
	 * Method to Initilize Browser
	 */

	public static void intialize(){

		String browserName = prop.getProperty("browser");

		switch(browserName){
		case "chrome" : {
			System.setProperty("webdriver.chrome.driver",System.getProperty("user.dir")+"\\BrowserExes\\chromedriver.exe");
			driver = new ChromeDriver();
			break;
		}
		case "gecko" : {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\BrowserExes\\GeckoDriver.exe");
			driver = new FirefoxDriver();
			break;
		}
		case "ie" : {
			System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\BrowserExes\\IEDriver.exe");
			driver = new InternetExplorerDriver();
			break;
		}
		default : System.err.println("There is no such browser availaible");	
		}

		EventFiringWebDriver eDriver = new EventFiringWebDriver(driver);
		WebEventListener listener = new WebEventListener();
		eDriver.register(listener);
		driver = eDriver;
	

		//driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(TestUtil.page_timeout, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtil.implicit_wait, TimeUnit.SECONDS);

		driver.get(prop.getProperty("url"));

	}

/*	public DesiredCapabilities setDesiredCapability()
	{
	capability = new DesiredCapabilities();
	capability.setPlatform(Platform.WINDOWS);
	
	return capability;
		
	}*/


}
