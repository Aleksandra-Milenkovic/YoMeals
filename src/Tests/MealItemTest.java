package Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.google.common.collect.Table.Cell;

import Pages.CartSummaryPage;

class MealItemTest extends BasicTest {

	//@Test
	public void addMealToCart() throws IOException, InterruptedException {
		
	driver.navigate().to(baseURL+ "/meal/lobster-shrimp-chicken-quesadilla-combo");
	locationPopUpPage.getCloseButton().click();
	
	Thread.sleep(500);
	mealPage.addMealToCart(2);
	

	
	Assert.assertTrue(notificationSystemPage.getMessageContent().contains("The Following Errors Occurred"),
			"The message is not visible.");
	notificationSystemPage.waitForTheMessageToDisappear();
	
	locationPopUpPage.setLocation("City Center - Albany");
	Thread.sleep(1000);
	mealPage.addMealToCart(2);
	Assert.assertTrue(notificationSystemPage.getMessageContent().contains("Meal Added To Cart"),
			"The message is not visible.");

	
	}
	
	//@Test
	public void addMealToFavorite() throws InterruptedException {
		driver.navigate().to(baseURL+ "/meal/lobster-shrimp-chicken-quesadilla-combo");
		locationPopUpPage.getCloseButton().click();
		
		Thread.sleep(500);
		
		mealPage.addToFavorites();
		Assert.assertTrue(notificationSystemPage.getMessageContent().contains("Please login first!"),
				"The message is not visible.");
		Thread.sleep(500);
		
		driver.navigate().to(baseURL + "/guest-user/login-form");
		Thread.sleep(1000);
		
		loginPage.userLogIn(email, password);
		driver.navigate().to(baseURL+ "/meal/lobster-shrimp-chicken-quesadilla-combo");
		
		Thread.sleep(500);
		mealPage.addToFavorites();
		
		
		Assert.assertTrue(notificationSystemPage.getMessageContent().contains("Product has been added to your favorites"),
				"The message is not visible.");
	}
	
	@Test
	public void clearCart() throws InterruptedException, IOException {
		driver.navigate().to(baseURL + "/meals");
		locationPopUpPage.getCloseButton().click();
		Thread.sleep(500);
		
		locationPopUpPage.setLocation("City Center - Albany");
		
		File file = new File("data/Data (3).xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Meals");
		
		for(int i=1; i<6;i++) {
			
			String meal= sheet.getRow(i).getCell(0).getStringCellValue();
		
		
			int quantity=(int)sheet.getRow(i).getCell(1).getNumericCellValue();
			
					
		driver.navigate().to(meal);
		mealPage.addMealToCart(quantity);
		
		SoftAssert sa= new SoftAssert();
		
		sa.assertTrue(notificationSystemPage.getMessageContent().contains("Meal Added To Cart"),
				"The message is not visible.");		}
		
		Thread.sleep(10000);
		
		cartSummaryPage.clearCart();
		Assert.assertTrue(notificationSystemPage.getMessageContent().contains("rtuyertr7utur"),
				"The message is not visible.");
		
		
	}

	
	
}
	

