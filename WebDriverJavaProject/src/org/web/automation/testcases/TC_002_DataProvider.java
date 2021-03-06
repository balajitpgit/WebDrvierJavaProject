package org.web.automation.testcases;

import java.io.FileInputStream;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.web.automation.base.InitiateDriver;

public class TC_002_DataProvider extends InitiateDriver {
	
@Test(dataProvider="Excel")
private void tc_002(String User, String Pass)   {
	
	driver.findElement(By.xpath("//input[@name='email']")).sendKeys(User);
	driver.findElement(By.xpath("//input[@name='pass']")).sendKeys(Pass);
}


@DataProvider(name="Excel") // <-----Data driven using input from Excel sheet
public Object[][] testDataGenerator1() throws Exception
{
	FileInputStream file = new FileInputStream("./TestData/TestData.xlsx");
	XSSFWorkbook workbook = new XSSFWorkbook(file);
	XSSFSheet loginsheet = workbook.getSheet("Login");
	int numberOfData = loginsheet.getPhysicalNumberOfRows();
	
	Object[][] testData = new Object[numberOfData][2];
	
	for(int i=0;i<numberOfData;i++)
	{
		XSSFRow row = loginsheet.getRow(i);
		XSSFCell username = row.getCell(0);
		XSSFCell password = row.getCell(1);
		testData[i][0] = username.getStringCellValue();
		testData[i][1] = password.getStringCellValue();
	}
	return testData;
}


@DataProvider(name="Static") //<-----------------Static Data provider class
public Object[][] testDataGenerator()
{
	Object[][] data = {{"name1","Pass1"},{"name2","Pass2"},{"name3","Pass3"}};
	return data;
}

}
