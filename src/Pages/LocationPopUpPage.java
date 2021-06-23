package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LocationPopUpPage extends BasicPage {

	public LocationPopUpPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	}

	
	public WebElement getSelectLocation() {
		return driver.findElement(By.xpath("//div[@class='location-selector']/a"));
	}

	public WebElement getCloseButton() {
		return driver.findElement(By.className("close-btn-white"));

	}

	public WebElement getKeyword() {
		return driver.findElement(By.xpath("//*[@id='locality_keyword']"));
	}

	public WebElement getLocationItem(String locationName) {
		return driver.findElement(By.xpath("//li/a[contains(text(), '" + locationName + "')]/.."));
	}

	public WebElement getLocationInput() {
		return driver.findElement(By.xpath("//*[@id='location_id']"));
	}

	public WebElement getSubmit() {
		return driver.findElement(By.xpath("//*[@name='btn_submit']"));
	}

	
	public void locationPopUp() {
		this.getSelectLocation().click();

	}

	
	public void setLocation(String locationName) {

		this.getKeyword().click();
		String location = this.getLocationItem(locationName).getAttribute("data-value");
		this.js.executeScript("arguments[0].value=arguments[1]", this.getLocationInput(), location);
		this.js.executeScript("arguments[0].click()", this.getSubmit());
		this.getCloseButton().click();

	}
}
