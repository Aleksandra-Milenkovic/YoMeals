package Tests;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import Pages.AughtPage;
import Pages.CartSummaryPage;
import Pages.LocationPopUpPage;
import Pages.LoginPage;
import Pages.MealPage;
import Pages.NotificationSystemPage;
import Pages.ProfilePage;
import org.apache.commons.io.FileUtils;

public abstract class BasicTest {


	protected WebDriver driver;
	protected LocationPopUpPage locationPopUpPage;
	protected String baseURL = "http://demo.yo-meals.com";
	protected String email="customer@dummyid.com";
	protected String password= "12345678a";
	protected JavascriptExecutor js;
	protected WebDriverWait waiter;
	protected LoginPage loginPage;
	protected NotificationSystemPage notificationSystemPage;
	protected ProfilePage profilePage;
	protected AughtPage aughtPage;
	protected MealPage mealPage;
	protected CartSummaryPage cartSummaryPage;
	
	@BeforeMethod
	public void setup() {
		System.setProperty("webdriver.chrome.driver", "driver-lib\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get(baseURL);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		

		js=(JavascriptExecutor)driver;
		waiter= new WebDriverWait(driver, 30,300);
		locationPopUpPage = new LocationPopUpPage(driver, js, waiter);
		loginPage= new LoginPage(driver, js, waiter);
		notificationSystemPage= new NotificationSystemPage(driver, js, waiter);
		profilePage= new ProfilePage(driver, js, waiter);
		aughtPage= new AughtPage(driver, js, waiter);
		mealPage= new MealPage(driver, js, waiter);
		cartSummaryPage= new CartSummaryPage(driver, js, waiter);
	}

	@AfterMethod
	
	
	
	public void takeScreenshot(ITestResult result) throws IOException, InterruptedException {
		
	
		if (ITestResult.FAILURE==result.getStatus())
		{
			
			TakesScreenshot ts=(TakesScreenshot) driver;
			File scr= ts.getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(scr, new File("C:\\Users\\Caka\\Desktop\\YoMeals\\YoMeals\\screenshots\\" + result.getName()+".png"));
			
		}
		Thread.sleep(3000);
		this.driver.quit();
	}

}
