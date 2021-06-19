package Tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Pages.LocationPopUpPage;
import Pages.LoginPage;
import Pages.NotificationSystemPage;

public abstract class BasicTest {

	protected WebDriver driver;
	protected LocationPopUpPage locationPopUpPage;
	protected String baseURL = "http://demo.yo-meals.com";
	protected JavascriptExecutor js;
	protected WebDriverWait waiter;
	protected LoginPage loginPage;
	protected NotificationSystemPage notificationSystemPage;
	
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(baseURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		

		locationPopUpPage = new LocationPopUpPage(driver, js, waiter);
		loginPage= new LoginPage(driver, js, waiter);
		notificationSystemPage= new NotificationSystemPage(driver, js, waiter);
	}

	@AfterMethod
	public void cleanup() {
		this.driver.quit();
	}

}
