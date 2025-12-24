package tricentis.demowebshop.utils;

import java.time.Duration;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.chaintest.plugins.ChainTestListener;

import io.qameta.allure.Step;

public class ElementsUtil {
	public WebDriver driver;
	private JavascriptUtils js;

	public ElementsUtil(WebDriver driver) {
		this.driver = driver;
		js = new JavascriptUtils(driver);

	}

	public void highlightElement(WebElement element) {
		js.flash(element);
	}

	@Step("Locator used is: {0}")
	public WebElement goToElement(By locator) {
		ChainTestListener.log("Locator used is "+locator.toString());
		WebElement element = driver.findElement(locator);
		highlightElement(element);
		return element;
	}

	@Step("Locator used is {0} with Timeout value {1}")
	public WebElement getElementWithWait(By locator, int timeout) {
		WebElement element = waitForElementVisible(locator, timeout);
		highlightElement(element);
		return element;
	}

	@Step("Following value {1} is sent to the Locator: {0}")
	public void sendKeys(By locator, String value) {
		goToElement(locator).sendKeys(value);
	}

	@Step("Getting Text from Locator: {0}")
	public String getElementText(By locator) {
		return goToElement(locator).getText();
	}
	
	public String getElementTextWithWait(By locator, int timeout) {
		return waitForElementVisible(locator,timeout).getText();
	}

	public void doClick(By locator) {
		goToElement(locator).click();
	}

	public void sendKeysClear(By locator) {
		WebElement element = goToElement(locator);
		doClick(locator);
		element.sendKeys(Keys.CONTROL + "a");
		element.sendKeys(Keys.DELETE);
	}

	public String getDOMAttributeWithWait(By locator, int timeout, String attributeName) {
		WebElement ele = waitForElementVisible(locator, timeout);
		String attributeValue = ele.getDomAttribute(attributeName);
		System.out.println(attributeName + " has the value as " + attributeValue);
		return attributeValue;
	}

	public String getDOMAttribute(By locator, String attributeName) {
		String attributeValue = goToElement(locator).getDomAttribute(attributeName);
		System.out.println(attributeName + " has the value as " + attributeValue);
		return attributeValue;
	}

	public String getDOMPropertyithWait(By locator, int timeout, String propertyName) {
		WebElement ele = waitForElementVisible(locator, timeout);
		String propertyValue = ele.getDomProperty(propertyName);
		System.out.println(propertyName + " has the value as " + propertyValue);
		return propertyValue;
	}

	public String getDOMProperty(By locator, String propertyName) {
		String propertyValue = goToElement(locator).getDomProperty(propertyName);
		System.out.println(propertyName + " has the value as " + propertyValue);
		return propertyValue;
	}

	public List<WebElement> getElementsList(By locator) {
		return driver.findElements(locator);
	}

	public Boolean isElementDisplayed(By locator, int timeout) {
		WebElement ele = waitForElementVisible(locator, timeout);
		try {
			ele.isDisplayed();
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	public Boolean selectDropDownByIndex(By locator, int index) {
		Select select = new Select(goToElement(locator));
		try {
			select.selectByIndex(index);
			return true;
		} catch (Exception e) {
			System.out.println(index + " given is not available in the drop down");
			return false;
		}

	}

	public Boolean selectDropDownByVisibleText(By locator, String value) {
		Select select = new Select(goToElement(locator));
		try {
			select.selectByVisibleText(value);
			return true;
		} catch (Exception e) {
			System.out.println(value + " given is not available in the drop down");
			return false;
		}
	}

	public Boolean selectDropDownByValue(By locator, String value) {
		Select select = new Select(goToElement(locator));
		try {
			select.selectByValue(value);
			return true;
		} catch (Exception e) {
			System.out.println(value + " given is not available in the drop down");
			return false;
		}

	}

	/************ Wait Utils *************/
	/**
	 * An method to check whether an element is present on the DOM of a page It does
	 * not necessarily mean that webelement is visible
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement waitForElementPresence(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.presenceOfElementLocated(locator));
		highlightElement(element);
		return element;
	}

	/**
	 * An method to check whether an element is present on the DOM of a page
	 * Visibility means not only element present but also has a width and height
	 * that is greater than 0
	 * 
	 * @param locator
	 * @param timeout
	 * @return
	 */
	public WebElement waitForElementVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		highlightElement(element);
		return element;
	}

	public void clickWithWait(By locator, int timeout) {
		waitForElementVisible(locator, timeout).click();
	}

	public void sendKeysWithWait(By locator, int timeout, CharSequence... value) {
		waitForElementVisible(locator, timeout).sendKeys(value);
	}

	public Alert waitForAlert(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.alertIsPresent());
	}

	public void acceptAlert(int timeout) {
		waitForAlert(timeout).accept();
	}

	public void dismissAlert(int timeout) {
		waitForAlert(timeout).dismiss();

	}

	public String getTextFromAlert(int timeout) {
		String alertText = waitForAlert(timeout).getText();
		System.out.println("Alert text is " + alertText);
		return alertText;

	}

	public void sendKeysAlert(int timeout, String promptValue) {
		waitForAlert(timeout).sendKeys(promptValue);

	}

//wait for Title

	public String waitForTitleContains(String fractionTitle, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.titleContains(fractionTitle));
			return driver.getTitle();
		} catch (TimeoutException e) {
			return null;
		}
	}

	public String waitForTitleIs(String Title, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.titleIs(Title));
			return driver.getTitle();
		} catch (TimeoutException e) {
			return null;
		}
	}

	public String waitForUrlContains(String fractionUrl, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.urlContains(fractionUrl));
			return driver.getCurrentUrl();
		} catch (TimeoutException e) {
			return null;
		}
	}

	public String waitForUrlIs(String Url, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			wait.until(ExpectedConditions.urlToBe(Url));
			return driver.getCurrentUrl();
		} catch (TimeoutException e) {
			return null;
		}
	}

	public void waitForFrameAndSwitchToIt(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
	}

	public void waitForFrameAndSwitchToIt(String nameOrID, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(nameOrID));
	}

	public void waitForFrameAndSwitchToIt(int index, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
	}

	public void waitForFrameAndSwitchToIt(WebElement element, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(element));
	}

	// WaitForWindows
	public Boolean waitForWindow(int expectedNumberOfWindows, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			return wait.until(ExpectedConditions.numberOfWindowsToBe(expectedNumberOfWindows));
		} catch (Exception e) {
			return false;
		}
	}

	public void clickWhenReady(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		WebElement element = wait.until(ExpectedConditions.elementToBeClickable(locator));
		highlightElement(element);
		element.click();
		
	}

	public List<WebElement> waitForAllElementsPresent(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		return wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));

	}

	public List<WebElement> waitForAllElementsVisible(By locator, int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		try {
			return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
		} catch (TimeoutException e) {
			return Collections.emptyList();
		}

	}

	public WebElement waitForElementWithFluent(By locator, int timeout, int pollInterval) {
		Wait<WebDriver> wait = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(timeout))
				.pollingEvery(Duration.ofSeconds(pollInterval)).ignoring(NoSuchElementException.class)
				.ignoring(StaleElementReferenceException.class).withMessage("====ELEMENT NOT FOUND");

		WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
		highlightElement(element);
		return element;
	}

	public Boolean waitUntilPageLoading(int timeout) {
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeout));
		String flag = wait.until(ExpectedConditions.jsReturnsValue("return document.readyState=='complete'"))
				.toString();
		return Boolean.parseBoolean(flag);

	}
}
