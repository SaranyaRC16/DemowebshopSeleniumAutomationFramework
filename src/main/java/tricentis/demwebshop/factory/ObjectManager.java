package tricentis.demwebshop.factory;

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
		
		return eo;
	}
	
	

}
