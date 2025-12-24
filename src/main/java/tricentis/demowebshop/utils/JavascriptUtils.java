package tricentis.demowebshop.utils;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class JavascriptUtils {

	public WebDriver driver;
	public JavascriptExecutor js;
	
	public JavascriptUtils(WebDriver driver) {
		this.driver = driver;
		js = (JavascriptExecutor)driver;
	}
	
	public void flash(WebElement element) {
		String bgColor = element.getCssValue("backgroundColor");
		for (int i=0;i<7;i++) {
			changeColor("rgb(0,200,0)",element);
			changeColor(bgColor, element);
		}
	}
	
	public void changeColor(String color, WebElement element) {
		js.executeScript("arguments[0].style.backgroundColor='"+color+"'", element);
		
		
	}
}
