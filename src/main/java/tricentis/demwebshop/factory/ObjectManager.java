package tricentis.demwebshop.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class ObjectManager {
	
	public Properties prop;
	
	public ObjectManager(Properties prop) {
		this.prop = prop;
	}
	
	
	public ChromeOptions chromeOptions() {
		ChromeOptions co = new ChromeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("*********Running in Headless Mode*********");
			co.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("*********Running in Incognito Mode*********");
			co.addArguments("--incognito");
		}
		//Selenium Grid Capabilities
		
		  if(Boolean.parseBoolean(prop.getProperty("remote"))) {
		  co.addArguments("--incognito"); co.addArguments("--no-sandbox");
		  co.addArguments("--disable-dev-shm-usage"); co.addArguments("--disable-gpu");
		  co.addArguments("--remote-allow-origins=*"); }
		 
		/*
		 * if (Boolean.parseBoolean(prop.getProperty("remote"))) {
		 * co.setCapability("browserName", "chrome");
		 * co.setBrowserVersion(prop.getProperty("browserversion").trim());
		 * 
		 * Map<String, Object> selenoidOptions = new HashMap<>();
		 * selenoidOptions.put("screenResolution", "1280x1024x24");
		 * selenoidOptions.put("enableVNC", true); selenoidOptions.put("name",
		 * prop.getProperty("testname")); co.setCapability("selenoid:options",
		 * selenoidOptions);
		 * 
		 * }
		 */
		return co;
	}
	
	public FirefoxOptions firefoxOptions() {
		FirefoxOptions fo = new FirefoxOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("*********Running in Headless Mode*********");
			fo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("*********Running in Incognito Mode*********");
			fo.addArguments("-private");
		}
		//Firefox Selenium Grid Capabilities
		
		  if(Boolean.parseBoolean(prop.getProperty("remote"))) {
		  fo.addArguments("--no-sandbox"); fo.addArguments("--disable-dev-shm-usage");
		  }
		 
			/*
			 * if (Boolean.parseBoolean(prop.getProperty("remote"))) {
			 * fo.setCapability("browserName", "firefox");
			 * fo.setBrowserVersion(prop.getProperty("browserversion").trim());
			 * 
			 * Map<String, Object> selenoidOptions = new HashMap<>();
			 * selenoidOptions.put("screenResolution", "1280x1024x24");
			 * selenoidOptions.put("enableVNC", true); selenoidOptions.put("name",
			 * prop.getProperty("testname")); fo.setCapability("selenoid:options",
			 * selenoidOptions);
			 * 
			 * }
			 */
		return fo;
	}
	
	
	public EdgeOptions edgeOptions() {
		EdgeOptions eo = new EdgeOptions();
		if(Boolean.parseBoolean(prop.getProperty("headless"))) {
			System.out.println("*********Running in Headless Mode*********");
			eo.addArguments("--headless");
		}
		if(Boolean.parseBoolean(prop.getProperty("incognito"))) {
			System.out.println("*********Running in Incognito Mode*********");
			eo.addArguments("--inprivate");
		}
		
		if(Boolean.parseBoolean(prop.getProperty("remote"))) {
			eo.addArguments("--inprivate");
		    eo.addArguments("--no-sandbox");
		    eo.addArguments("--disable-dev-shm-usage");
		}
		return eo;
	}
	
	

}
