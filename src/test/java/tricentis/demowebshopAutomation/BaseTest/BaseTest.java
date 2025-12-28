package tricentis.demowebshopAutomation.BaseTest;

import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import com.aventstack.chaintest.plugins.ChainTestListener;

import tricentis.demowebshop.pages.AccountPage;
import tricentis.demowebshop.pages.HomePage;
import tricentis.demowebshop.pages.ProductInfoPage;
import tricentis.demowebshop.pages.ProductSearchResultPage;
import tricentis.demowebshop.pages.RegistrationPage;
import tricentis.demwebshop.factory.DriverFactory;

//@Listeners(ChainTestListener.class)
public class BaseTest {

	WebDriver driver;
	DriverFactory df;
	protected HomePage homePage;
	protected Properties prop;
	protected AccountPage acctPage;
	protected ProductSearchResultPage searchPage;
	protected ProductInfoPage productInfoPage;
	protected RegistrationPage registrationPage;
	
	private static final Logger log = LogManager.getLogger(BaseTest.class);
	
    @Parameters({"browser","browserversion","testname"})
    @BeforeTest
	public void setup(@Optional String browserName, @Optional String browserVersion, @Optional String testName) {
		df = new DriverFactory();
		prop = df.initProp();
		log.info("Properties are" +prop);
        log.info("Browser launched is " +browserName);
		ChainTestListener.log("Borwser Launched is "+browserName);	
		  if(!(browserName==null)) {
			  prop.setProperty("browser", browserName);
			  prop.setProperty("browserversion", browserVersion);
			  prop.setProperty("testname", testName);
		  }
		driver = df.launchApplication(prop);
		homePage = new HomePage(driver);

	}

    @AfterMethod
    public void attachScreenshot(ITestResult result) {
    	log.info("**************STARTING TEST*****************"+result.getName());
    	if(!(result.isSuccess())) {
    		DriverFactory.getScreenshotFile();
    		ChainTestListener.embed(DriverFactory.getScreenshotFile(), "image/png");
    	}
    	
    }
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
