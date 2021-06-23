package Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

class MealItemTest extends BasicTest {

	@Test
	public void addMealToCart() throws IOException, InterruptedException {

		driver.navigate().to(baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		Thread.sleep(500);
		locationPopUpPage.getCloseButton().click();
		Thread.sleep(500);

		mealPage.addMealToCart(2);
		Assert.assertTrue(notificationSystemPage.getMessageContent().contains("The Following Errors Occurred"),
				"[ERROR] No message implying that the location setup is necessary. ");
		notificationSystemPage.waitForTheMessageToDisappear();

		locationPopUpPage.locationPopUp();
		locationPopUpPage.setLocation("City Center - Albany");
		Thread.sleep(500);
		mealPage.addMealToCart(2);
		Assert.assertTrue(notificationSystemPage.getMessageContent().contains("Meal Added To Cart"),
				"[ERROR] The 'Meal Added To Cart' message is not visible.");

	}

	@Test
	public void addMealToFavorite() throws InterruptedException {

		driver.navigate().to(baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		Thread.sleep(500);
		locationPopUpPage.getCloseButton().click();
		Thread.sleep(500);

		mealPage.addToFavorites();
		Assert.assertTrue(notificationSystemPage.getMessageContent().contains("Please login first!"),
				"[ERROR] The message 'Please login first' is not visible.");
		Thread.sleep(500);

		driver.navigate().to(baseURL + "/guest-user/login-form");
		Thread.sleep(500);
		loginPage.userLogIn(email, password);
		Thread.sleep(500);
		driver.navigate().to(baseURL + "/meal/lobster-shrimp-chicken-quesadilla-combo");
		Thread.sleep(500);
		mealPage.addToFavorites();
		Assert.assertTrue(
				notificationSystemPage.getMessageContent().contains("Product has been added to your favorites"),
				"[ERROR]The message 'Product added to favorites' is not visible.");
	}

	@Test
	public void clearCart() throws InterruptedException, IOException {

		File file = new File("data/Data (4).xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Meals");
		SoftAssert sa = new SoftAssert();

		driver.navigate().to(baseURL + "/meals");
		Thread.sleep(2000);
		locationPopUpPage.setLocation("City Center - Albany");
		Thread.sleep(500);

		for (int i = 1; i < 6; i++) {

			String meal = sheet.getRow(i).getCell(0).getStringCellValue();
			Thread.sleep(1000);
			int quantity = (int) sheet.getRow(i).getCell(1).getNumericCellValue();
			Thread.sleep(1000);

			driver.navigate().to(meal);
			Thread.sleep(1000);
			mealPage.addMealToCart(quantity);
			Thread.sleep(1000);

			sa.assertTrue(notificationSystemPage.getMessageContent().contains("Meal Added To Cart"),
					"[ERROR]The message 'Meal added to cart' is not visible.");
			Thread.sleep(500);

		}

		cartSummaryPage.clearCart();
		Assert.assertTrue(
				notificationSystemPage.getMessageContent().contains("All meals removed from Cart successfully"),
				"[ERROR]The message 'Meals successfully removed' is not visible.");
		sa.assertAll();

	}

}
