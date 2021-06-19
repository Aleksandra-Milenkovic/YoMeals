package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NotificationSystemPage extends BasicPage {

	public NotificationSystemPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js,waiter);
		this.waiter= new WebDriverWait(driver, 30, 300);
		
	}
	
	public WebElement getMessageElement () {
		return driver.findElement(By.xpath
				("//*[contains(@class, 'alert--success') or contains(@class, 'alert--danger')][contains(@style,'display: block')]"));
	}

	public String getMessageContent() {
		return this.getMessageElement().getText();
	}
	
	public Boolean waitForTheMessageToDisappear() {
		return this.waiter.until(ExpectedConditions.attributeToBe(By.xpath("//*[contains(@class, 'system_message')]"), "style",  "display: none;"));
	}
}
