package tricentis.demwebshop.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import tricentis.demowebshop.exceptions.BrowserExceptions;

public class DriverFactory {

	static WebDriver driver;
	Properties prop;
	ObjectManager objManager;
	public static String highlight;
	
	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

	private static final Logger log = LogManager.getLogger(DriverFactory.class);
	
	public void initDriver(String browser) {
		
		log.info("Browser name passed is" +browser);
		//System.out.println("Browser name is " + browser);
		objManager = new ObjectManager(prop);
		highlight = prop.getProperty("highlight");
		Boolean remote = Boolean.parseBoolean(prop.getProperty("remote"));

		switch (browser.toLowerCase().trim()) {
		case ("chrome"):
			if(remote) {
				initRemoteDriver(browser);
			}
			else {
			tlDriver.set(new ChromeDriver(objManager.chromeOptions()));
			}
			break;
		case ("firefox"):
			if(remote) {
				initRemoteDriver(browser);
			}
			else {
				tlDriver.set(new FirefoxDriver(objManager.firefoxOptions()));
			}			
			break;
		case ("edge"):
			if(remote) {
				initRemoteDriver(browser);
			}
			else {
				tlDriver.set(new EdgeDriver(objManager.edgeOptions()));
			}			
			break;
		case ("safari"):
			if(remote) {
				throw new BrowserExceptions("=====INVALID BROWSER NOT SUPPORTED WITH SELENIUM GRID====="+browser);
			}
			else {
				tlDriver.set(new SafariDriver());
			}			
			break;
		default:
			log.error("INVALID BROWSER NAME");
			throw new BrowserExceptions("=====INVALID BROWSER=====");

		}
	}

	public static WebDriver getDriver() {
		return tlDriver.get();
	}
	public WebDriver launchApplication(Properties prop) {
		String browser = prop.getProperty("browser");
		String url = prop.getProperty("url");
		initDriver(browser);
		getDriver().get(url);
		getDriver().manage().window().maximize();
		getDriver().manage().deleteAllCookies();
		return getDriver();
	}

	/**
	 * env variable with possible values: qa, dev, stage, prod maven command: mvn
	 * clean install -Denv = "qa"
	 * 
	 * @return
	 */
	public Properties initProp() {
		prop = new Properties();
		FileInputStream ip = null;
		String envName = System.getProperty("env");
		try {
			if (envName == null) {
				log.warn("Environment Name is null. Hence running with qa configuration");
				//System.out.println("Environment Name is null. Hence running with qa configuration");
				ip = new FileInputStream(".\\src\\test\\resources\\config\\qaConfig.properties");
			}
			else {
			switch (envName.toLowerCase().trim()) {
			case "qa":
				ip = new FileInputStream(".\\src\\test\\resources\\config\\qaConfig.properties");
				break;
			case "dev":
				ip = new FileInputStream(".\\src\\test\\resources\\config\\devConfig.properties");
				break;
			case "stage":
				ip = new FileInputStream(".\\src\\test\\resources\\config\\stageConfig.properties");
				break;
			case "prod":
				ip = new FileInputStream(".\\src\\test\\resources\\config\\prodConfig.properties");
				break;
			}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return prop;
	}
	
	private void initRemoteDriver(String browserName) {
		
		switch(browserName) {
		case "chrome":
		try {
				tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("hubUrl")),objManager.chromeOptions()));
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		break;
		case "firefox":
			try {
					tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("hubUrl")),objManager.firefoxOptions()));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
		case "edge":
			try {
					tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("hubUrl")),objManager.edgeOptions()));
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			break;
		default:
			throw new BrowserExceptions("======INVALID BROWSER====="+browserName);
			
		}
		
	}
	
 /** 
  * take Screenshot
  */
	public static String takeScreenshot() {
		File srcFile = null;
		System.out.println("screenshot method called");
		try {
			srcFile = ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.FILE);
		} catch (WebDriverException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String Filepath = System.getProperty("user.dir")+File.separator+"screenshot";
		String path = Filepath+File.separator+"screenshot"+"_"+System.currentTimeMillis()+".png";
		File destination = new File(path);
		
		try {
			FileHandler.copy(srcFile, destination);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return path;
	}
	
	
	public static byte[] getScreenshotFile() {
		return ((TakesScreenshot)getDriver()).getScreenshotAs(OutputType.BYTES);
	}
}
