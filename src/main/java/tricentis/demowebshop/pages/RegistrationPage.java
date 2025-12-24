package tricentis.demowebshop.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import tricentis.demowebshop.utils.ElementsUtil;
import tricentis.demowebshop.utils.StringUtils;

import static tricentis.demowebshopAutomation.constants.AppConstants.*;

import java.util.HashMap;
import java.util.Map;

public class RegistrationPage {
	
	private WebDriver driver;
	private ElementsUtil eUtil;

	public RegistrationPage(WebDriver driver) {
		this.driver = driver;
		eUtil = new ElementsUtil(driver);
	}
	
	private final By genderMaleRadio = By.id("gender-male");
	private final By genderFemaleRadio = By.id("gender-female");
	private final By firstName = By.id("FirstName");
	private final By lastName = By.id("LastName");
	private final By email = By.id("Email");
	private final By password = By.id("Password");
	private final By confirmPassword = By.id("ConfirmPassword");
	private final By registerButton = By.cssSelector("input[name='register-button']");
	private final By registrationSuccessMsg = By.xpath("//div[@class='result']");
	private final By accountInfoLink = By.xpath("//div[@class='header-links']//a[@class='account']");
	
	
	
	public Map<String, String> doUserRegistration(String gender, String firstName, String lastName,String password) {
		Map<String, String> map = new HashMap<String, String>();
		eUtil.waitForElementVisible(genderMaleRadio, SHORT_DEFAULT_TIME);
		if(gender.equalsIgnoreCase("male")) {
			eUtil.doClick(genderMaleRadio);
		}
		else if(gender.equalsIgnoreCase("female")){
			eUtil.doClick(genderFemaleRadio);
		}
		
		eUtil.sendKeys(this.firstName, firstName);
		eUtil.sendKeys(this.lastName, lastName);
		eUtil.sendKeys(this.email, StringUtils.generateRandomEmail());
		eUtil.sendKeys(this.password, password);
		eUtil.sendKeys(this.confirmPassword, password);
		eUtil.doClick(registerButton);
		String successMsg = eUtil.waitForElementVisible(registrationSuccessMsg, MEDIUM_DEFAULT_TIME).getText().trim();
		String accountName = eUtil.getElementText(accountInfoLink).trim();
		map.put("successMsg", successMsg);
		map.put("accountName", accountName);
	    System.out.println(successMsg + " : for "+accountName);
		return map;
		
	}
	
	
}
