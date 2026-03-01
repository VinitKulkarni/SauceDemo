package tests;

import static org.testng.Assert.assertEquals;

import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import base.SauceDemoBaseClass;
import pages.AddPersonInfoPage;
import pages.CheckOutOverViewPage;
import pages.InventoryPage;
import pages.LoginPage;
import pages.ProductPage;
import pages.ViewCartPage;
import utility.UtilityClass;

public class CheckOutOverViewPageTest extends SauceDemoBaseClass {
	
	static boolean flag = true;
	LoginPage loginPageObj;
	InventoryPage inventoryPageObj;
	ProductPage productPageObj;
	ViewCartPage viewCartPageObj;
	AddPersonInfoPage addPersonInfoPageObj;
	CheckOutOverViewPage checkOutOverViewPageObj;
	
	@BeforeMethod
	public void preSteps() throws IOException {
		LoginPage loginPageObj = new LoginPage(driver);
		loginPageObj.setUserName(UtilityClass.readDataFromPropertiesFile("userName"));
		loginPageObj.setPassword(UtilityClass.readDataFromPropertiesFile("password"));
		loginPageObj.clickOnLoginButton(driver);
		
		inventoryPageObj = new InventoryPage(driver);
		productPageObj = new ProductPage(driver);
		viewCartPageObj = new ViewCartPage(driver);

		inventoryPageObj.click_on_Sauce_Labs_Bike_Light_product();

		if(flag == true){
		productPageObj.clickOnAddToCart();
		productPageObj.clickOnViewCart();
		}else{
		   productPageObj.clickOnViewCart();
		}

		viewCartPageObj.clickOnCheckOutButton();
				
		addPersonInfoPageObj = new AddPersonInfoPage(driver);
		addPersonInfoPageObj.setFirstName(UtilityClass.readDataFromExcelFile(22, 0));
		addPersonInfoPageObj.setLastName(UtilityClass.readDataFromExcelFile(23, 0));
		addPersonInfoPageObj.setZipCode(UtilityClass.readDataFromExcelFile(24, 0));
		addPersonInfoPageObj.clickOnContinueButton();
	}
	
	@Test(priority = 1)
	public void validateCheckOutOverViewPageUrl() throws EncryptedDocumentException, IOException {
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = UtilityClass.readDataFromExcelFile(5, 0);
		assertEquals(actualUrl, expectedUrl);
		flag = false;
	}
	
	@Test(priority = 2)
	public void validate_the_sauc_labs_bike_light_info() throws EncryptedDocumentException, IOException {
		checkOutOverViewPageObj = new CheckOutOverViewPage(driver);
		
		String actualProductName = checkOutOverViewPageObj.getTheProductName();
		String expectedProductName = UtilityClass.readDataFromExcelFile(27, 0);
		assertEquals(actualProductName, expectedProductName);
		
		String actualAllProductPrice = checkOutOverViewPageObj.get_all_product_total_price_in_cart();
		String expectedAllProductPrice = UtilityClass.readDataFromExcelFile(28, 0);
		assertEquals(actualAllProductPrice, expectedAllProductPrice);
		
		String actualTax = checkOutOverViewPageObj.get_tax();
		String expectedTax = UtilityClass.readDataFromExcelFile(29, 0);
		assertEquals(actualTax, expectedTax);
		
		String actualTotalPriceWithTax = checkOutOverViewPageObj.get_total_price_including_tax();
		String expectedTotalPriceWithTax = UtilityClass.readDataFromExcelFile(30, 0);
		assertEquals(actualTotalPriceWithTax, expectedTotalPriceWithTax);
		flag = false;
	}
	
	@AfterMethod
	public void postSteps() {
		inventoryPageObj.clickOnMenu();
		inventoryPageObj.clickOnLogOutBtn();
	}
}
