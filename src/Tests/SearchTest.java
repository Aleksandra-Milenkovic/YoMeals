package Tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class SearchTest extends BasicTest {

	@Test
	public void searchResults() throws InterruptedException, IOException {

		File file = new File("data/Data(1).xlsx");
		FileInputStream fis = new FileInputStream(file);
		XSSFWorkbook workbook = new XSSFWorkbook(fis);
		XSSFSheet sheet = workbook.getSheet("Meal Search Results");
		SoftAssert sa = new SoftAssert();

		driver.navigate().to(baseURL + "/meals");
		Thread.sleep(500);
		locationPopUpPage.setLocation("City Center - Albany");
		Thread.sleep(500);

		for (int i = 1; i < 7; i++) {

			String URL = sheet.getRow(i).getCell(1).getStringCellValue();
			Thread.sleep(1000);

			driver.navigate().to(URL);
			Thread.sleep(500);

			String location = sheet.getRow(i).getCell(0).getStringCellValue();
			Thread.sleep(3000);

			locationPopUpPage.locationPopUp();
			Thread.sleep(500);
			locationPopUpPage.setLocation(location);
			Thread.sleep(1000);

			int NumOfMeals = searchResultPage.getNumberOfResults();
			Thread.sleep(3000);
			int NumOfResults = (int) sheet.getRow(i).getCell(2).getNumericCellValue();

			Thread.sleep(1000);
			sa.assertEquals(NumOfMeals, NumOfResults);

			for (int i1 = 0; i1 < searchResultPage.getNumberOfResults(); i1++) {

				String resultsOnPage = searchResultPage.getMealNames().get(i1);
				Thread.sleep(3000);

				String resultsInTheSheet = sheet.getRow(i).getCell(3 + i1).getStringCellValue();
				Thread.sleep(1000);

				sa.assertTrue(resultsOnPage.contains(resultsInTheSheet), "The Results are not compatible");

			}

			sa.assertAll();

		}
	}
}
