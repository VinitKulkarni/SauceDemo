package tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

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
import pages.OrderPlacedPage;
import pages.ProductPage;
import pages.ViewCartPage;
import utility.UtilityClass;

public class OrderPlacedPageTest extends SauceDemoBaseClass {
	
	static boolean flag = true;
	LoginPage loginPageObj;
	InventoryPage inventoryPageObj;
	ProductPage productPageObj;
	ViewCartPage viewCartPageObj;
	AddPersonInfoPage addPersonInfoPageObj;
	CheckOutOverViewPage checkOutOverViewPageObj;
	OrderPlacedPage orderPlacedPageObj;
	
	@BeforeMethod
	public void preSteps() throws IOException {
		loginPageObj = new LoginPage(driver);
		loginPageObj.setUserName(UtilityClass.readDataFromPropertiesFile("userName"));
		loginPageObj.setPassword(UtilityClass.readDataFromPropertiesFile("password"));
		loginPageObj.clickOnLoginButton(driver);
		
		inventoryPageObj = new InventoryPage(driver);
		inventoryPageObj.click_on_Sauce_Labs_Bike_Light_product();
		
		productPageObj = new ProductPage(driver);
		productPageObj.clickOnAddToCart();
		productPageObj.clickOnViewCart();
		
		viewCartPageObj = new ViewCartPage(driver);
		viewCartPageObj.clickOnCheckOutButton();
		
		addPersonInfoPageObj = new AddPersonInfoPage(driver);
		addPersonInfoPageObj.setFirstName(UtilityClass.readDataFromExcelFile(22, 0));
		addPersonInfoPageObj.setLastName(UtilityClass.readDataFromExcelFile(23, 0));
		addPersonInfoPageObj.setZipCode(UtilityClass.readDataFromExcelFile(24, 0));
		addPersonInfoPageObj.clickOnContinueButton();
		
		checkOutOverViewPageObj = new CheckOutOverViewPage(driver);
		checkOutOverViewPageObj.clickOnFinish();
		
		orderPlacedPageObj = new OrderPlacedPage(driver);
	}
	
	@Test(priority = 1)
	public void validateOrderPlacedPageUrl() throws EncryptedDocumentException, IOException {
		String actualUrl = driver.getCurrentUrl();
		String expectedUrl = UtilityClass.readDataFromExcelFile(6, 0);
		assertEquals(actualUrl, expectedUrl);
	}
	
	@Test(priority = 2)
	public void validate_confirm_checkout_complete_msg() throws EncryptedDocumentException, IOException {
		String actulConfirmMsg = orderPlacedPageObj.getOrderPlacedConfirmMsg();
		String expectedMsg = UtilityClass.readDataFromExcelFile(32, 0);
		assertEquals(actulConfirmMsg, expectedMsg);
		
		assertTrue(orderPlacedPageObj.is_back_to_home_button_enabled());
	}
	
	@Test(priority = 3)
	public void validate_cart_badge_value_should_removed() throws InterruptedException {
		String actualCartBadgeValue = orderPlacedPageObj.get_cart_badge_value();
		String expectedCartBadgeValue = "";
		
		assertEquals(actualCartBadgeValue, expectedCartBadgeValue);
	}
	
	@AfterMethod
	public void postSteps() {
		inventoryPageObj.clickOnMenu();
		inventoryPageObj.clickOnLogOutBtn();
	}
}
