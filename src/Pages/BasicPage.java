package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public abstract class BasicPage {

	protected WebDriver driver;
	protected JavascriptExecutor js;
	protected WebDriverWait waiter;

	public BasicPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		this.driver = driver;
		this.js = js;
		this.waiter = new WebDriverWait(driver, 30, 300);

	}

	public boolean elementExist(By by) {
		boolean exist = true;
		try {
			driver.findElement(by);
		} catch (Exception e) {
			exist = false;
		}
		return exist;
	}

}
