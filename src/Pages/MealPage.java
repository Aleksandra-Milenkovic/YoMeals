package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MealPage extends BasicPage {

	public MealPage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
		
	}
	
	public WebElement getMealsBtn() {
		return driver.findElement(By.linkText("Meals"));
	}
	
	public WebElement getAddToCartBtn() {
		return driver.findElement(By.partialLinkText("Add To Cart"));
	}
	public WebElement getQuantityBtn() {
	 return	driver.findElement(By.name("product_qty"));
	}
	public void addMealToCart(int quantity) throws InterruptedException {
		this.getQuantityBtn().sendKeys(Keys.DELETE);
		Thread.sleep(1000);
		this.getQuantityBtn().sendKeys(String.valueOf(quantity));
		Thread.sleep(500);
		this.getAddToCartBtn().click();
		
	}
	
	public void addToFavorites() {
		driver.findElement(By.xpath("//a[@title='Favorite']")).click();
	}

}
