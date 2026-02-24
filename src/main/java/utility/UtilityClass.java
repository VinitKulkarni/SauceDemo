package utility;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

public class UtilityClass {
	
	public static void globalWait(WebDriver driver, int seconds) {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(seconds));
	}
	
	//Read Data From Properties File
	public static String readDataFromPropertiesFile(String key) throws IOException {
		String PropertiesFilePath = System.getProperty("user.dir")+"/test_data/saucedemofile.properties";
		Properties prop = new Properties();
		FileInputStream reader = new FileInputStream(PropertiesFilePath);
		prop.load(reader);
		String value = prop.getProperty(key);
		return value;
	}
	
	//Read Data From Excel Sheet
	public static String readDataFromExcelFile(int rowIndex, int cellIndex) throws EncryptedDocumentException, IOException {
		String excelFilePath = System.getProperty("user.dir")+"/test_data/saucedemoexcelfile.xlsx";
		File myFile = new File(excelFilePath);
		Sheet mySheet = WorkbookFactory.create(myFile).getSheet("Sheet1");
		
		String value = mySheet.getRow(rowIndex).getCell(cellIndex).getStringCellValue();
		System.out.println("This Value Is From ExcelSheet:" + value);
		return value;
	}
	
	//Screenshot Code
	public static void takeScreenShot(WebDriver driver, String imageName) throws IOException {
		int random = (int)(Math.random() * 1000);
		imageName = imageName + "_screenshot_" + random;
		String screenShotName = System.getProperty("user.dir")+"/screenshots/"+imageName + ".jpg";
			
		File Temp = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE); 
		File desirdlc = new File(screenShotName);
		FileHandler.copy(Temp, desirdlc);
	}
}
