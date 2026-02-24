package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPlacedPage {
	//web elements
	@FindBy (xpath = "//span[text()='Checkout: Complete!']") private WebElement orderPlacedPageTitle;
	@FindBy (xpath = "//h2[text()='Thank you for your order!']") private WebElement orderConfirmMsg;
	@FindBy (xpath = "//button[@id='back-to-products']") private WebElement backToHomeBtn;
	@FindBy (xpath = "//div[@id='shopping_cart_container']") private WebElement cartBadgeValue;
	
	
	//constructor
	public OrderPlacedPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	//methods
	public String getOrderPlacedConfirmMsg() {
		return orderConfirmMsg.getText();
	}
	
	public boolean is_back_to_home_button_enabled() {
		return backToHomeBtn.isEnabled();
	}
	
	public String get_cart_badge_value() {
		return cartBadgeValue.getText();
	}
}
