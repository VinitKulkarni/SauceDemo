package tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.SauceDemoBaseClass;
import pages.InventoryPage;
import pages.LoginPage;
import pages.ProductPage;
import utility.UtilityClass;

public class ProductPageTest extends SauceDemoBaseClass {
	
	LoginPage loginPageObj;
	ProductPage productPageObj;
	InventoryPage inventoryPageObj;
	
	@BeforeMethod
	public void preSetup() throws IOException {
		loginPageObj = new LoginPage(driver);
		loginPageObj.setUserName(UtilityClass.readDataFromPropertiesFile("userName"));
		loginPageObj.setPassword(UtilityClass.readDataFromPropertiesFile("password"));
		loginPageObj.clickOnLoginButton(driver);
		
		inventoryPageObj = new InventoryPage(driver);
		inventoryPageObj.click_on_Sauce_Labs_Bike_Light_product();
		
		productPageObj = new ProductPage(driver);
	}
	
	
	@Test(priority = 1)
	public void validateProductPageUrl() throws EncryptedDocumentException, IOException {
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = UtilityClass.readDataFromExcelFile(2, 0);
		assertEquals(actualUrl, expectedUrl);
	}
	
	@Test(priority=2)
	public void validateProductInformation() throws EncryptedDocumentException, IOException {
		String actualProductName = productPageObj.getTitleOfProduct();
		String actualProductDesc = productPageObj.getDescriptionOfProduct();
		String actualProductPrice = productPageObj.getPriceOfProduct();
		Boolean actualCartBtnEnabled = productPageObj.addToCartBtnPresent();
		
		String expectedProductName = UtilityClass.readDataFromExcelFile(16, 0);
		String expectedProductDesc = UtilityClass.readDataFromExcelFile(17, 0);
		String expectedProductPrice = UtilityClass.readDataFromExcelFile(18, 0);
		Boolean expectedCartBtnEnabled = true;
		
		assertEquals(actualProductName, expectedProductName, "Error:Product name not matching");
		assertEquals(actualProductDesc, expectedProductDesc, "Error:Product Description not matching");
		assertEquals(actualProductPrice, expectedProductPrice, "Error:Product price not matching");
		assertEquals(actualCartBtnEnabled, expectedCartBtnEnabled, "Error:Add to cart button problem");
	}
	
	
	@AfterMethod
	public void postSetups() {
		inventoryPageObj.clickOnMenu();
		inventoryPageObj.clickOnLogOutBtn();
	}
}
