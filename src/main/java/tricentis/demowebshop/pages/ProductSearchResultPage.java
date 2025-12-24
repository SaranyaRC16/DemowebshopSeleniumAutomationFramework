package tricentis.demowebshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import tricentis.demowebshop.utils.ElementsUtil;
import tricentis.demowebshop.utils.TimeUtils;

import static tricentis.demowebshopAutomation.constants.AppConstants.*;

public class ProductSearchResultPage {

	private WebDriver driver;
	private ElementsUtil eUtil;

	private final By products = By.cssSelector("div[class='item-box']");
	
	public ProductSearchResultPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementsUtil(driver);

	}
	

	public int getSearchProductResultsCount() {
		TimeUtils.shortWait();
		return eUtil.waitForAllElementsVisible(products, LONGER_DEFAULT_TIME).size();
		
	}

	public ProductInfoPage selectProduct(String productName) {
		System.out.println("Selected Product name is "+productName);
		eUtil.clickWithWait(By.linkText(productName),MEDIUM_DEFAULT_TIME);
		return new ProductInfoPage(driver);
	}
	
}
