package tricentis.demowebshopAutomation.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import tricentis.demowebshopAutomation.BaseTest.BaseTest;

public class ProductInfoPageTest extends BaseTest{
	
	@BeforeClass
	public void loginToApplication() {
		homePage.clickOnLogin();
		acctPage = homePage.loginApplication(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Test
	public void verifyProductTitleTest() {
		searchPage = acctPage.searchProduct("computer");
		productInfoPage = searchPage.selectProduct("Build your own cheap computer");
		String actualProductTitle = productInfoPage.getProductTitle();
		Assert.assertEquals(actualProductTitle, "Build your own cheap computer");
		
	}
	
	@Test
	public void verifyProductPriceTest() {
		searchPage = acctPage.searchProduct("computer");
		productInfoPage = searchPage.selectProduct("Build your own cheap computer");
		String actualProductTitle = productInfoPage.getProductPrice();
		Assert.assertEquals(actualProductTitle, "800.00");
		
	}
	
	@Test
	public void verifyProductThumbnailsCountTest() {
		searchPage = acctPage.searchProduct("computer");
		productInfoPage = searchPage.selectProduct("Build your own cheap computer");
		int actualProductThumbnailCount = productInfoPage.getProductThumbnailCount();
		Assert.assertEquals(actualProductThumbnailCount, 3);
		
	}

}
