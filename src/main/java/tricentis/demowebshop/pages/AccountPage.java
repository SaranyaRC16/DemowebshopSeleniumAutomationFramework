package tricentis.demowebshop.pages;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import io.qameta.allure.Step;
import tricentis.demowebshop.utils.ElementsUtil;
import tricentis.demowebshopAutomation.constants.AppConstants;

public class AccountPage {

	private WebDriver driver;
	private ElementsUtil eUtil;

	private static final By categoryHeaders = By.cssSelector("div[class='header-menu'] ul[class='top-menu']>li>a");
	private static final By searchTextbox = By.cssSelector("input[value='Search store1']");
	private static final By searchButton = By.xpath("(//input[@value='Search1'])[1]");

	private final By accountInfo = By.xpath("(//a[@href='/customer/info'])[1]");

	private static final Logger log = LogManager.getLogger(AccountPage.class);

	public AccountPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementsUtil(driver);
	}

	@Step("Fetch Account Info")
	public String getAccountInfo() {
		String accountName = eUtil.getElementTextWithWait(accountInfo, AppConstants.LONGER_DEFAULT_TIME);
		log.info("User Account Name is" + accountName);
		return accountName;
	}

	@Step("Fetch Category details")
	public List<String> getCategoryDetails() {
		List<WebElement> elements = eUtil.getElementsList(categoryHeaders);
		List<String> actualCategoryDetails = new ArrayList<String>();
		for (WebElement e : elements) {
			String text = e.getText().trim();
			actualCategoryDetails.add(text);
		}
		log.info("Actual Category details displayed in accounts page is " + actualCategoryDetails.toString());
		// System.out.println("Actual Category details displayed in accounts page is
		// "+actualCategoryDetails.toString());
		return actualCategoryDetails;

	}

	@Step("Select Product")
	public ProductSearchResultPage searchProduct(String productName) {
		log.info("Product selected is " + productName);
		eUtil.sendKeysWithWait(searchTextbox, AppConstants.MEDIUM_DEFAULT_TIME, productName);
		eUtil.doClick(searchButton);
		return new ProductSearchResultPage(driver);
	}

}
