package tricentis.demowebshopAutomation.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import tricentis.demowebshopAutomation.BaseTest.BaseTest;

public class ProductSearchResultsPageTest extends BaseTest{
	
	@BeforeClass
	public void loginToApplication() {
		homePage.clickOnLogin();
		acctPage = homePage.loginApplication(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void verifyProductResultsCountTest() {
		searchPage = acctPage.searchProduct("computer");
		int actualProductResultCount = searchPage.getSearchProductResultsCount();
		Assert.assertEquals(actualProductResultCount, 4);
		
		
	}

}
