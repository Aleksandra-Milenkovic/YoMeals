package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AughtPage extends BasicPage {

	public AughtPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);

	}

	public WebElement getAccountButton() {

		return driver.findElement(By.className("user-trigger-js"));
	}

	public WebElement getLogOutBtn() {

		return driver.findElement(By.linkText("Logout"));
	}

	public void LogOut() throws InterruptedException {
		this.getAccountButton().click();
		Thread.sleep(500);
		this.getLogOutBtn().click();
	}
}
