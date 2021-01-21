package utils;

import java.util.Properties;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class ExcelDataProvider {

	WebDriver driver=null;

	@BeforeTest
	public void setUpTest() {
		String projectpath =System.getProperty("user.dir");
		System.out.println("projectpath :" +projectpath);
		System.setProperty("webdriver.gecko.driver", projectpath+"\\driver\\geckodriver\\geckodriver.exe");
		driver= new FirefoxDriver();



	}

	@Test(dataProvider = "test1data")
	public void test1(String username, String password) throws Exception {

		System.out.println(username+" | "+password);
		
		driver.get("https://www.flipkart.com/");
		
		  driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[1]/input")).sendKeys(username);

	     driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[2]/input")).sendKeys(password);
	       
	     driver.findElement(By.xpath("/html/body/div[2]/div/div/div/div/div[2]/div/form/div[3]/button")).click();
	     
		Thread.sleep(2000);
	}

	@DataProvider(name = "test1data")
	public Object[][] getData() {
		String excelPath= "D:\\My_Workspace\\DevZone\\excel\\data.xlsx";

		Object data[][] = testData(excelPath, "Sheet1");
		return data;

	}

	public static Object[] [] testData(String excelPath, String sheetName) {

		ExcelUtils excel= new ExcelUtils(excelPath, sheetName);

		int rowCount= excel.getRowCount();
		int colCount= excel.getColCount();

		Object data[] []= new Object[rowCount-1][colCount];

		for(int i=1; i<rowCount; i++ ) {
			for(int j=0; j<colCount; j++) {

				String cellData= excel.getCellDataString(i, j);  
				//System.out.print(cellData+" | ");
				data[i-1][j]= cellData;
			}
			//System.out.println();

		}
		return data;
	}
}
