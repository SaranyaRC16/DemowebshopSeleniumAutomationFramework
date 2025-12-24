package tricentis.demowebshopAutomation.tests;

import java.util.Map;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import tricentis.demowebshop.utils.CSVUtil;
import tricentis.demowebshop.utils.ExcelUtils;
import tricentis.demowebshopAutomation.BaseTest.BaseTest;
import static tricentis.demowebshopAutomation.constants.AppConstants.*;

public class RegistrationPageTest extends BaseTest {

	@BeforeMethod
	public void registrationSetup() {
		registrationPage = homePage.navigateToRegistration();
	}

	@DataProvider
	public Object[][] getUserRegisterData() {
		return new Object[][] { { "male", "Test", "Automation", "testauto@01" },
				{ "male", "Sarah", "chan", "sarachan@01" } };
	}

	@DataProvider
	public Object[][] getRegisterData() {
		Object[][] data = ExcelUtils.getTestDataFromExcel(REGISTER_DATA_SHEET);
		return data;
	}

	@DataProvider
	public Object[][] getResgisterDatCSV() {
		Object[][] data = CSVUtil.readDataFromCSV("Register");
		return data;
	}

	@Test(dataProvider = "getResgisterDatCSV")
	public void userRegistrationVerificationTest(String gender, String firstName, String lastName, String password) {
		Map<String, String> registrationDetails = registrationPage.doUserRegistration(gender, firstName, lastName,
				password);
		Assert.assertEquals(registrationDetails.get("successMsg"), "Your registration completed");
		Assert.assertTrue(registrationDetails.get("accountName").contains("testautomation"));
		homePage.logoutApplication();
	}

}
