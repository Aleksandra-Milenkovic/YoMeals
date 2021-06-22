package Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ProfilePage extends BasicPage {

	public ProfilePage(WebDriver driver, JavascriptExecutor js, WebDriverWait waiter) {
		super(driver, js, waiter);
	
	}
	
	public WebElement getAccountButton () {
		
		return driver.findElement(By.className("user-trigger-js"));
	}
	
	public WebElement getMyAccount() {
		return driver.findElement(By.linkText("My Account"));
	}

	public WebElement getProfilePage() {
		return driver.findElement(By.partialLinkText("Profile"));
	}
	
	public WebElement getFirstName() {
		return driver.findElement(By.name("user_first_name"));
	}
	
	public WebElement getLastName() {
		return driver.findElement(By.name("user_last_name"));
	}
	
	public WebElement getAddress() {
		return driver.findElement(By.name("user_address"));
	}
	
	public WebElement getPhoneNo() {
		return driver.findElement(By.name("user_phone"));
	}
	
	public WebElement getZipCode() {
		return driver.findElement(By.name("user_zip"));
	}
	
	public Select getCountry() {
		Select countries = new Select(driver.findElement(By.id("user_country_id")));
		return countries;
	}
	
	public Select getState() {
		Select states = new Select(driver.findElement(By.id("user_state_id")));
		return states;
	}
	
	public Select getCity() {
		Select cities= new Select(driver.findElement(By.id("user_city")));
		return cities;
	}
	
	public WebElement getSave() {
		return driver.findElement(By.xpath("//input[@value='Save']"));
				
	}
	

	public WebElement getUploadbtn() {
		return driver.findElement(By.xpath("//a[@title='Uplaod']"));
	}
	public WebElement getinputFile() {
		return driver.findElement(By.xpath("//input[@type='file']"));
	}
	
	public void changeImage(String path) {
		this.js.executeScript("arguments[0].click();", this.getUploadbtn());
		this.getinputFile().sendKeys(path);
	}
	
	public void removeImage() {
		WebElement removeButton= driver.findElement(By.xpath("//a[@title='Remove']"));
		this.js.executeScript("arguments[0].click();", removeButton);
	}
	
	public void setBasicinfo(
			String firstName, 
			String lastName,
			String address, 
			String phoneNo,
			String zipCode,
			String country,
			String state, 
			String city
		) throws InterruptedException {
		this.getFirstName().clear();
		this.getFirstName().sendKeys(firstName);
		Thread.sleep(500);
		this.getLastName().clear();
		this.getLastName().sendKeys(lastName);
		Thread.sleep(500);
		this.getAddress().clear();
		this.getAddress().sendKeys(address);
		Thread.sleep(500);
		this.getPhoneNo().clear();
		this.getPhoneNo().sendKeys(phoneNo);
		Thread.sleep(500);
		this.getZipCode().clear();
		this.getZipCode().sendKeys(zipCode);
		Thread.sleep(500);
		this.getCountry().selectByVisibleText(country);
		Thread.sleep(500);
		this.getState().selectByVisibleText(state);
		Thread.sleep(500);
		this.getCity().selectByVisibleText(city);
		this.getSave().click();
		Thread.sleep(1000);
		
	}
}
