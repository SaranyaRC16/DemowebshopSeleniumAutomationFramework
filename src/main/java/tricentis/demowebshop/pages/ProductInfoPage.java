package tricentis.demowebshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import tricentis.demowebshop.utils.ElementsUtil;
import static tricentis.demowebshopAutomation.constants.AppConstants.*;

public class ProductInfoPage {
	
	private WebDriver driver;
	private ElementsUtil eUtil;
	
	private final By productTitle = By.tagName("h1");
	private final By productThumnbnails = By.cssSelector("div[class='picture-thumbs'] a");
	private final By productPrice = By.cssSelector("span[itemprop='price']");
	
	public ProductInfoPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementsUtil(driver);
	}
	
	
	public String getProductTitle() {
		String prodTitle = eUtil.getElementText(productTitle);
		System.out.println("Product Title is "+prodTitle);
		return prodTitle;
	}
	
	public String getProductPrice() {
		String prodPrice = eUtil.getElementText(productPrice);
		System.out.println("Product Price is "+prodPrice);
		return prodPrice;
	}
	
	public int getProductThumbnailCount() {
		int prodThumbnailCount = eUtil.waitForAllElementsVisible(productThumnbnails, SHORT_DEFAULT_TIME).size();
		System.out.println("Product Thumbnail Count is "+prodThumbnailCount);
		return prodThumbnailCount;
	}
	

}
