package com.crm.qa.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.crm.qa.base.TestBase;

public class TestUtil extends TestBase {

	
	static XSSFWorkbook book;
	static XSSFSheet sh;
	static FileInputStream fileInput;
	static ExceptionHandlingUtility exceptionHanding;

	public static long page_timeout = 20;
	public static long implicit_wait = 10;

	/*
	 * Method to Change Frame
	 */



	public void changeFrame(String name){
		driver.switchTo().frame(name);
	}



	/*
	 * Method to Check ELement in all frame present on a page
	 */
	public boolean frameCheck(WebElement element){

		List<WebElement> frameCheck = driver.findElements(By.tagName("frame"));

		boolean b = false;

		int numberofFrame = frameCheck.size();

		li:
			for(int i=0;i<numberofFrame;i++){
				driver.switchTo().frame(i);
				if(element.isDisplayed()){
					b = true;
					break li;
				}
			}
		driver.switchTo().defaultContent();
		return b;

	}

	/*
	 * Method to define explicit wait
	 */
	public  WebDriverWait explicitWait(){
		return new WebDriverWait(driver,10);

	}

	/*
	 * Method to fetch Data from excel line by line
	 */

	public static Object[][] getExcelData(String sheetName) throws ExceptionHandlingUtility {


		//File excelFile = new FileInputStream("")
		try{
			fileInput = new FileInputStream(prop.getProperty("excel_File_Path"));
			book = new XSSFWorkbook(fileInput);
		}catch(FileNotFoundException e){
					e.printStackTrace();
		}catch(IOException e){
			throw new ExceptionHandlingUtility("IO Not Found"+ e.getMessage());
		}

		sh = book.getSheet(sheetName);
		Object[][] obj = new Object[sh.getLastRowNum()][sh.getRow(0).getLastCellNum()];


		for(int i=0;i<sh.getLastRowNum();i++){
			for(int j=0;j<sh.getRow(i).getLastCellNum();j++){

				obj[i][j] = sh.getRow(i+1).getCell(j).toString();
			}
		}
		return obj;
	}
	
	/*
	 * Method to capture ScreenShot when test fails
	 */
	
	public static void takeScreenshotAtEndOfTest() throws IOException{
		
		File FileSrcNew= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentDir=System.getProperty("user.dir");
		FileUtils.copyFile(FileSrcNew, new File(currentDir+"//ScreenShot//"+System.currentTimeMillis()+".png"));
		
	}

}
