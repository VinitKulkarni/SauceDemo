package base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

import utility.UtilityClass;


public class SauceDemoBaseClass {
	public WebDriver driver;
	
	@Parameters("browserName")
	@BeforeClass
	public void initalSetup(String browserName) throws IOException {
		
		if(browserName.equalsIgnoreCase("chrome")) {
			final Map<String, Object> chromePrefs = new HashMap<>();
	        chromePrefs.put("credentials_enable_service", false);
	        chromePrefs.put("profile.password_manager_enabled", false);
	        chromePrefs.put("profile.password_manager_leak_detection", false);

	        final ChromeOptions chromeOptions = new ChromeOptions();
	        chromeOptions.setExperimentalOption("prefs", chromePrefs);

	        // Launch browser
	        driver = new ChromeDriver(chromeOptions);
	        //driver.get(UtilityClass.readDataFromPropertiesFile("url"));
			//driver.manage().window().maximize();
			//UtilityClass.globalWait(driver, 10);
			
		}
		else {
			driver = new FirefoxDriver();
			//driver.get(UtilityClass.readDataFromPropertiesFile("url"));
			//driver.manage().window().maximize();
			//UtilityClass.globalWait(driver, 10);
		}
		
		//driver = new FirefoxDriver();
		driver.get(UtilityClass.readDataFromPropertiesFile("url"));
		driver.manage().window().maximize();
		UtilityClass.globalWait(driver, 10);
	}
	
	@AfterClass
	public void teardown() {
		if(driver != null) {
			driver.quit();
		}
	}
}

