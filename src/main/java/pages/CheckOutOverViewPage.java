package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutOverViewPage {
	//web element
	@FindBy (xpath = "//div[@class='inventory_item_name']") private WebElement productName;
	@FindBy (xpath = "//button[@id='finish']") private WebElement finishButton;
	@FindBy (xpath = "//span[text()='Checkout: Overview']") private WebElement CheckOutOverViewPageTitle;
	@FindBy (xpath = "//div[@class='summary_subtotal_label']") private WebElement allProductPrice;
	@FindBy (xpath = "//div[@class='summary_tax_label']") private WebElement tax;
	@FindBy (xpath = "//div[@class='summary_total_label']") private WebElement totalPrice;
	
	
	//constructor
	public CheckOutOverViewPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	//method
	public void clickOnFinish() {
		finishButton.click();
	}
	
	public String getTheProductName() {
		return productName.getText();
	}
	
	public String get_all_product_total_price_in_cart() {
		return allProductPrice.getText();
	}
	
	public String get_tax() {
		return tax.getText();
	}
	
	public String get_total_price_including_tax() {
		return totalPrice.getText();
	}
}
