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
import pages.ViewCartPage;
import utility.UtilityClass;

public class ViewCartPageTest extends SauceDemoBaseClass {
	
	static boolean flag = true;
	LoginPage loginPageObj;
	InventoryPage inventoryPageObj;
	ProductPage productPageObj;
	ViewCartPage viewCartPage;
	
	@BeforeMethod
	public void preSetup() throws IOException {
		loginPageObj = new LoginPage(driver);
		loginPageObj.setUserName(UtilityClass.readDataFromPropertiesFile("userName"));
		loginPageObj.setPassword(UtilityClass.readDataFromPropertiesFile("password"));
		loginPageObj.clickOnLoginButton(driver);
		
		inventoryPageObj = new InventoryPage(driver);
		inventoryPageObj.click_on_Sauce_Labs_Bike_Light_product();
		productPageObj = new ProductPage(driver);
		
		if(flag == true){
			productPageObj.clickOnAddToCart();
			productPageObj.clickOnViewCart();
		}else{
			   productPageObj.clickOnViewCart();
		}	
	}
	
	
	@Test(priority = 1)
	public void validateViewCartPageUrl() throws EncryptedDocumentException, IOException {
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = UtilityClass.readDataFromExcelFile(3, 0);
		assertEquals(actualUrl, expectedUrl);
		flag = false;
	}
	
	@Test(priority = 2)
	public void validate_cart__badge_after_adding_product() throws EncryptedDocumentException, IOException {
		viewCartPage = new ViewCartPage(driver);
		String actualCartBadgeValue = viewCartPage.get_cart_badge_value();
		String expectedCartBadgeValue = UtilityClass.readDataFromExcelFile(20, 0);
		assertEquals(actualCartBadgeValue, expectedCartBadgeValue);
		flag = false;
	}
	
	@Test(priority = 3)
	public void validate_cart_badge_after_remove_product() {
		viewCartPage = new ViewCartPage(driver);
		viewCartPage.remove_product_from_cart();
		String actualCartBadgeValue = viewCartPage.get_cart_badge_value();
		String expectedCartBadgeValue = "";
		assertEquals(actualCartBadgeValue, expectedCartBadgeValue);
		flag = false;
	}
	
	
	@AfterMethod
	public void postSetup() {
		inventoryPageObj.clickOnMenu();
		inventoryPageObj.clickOnLogOutBtn();
	}
}
