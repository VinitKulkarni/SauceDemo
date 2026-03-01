package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

public class InventoryPage {
	//web element
	@FindBy (xpath = "//button[@id='react-burger-menu-btn']") private WebElement menuBtn;
	@FindBy (xpath = "//a[@id='logout_sidebar_link']") private WebElement logOutBtn;
	@FindBy (xpath = "//div[@class='inventory_item_name ']") private List<WebElement> allProductsNamesList;
	@FindBy (xpath = "//select[@class='product_sort_container']") private WebElement productSortDropDown;
	@FindBy (xpath = "//div[@class='inventory_item_price']") private List<WebElement> allProductPriceList;
	@FindBy (xpath = "//div[text()='Sauce Labs Bike Light']") private WebElement sauceLabsBikeLightProductClick;

	
	//constructor
	public InventoryPage(WebDriver driver) {
		PageFactory.initElements(driver, this);
	}
	
	
	//method
	public void clickOnMenu() {
		menuBtn.click();
	}
	
	public void clickOnLogOutBtn() {
		logOutBtn.click();
	}
	
	public String getFirstProductPriceAfterLowToHighFilter(String key) {
		Select selectObj = new Select(productSortDropDown);
		selectObj.selectByVisibleText("Price (low to high)");
		
		if(key.equals("firstProductPrice")) {
			return allProductPriceList.get(0).getText();
		}else {
			return allProductPriceList.get(allProductPriceList.size() - 1).getText();
		}
	}
	
	public List<WebElement> getAllProductsList() {
		return allProductsNamesList;
	}
	
	public void click_on_Sauce_Labs_Bike_Light_product() {
		sauceLabsBikeLightProductClick.click();
	}
}
