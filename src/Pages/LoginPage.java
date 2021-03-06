package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasicPage{

	public LoginPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
		
	}
	
	
	public WebElement getLoginBtn() {
		return driver.findElement(By.linkText("Login"));
	}

	public WebElement getUserName() {
		return driver.findElement(By.name("username"));
	}
	
	public WebElement getPassword() {
		return driver.findElement(By.name("password"));
	}
	
	public WebElement getRememberMe() {
		return driver.findElement(By.name("remember_me"));
	}
	public WebElement getLoginSubmitBtn() {
		return driver.findElement(By.name("btn_submit"));
	}
	
	public void userLogIn(String email, String password) throws InterruptedException {
		
		
		this.getUserName().clear();
		this.getUserName().sendKeys(email);
		this.getPassword().clear();;
		this.getPassword().sendKeys(password);
		this.getRememberMe().click();
		this.getLoginSubmitBtn().click();
	}
}
