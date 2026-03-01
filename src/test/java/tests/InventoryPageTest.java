package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.SauceDemoBaseClass;
import pages.InventoryPage;
import pages.LoginPage;
import utility.UtilityClass;


public class InventoryPageTest extends SauceDemoBaseClass {
	
	boolean flag;
	InventoryPage inventoryPageObj;
	
	
	@BeforeMethod
	public void preSteps() throws IOException {
		LoginPage loginPageObj = new LoginPage(driver);
		loginPageObj.setUserName(UtilityClass.readDataFromPropertiesFile("userName"));
		loginPageObj.setPassword(UtilityClass.readDataFromPropertiesFile("password"));
		loginPageObj.clickOnLoginButton(driver);
		
		inventoryPageObj = new InventoryPage(driver);
	}
	
	
	@Test(priority = 1)
	public void validateInventoryPageUrl() throws EncryptedDocumentException, IOException {
		String actualPageUrl = driver.getCurrentUrl();
		String expectedPageUrl = UtilityClass.readDataFromExcelFile(1, 0);
		assertEquals(actualPageUrl, expectedPageUrl);
	}
	
	@Test(priority = 2)
	public void validateLowToHighPriceFilter() throws EncryptedDocumentException, IOException {
		String actualFirstProductPrice = inventoryPageObj.getFirstProductPriceAfterLowToHighFilter("firstProductPrice");
		String actualLastProductPrice = inventoryPageObj.getFirstProductPriceAfterLowToHighFilter("lastProductPrice");
		
		String expectedFirstProductPrice = UtilityClass.readDataFromExcelFile(11, 0);
		String expectedLastProductPrice = UtilityClass.readDataFromExcelFile(12, 0);
		
		List<WebElement> actualAllProductsNames = inventoryPageObj.getAllProductsList();
		String actualFirstProductName = actualAllProductsNames.get(0).getText();
		String actualLastProductName = actualAllProductsNames.get(actualAllProductsNames.size() - 1).getText();
		
		String expectedFirstProductName = UtilityClass.readDataFromExcelFile(13, 0);
		String expectedLastProductName = UtilityClass.readDataFromExcelFile(14, 0);
		
		
		assertEquals(actualFirstProductPrice, expectedFirstProductPrice);
		assertEquals(actualFirstProductName, expectedFirstProductName);
		
		assertEquals(actualLastProductPrice, expectedLastProductPrice);
		assertEquals(actualLastProductName, expectedLastProductName);	
	}
	
	@Test (priority = 3)
	public void validate_all_products_disaplyed() {
		List<WebElement> actualAllProductsNames = inventoryPageObj.getAllProductsList();
		List<String> expectedAllProductsNames = Arrays.asList("Sauce Labs Backpack", "Sauce Labs Bike Light", "Sauce Labs Bolt T-Shirt", "Sauce Labs Fleece Jacket", "Sauce Labs Onesie", "Test.allTheThings() T-Shirt (Red)");
		
		//compare actualAllProductsNames with expectedAllProductsNames
		for(int i=0; i<actualAllProductsNames.size(); i++) 
		{
			if(actualAllProductsNames.get(i).getText().equals(expectedAllProductsNames.get(i))) 
			{
				flag = true;
			}
			else 
			{
				flag = false;
				break;
			}
		}
		assertTrue(flag);
	}
	
	
	@AfterMethod
	public void postSetups() {
		inventoryPageObj.clickOnMenu();
		inventoryPageObj.clickOnLogOutBtn();
	}
}
