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
import utility.UtilityClass;

public class LoginPageTest extends SauceDemoBaseClass {
	
	boolean flag;
	LoginPage loginPageObj;
	InventoryPage inventoryPageObj;

	
	@BeforeMethod
	public void preSteps() {
		loginPageObj = new LoginPage(driver);
		inventoryPageObj = new InventoryPage(driver);
		flag = false;
	}
	
	
	@Test (priority = 1)
	public void validateLoginPageUrl() throws EncryptedDocumentException, IOException {
		String actualPageUrl = driver.getCurrentUrl();
		String expectedPageUrl = UtilityClass.readDataFromExcelFile(0, 0);
		assertEquals(actualPageUrl, expectedPageUrl);
		flag = true;
	}
	
	@Test(priority = 2)
	public void validate_login_with_valid_credentials() throws IOException {
		loginPageObj.setUserName(UtilityClass.readDataFromPropertiesFile("userName"));
		loginPageObj.setPassword(UtilityClass.readDataFromPropertiesFile("password"));
		loginPageObj.clickOnLoginButton(driver);
		
		String expectedPageUrl = UtilityClass.readDataFromExcelFile(1, 0);
		String actualPageUrl = driver.getCurrentUrl();
		assertEquals(expectedPageUrl, actualPageUrl);
	}
	
	@Test(priority = 3)
	public void validate_login_with_valid_username_and_invalid_password() throws IOException {
		loginPageObj.setUserName(UtilityClass.readDataFromPropertiesFile("invalidUserName"));
		loginPageObj.setPassword(UtilityClass.readDataFromPropertiesFile("invalidPassword"));
		loginPageObj.clickOnLoginButton(driver);
		
		String expectedErrorMsg = UtilityClass.readDataFromExcelFile(8, 0);
		String actualErrorMsg = loginPageObj.getInvalidErroMsg();
		
		assertEquals(actualErrorMsg, expectedErrorMsg, "Error Msg's are not matching");
		flag = true;
	}
	
	@Test (priority = 4)
	public void validate_login_with_both_empty_fields() throws EncryptedDocumentException, IOException {
		loginPageObj.clearBothFields();
		loginPageObj.setUserName("");
		loginPageObj.setPassword("");
		loginPageObj.clickOnLoginButton(driver);
		
		String expectedErrorMsg = UtilityClass.readDataFromExcelFile(9, 0);
		String actualErrorMsg = loginPageObj.getEmptyErrorMsg();
		
		assertEquals(actualErrorMsg, expectedErrorMsg, "Empty Error Msg's are not matching");
		flag = true;
	}
	
	
	@AfterMethod
	public void postSteps() {
		if(flag == false) {
			inventoryPageObj.clickOnMenu();
			inventoryPageObj.clickOnLogOutBtn();
		}
		else {
			System.out.println("Not using logout. Bcz you are in Login Page");
		}
	}
}
