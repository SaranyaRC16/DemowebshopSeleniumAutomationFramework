package tricentis.demowebshopAutomation.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.chaintest.plugins.ChainTestListener;

import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Owner;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import tricentis.demowebshopAutomation.BaseTest.BaseTest;
import static tricentis.demowebshopAutomation.constants.AppConstants.*;

import java.util.List;
@Feature(value = "Login Feature")
@Epic(value="Account Login & HomePahe verification")
@Story(value = "Validate Account Page functionality")
public class AccountPageTest extends BaseTest{
	
	@BeforeClass
	public void loginApplication() {
		homePage.clickOnLogin();
		acctPage = homePage.loginApplication(prop.getProperty("username"), prop.getProperty("password"));
	}
	
	@Description("Verify Account Name in homepage")
	@Owner("Sarah")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void verifyAccountInfo() {
		String acctInfo = acctPage.getAccountInfo();
		ChainTestListener.log("Account Name is displayed as "+acctInfo);
		Assert.assertEquals(acctInfo, prop.getProperty("username"));
		
	}
	
	@Description("Verify Category details in homepage")
	@Owner("Sarah")
	@Severity(SeverityLevel.NORMAL)
	@Test
	public void verifyCategoryDetailsTest() {
		List<String> expectedCategoryDetails = acctPage.getCategoryDetails();
		ChainTestListener.log("Category Details Displayed are "+expectedCategoryDetails);
		Assert.assertEquals(expectedCategoryDetails, ACTUAL_CATEGORY_DETAILS);
	}

}
