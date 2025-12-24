package tricentis.demowebshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import io.qameta.allure.Step;
import tricentis.demowebshop.utils.ElementsUtil;
import static tricentis.demowebshopAutomation.constants.AppConstants.*;

public class HomePage {

	private WebDriver driver;
	private ElementsUtil eleUtil;

	// Locators
	private final By registerLink = By.xpath("//a[text()='Register']");
	private final By loginLink = By.className("ico-login");
	private final By shoppingCartLink = By.cssSelector("li[id='topcartlink'] span[class='cart-label']");
	private final By wishlistLink = By.xpath("(//a[@class='ico-wishlist'])[1]");
	private final By email = By.id("Email");
	private final By password = By.id("Password");
	private final By loginButton = By.cssSelector("input[value='Log in']");
	private final By logo = By.xpath("//img[@alt='Tricentis Demo Web Shop']");
	private final By logoutLink = By.className("ico-logout");

	public HomePage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementsUtil(driver);
	}

	// page actions
	@Step("Navigate to Register")
	public void clickOnRegister() {
		eleUtil.clickWhenReady(registerLink, SHORT_DEFAULT_TIME);
	}

	@Step("Navigate to Login")
	public String clickOnLogin() {
		eleUtil.clickWhenReady(loginLink, SHORT_DEFAULT_TIME);
		return eleUtil.waitForTitleContains(LOGIN_PAGE_TITLE, SHORT_DEFAULT_TIME);
	}

	@Step("Fetch Homepage Title")
	public String getPageTitle() {
		String pageTitle = eleUtil.waitForTitleContains(LOGIN_PAGE_TITLE, SHORT_DEFAULT_TIME);
		System.out.println("Page Title is "+pageTitle);
		return pageTitle;
	}
	
	
	@Step("Fetch Homepage Url")
	public String getPageUrl() {
		String pageUrl = eleUtil.waitForUrlContains(LOGIN_PAGE_FRACATION_URL, SHORT_DEFAULT_TIME);
		System.out.println("Page Title is "+pageUrl);
		return pageUrl;
	}
	
	@Step("Login to Application")
	public AccountPage loginApplication(String username, String pwd) {
		eleUtil.sendKeysWithWait(email, SHORT_DEFAULT_TIME, username);
		eleUtil.sendKeys(password, pwd);
		eleUtil.doClick(loginButton);
		return new AccountPage(driver);
		}
	
	@Step("Logout of Application")
	public Boolean logoutApplication() {
		eleUtil.clickWhenReady(logoutLink, SHORT_DEFAULT_TIME);
		return eleUtil.isElementDisplayed(loginLink, SHORT_DEFAULT_TIME);
	}

	
	@Step("Navigate to Register")
	public RegistrationPage navigateToRegistration() {
		eleUtil.clickWhenReady(registerLink, MEDIUM_DEFAULT_TIME);
		return new RegistrationPage(driver);
	}
}
