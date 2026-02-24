package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	//web elements
	@FindBy (xpath = "//button[@id='add-to-cart']") private WebElement addToCartButton;
	@FindBy (xpath = "//a[@class='shopping_cart_link']") private WebElement clickOnViewCartButton;
	@FindBy (xpath = "//button[@id='back-to-products']") private WebElement productPageTitle;
	@FindBy (xpath = "//div[text()='Sauce Labs Bike Light']") private WebElement productName;
	@FindBy (xpath = "//div[@class='inventory_details_desc large_size']") private WebElement productDescription;
	@FindBy (xpath = "//div[@class='inventory_details_price']") private WebElement productPrice;
	@FindBy (xpath = "//button[@id='add-to-cart']") private WebElement addToCartBtn;
	@FindBy (xpath = "//button[@id='remove']") private WebElement removeProductFromCartBtn;
	
	
	//constructor
	public ProductPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
		
		
	//methods
	public void clickOnAddToCart() {
		addToCartButton.click();
	}
		
	public void clickOnViewCart() {
		clickOnViewCartButton.click();
	}
	
	public String getPageTitle() {
		return productPageTitle.getText();
	}
	
	public String getTitleOfProduct() {
		return productName.getText();
	}
	
	public String getDescriptionOfProduct() {
		return productDescription.getText();
	}
	
	public String getPriceOfProduct() {
		return productPrice.getText();
	}
	
	
	public boolean addToCartBtnPresent() {
		return addToCartButton.isEnabled();
	}
	
	public boolean backToProductListBtnPresent() {
		return productPageTitle.isDisplayed();
	}
	
	public boolean addToCartBtnDisplayed() {
		return addToCartBtn.isDisplayed();
	}
	
	public boolean removeProductFromCartBtnDisplayed() {
		return removeProductFromCartBtn.isDisplayed();
	}
}
