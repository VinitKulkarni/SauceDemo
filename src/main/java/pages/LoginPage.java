package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {
	//Web elements
	@FindBy (xpath = "//input[@id='user-name']") private WebElement userName;
	@FindBy (xpath = "//input[@id='password']") private WebElement password;
	@FindBy (xpath = "//input[@id='login-button']") private WebElement loginButton;
	@FindBy (xpath = "//h3[text()='Epic sadface: Username and password do not match any user in this service']") private WebElement invalidErrorMsg;
	@FindBy (xpath = "//h3[text()='Epic sadface: Username is required']") private WebElement emptyErrorMsg;
	
	
	//constructor
	public LoginPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
		
		
	//methods
	public void setUserName(String uName) {
		userName.sendKeys(uName);
	}
		
	public void setPassword(String passw) {
		password.sendKeys(passw);
	}
		
	public void clickOnLoginButton(WebDriver driver) {
		loginButton.click();
	}

	public String getInvalidErroMsg() {
		return invalidErrorMsg.getText();
	}
	
	public void clearBothFields() {
		userName.clear();
		password.clear();
	}
	
	public String getEmptyErrorMsg() {
		return emptyErrorMsg.getText();
	}
}
