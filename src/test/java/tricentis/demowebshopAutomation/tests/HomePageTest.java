package tricentis.demowebshopAutomation.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import tricentis.demowebshopAutomation.BaseTest.BaseTest;
import static tricentis.demowebshopAutomation.constants.AppConstants.*;

public class HomePageTest extends BaseTest {

	@Test(priority = 1)
	public void verifyPageTitle() {
		String pageTitle = homePage.getPageTitle();
		Assert.assertEquals(pageTitle, LOGIN_PAGE_TITLE);
	}

	@Test(priority = 2)
	public void verifyPageUrl() {
		String pageTitle = homePage.clickOnLogin();
		Assert.assertEquals(pageTitle, "Demo Web Shop. Login");
		String pageUrl = homePage.getPageUrl();
		Assert.assertTrue(pageUrl.contains(LOGIN_PAGE_FRACATION_URL));

	}

	@Test(priority = 3)
	public void loginVerification() {
		acctPage = homePage.loginApplication(prop.getProperty("username"), prop.getProperty("password"));
		Assert.assertEquals(acctPage.getAccountInfo(),prop.getProperty("username"));
	}

	@Test(priority = 4)
	public void logoutVerification() {
		Boolean loginPageDisplayed = homePage.logoutApplication();
		Assert.assertTrue(loginPageDisplayed);

	}
}
