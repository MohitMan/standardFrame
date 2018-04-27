package com.disyy.standardFrame;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Component;

@Component
public class StandardMethods {

//	@Value("${Device}")
	private static String device = "Chrome";
	 
	public static HashMap<String, String> map = new HashMap<String, String>();
	public static WebDriver driver, driver1;
	
	private XSSFSheet Mainsheet, Childsheet;
    private XSSFWorkbook workbook;
    private XSSFRow row, Firstrow;
    
	
	public WebDriver getdriver() {
			if(!(System.getenv("Device") == null ))
				device = System.getenv("Device");
			
			
			if (device.equalsIgnoreCase("Chrome"))
			{ 
				driver = new ChromeDriver();
			}
			else if (device.equalsIgnoreCase("IE"))
			{
				System.setProperty("webdriver.ie.driver", "IEDriverServer.exe");
				 driver = new InternetExplorerDriver();
			}
			else if (device.equalsIgnoreCase("Firefox"))
			{
				 driver = new FirefoxDriver();
			}
			
			driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			driver.manage().window().maximize();
			 
		return driver;
	}
	
	public HashMap<String, String> readfromexcel(String scenarioname) {
        try {
               
               //Main sheet data fetch logic
        	Boolean flag = false;
        	FileInputStream file = null;
               file = new FileInputStream("Test_Data.xlsx");
               workbook = new XSSFWorkbook(file);
               Mainsheet = workbook.getSheet("Mainsheet");
                   DataFormatter formatter = new DataFormatter();
               for (int i = 1; i <= Mainsheet.getLastRowNum(); i++) {
                     row = Mainsheet.getRow(i);
                     Firstrow = Mainsheet.getRow(0);
                     if (formatter.formatCellValue(row.getCell(0)).equalsIgnoreCase(scenarioname)) {
                    	 	flag = true;
                            for (int j = 1; j < Firstrow.getLastCellNum(); j++) {
                                   map.put(formatter.formatCellValue(Firstrow.getCell(j)), formatter.formatCellValue(row.getCell(j)));
                            }
                     }
               }
               if (flag == false)
               {
            	   Assert.assertTrue("Scenario name not found in excel: "+ scenarioname, false);
               }
               
               //Child sheet data fetch logic
               String sheets[] = map.get("SheetName").split(";");
               for (String multiplesheet : sheets) {
                     Childsheet = workbook.getSheet(multiplesheet);
                     for (int i = 1; i <= Childsheet.getLastRowNum(); i++) {
                            row = Childsheet.getRow(i);
                            Firstrow = Childsheet.getRow(0);
                            if (formatter.formatCellValue(row.getCell(0)).equalsIgnoreCase(scenarioname)) {
                                   for (int j = 1; j < Firstrow.getLastCellNum(); j++) {
                                      map.put(formatter.formatCellValue(Firstrow.getCell(j)), formatter.formatCellValue(row.getCell(j)));
                                   }
                            }
                     }
               }

        } catch (Exception e) {
        	Assert.assertTrue("Scenario"+scenarioname+" not found in Data Table", false);   
        	e.printStackTrace();
        }
        return map;
 }
	
	public WebElement XPATH(By element)
	{
		WebElement path = null;
		path = driver.findElement(element);
		return path;
	}
	
	
	public List<WebElement> XPATHS(String elements)
	{
		List<WebElement> Listpath = null;
		Listpath = driver.findElements(By.xpath(elements));
		return Listpath;
	}
	
	
	public WebElement WAIT(String type, By element)
	{
		WebElement waitpass = null;
		WebDriverWait waitelement = new WebDriverWait(driver, 30);
		if (type.equalsIgnoreCase("visible"))
		waitpass = waitelement.until(ExpectedConditions.visibilityOfElementLocated(element));
		else if (type.equalsIgnoreCase("clickable"))
		waitpass = waitelement.until(ExpectedConditions.elementToBeClickable(element));
		else if (type.equalsIgnoreCase("presence"))
		waitpass = waitelement.until(ExpectedConditions.presenceOfElementLocated(element));
		else
		Assert.assertTrue("This type of wait is not exist : "+type, false);
		return waitpass;
	}
	
	
	
	public void WaitForLoading() throws Throwable {
		try
		{
			WebDriverWait wait = new WebDriverWait(driver, 100);	    	
	 		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class='Loader__spinner-container']")));
	 		Thread.sleep(2000);
	 		wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[@class='Loader__spinner-container']")));
		}	
		 catch(Exception ex)
			{
			 	String error_message = "";
				System.out.println("[INFO] ---"+ error_message + "---");
				System.out.println(ex.getMessage());
				Assert.assertTrue(error_message,false);
			}
	}
}
