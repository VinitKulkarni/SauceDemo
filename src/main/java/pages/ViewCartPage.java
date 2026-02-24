package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ViewCartPage {
	//webElements
	@FindBy (xpath = "//button[@id='checkout']") private WebElement checkOutButton;
	@FindBy (xpath = "//span[text()='Your Cart']") private WebElement ViewCartPageTitle;
	@FindBy (xpath ="//button[@id='remove-sauce-labs-bike-light']") private WebElement removeProductBtn;
	@FindBy (xpath ="//a[@class='shopping_cart_link']") private WebElement cartBadgeValue;
	
	
	//constructor
	public ViewCartPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	//method
	public void clickOnCheckOutButton() {
		checkOutButton.click();
	}
	
	public String get_cart_badge_value() {
		return cartBadgeValue.getText();
	}
	
	public void remove_product_from_cart() {
		removeProductBtn.click();
	}
}
