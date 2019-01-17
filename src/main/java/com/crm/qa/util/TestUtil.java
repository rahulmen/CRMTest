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


	private static XSSFWorkbook book;
	private static XSSFSheet sh;
	private static FileInputStream fileInput;
	private static ExceptionHandlingUtility exceptionHanding;

	public static long page_timeout = 20;
	public static long implicit_wait = 10;
	
	
	static {
		try {
		fileInput = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//com//crm//qa//testdata//"+prop.getProperty("excel_File_name"));
		book = new XSSFWorkbook(fileInput);
	}catch(FileNotFoundException e){
		e.printStackTrace();
	}catch(IOException e){
	try {
		throw new ExceptionHandlingUtility("IO Not Found"+ e.getMessage());
		} catch (ExceptionHandlingUtility e1) {
			e1.getMessage();
		}
	}
	}

	/*
	 * Method to Change Frame
	 * @author mendirah
	 */
	public void changeFrame(String name){
		driver.switchTo().frame(name);
	}

	/*
	 * Function to Load Excel file to be executed
	 * @author mendirah
	 */

	public static void loadExcelToBeExecuted() throws ExceptionHandlingUtility {
		try{
			fileInput = new FileInputStream(System.getProperty("user.dir")+"//src//main//java//com//crm//qa//testdata//"+prop.getProperty("excel_File_name"));
			book = new XSSFWorkbook(fileInput);
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			throw new ExceptionHandlingUtility("IO Not Found"+ e.getMessage());
		}

	}

	/*
	 * Method to Check ELement in all frame present on a page
	 * @author mendirah
	 * return true/false
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
				}}
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
	 * Method to fetch Data from excel for selected TestCase
	 * @author mendirah
	 * @return 2-D Object Array  
	 */
	public static Object[][] getExcelData(String sheetName) throws ExceptionHandlingUtility {

		sh = book.getSheet(sheetName);
		int actualTestCase=countNumberOfTestCase("Contact");
		Object[][] obj = new Object[actualTestCase][sh.getRow(0).getLastCellNum()-1];
		int testCaseCount=0;
		l2:
			for(int i1=1;i1<=sh.getLastRowNum();i1++){
				if(sh.getRow(i1).getCell(0).toString().equalsIgnoreCase("No")) {
					continue l2;
				}
				for(int j=0;j<sh.getRow(i1).getLastCellNum()-1;j++){	
					obj[testCaseCount][j] = sh.getRow(i1).getCell(j+1).toString();
				}
				if(testCaseCount<actualTestCase) {
					testCaseCount++;
				}else {
					break l2;
				}			
			}
		return obj;
	}


	/*
	 * Method to write data into excel
	 * @author mendirah
	 */

	public void setExcelData(String sheetName) throws ExceptionHandlingUtility {
		
		sh = book.getSheet(sheetName);

	}

	/*
	 * Method to calculate number of TestCase to be executed in excelFile
	 * @author mendirah
	 * @return testCase Count to be executed
	 */

	public static int countNumberOfTestCase(String sheetName) throws ExceptionHandlingUtility {
		int testCasecount=0;
		sh = book.getSheet(sheetName);
		for(int i=1;i<=sh.getLastRowNum();i++) {

			if(sh.getRow(i).getCell(0).toString().equals("Yes")) {
				testCasecount++;
			}
		}
		System.out.println(testCasecount);
		return testCasecount;
	}

	/*
	 * Method to capture ScreenShot when test fails
	 * @author mendirah
	 */

	public static void takeScreenshotAtEndOfTest() throws IOException{

		File FileSrcNew= ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String currentDir=System.getProperty("user.dir");
		FileUtils.copyFile(FileSrcNew, new File(currentDir+"//ScreenShot//"+System.currentTimeMillis()+".png"));

	}

}
