package Tests;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {

	@Test
	public void editProfileTest() throws InterruptedException {

		driver.navigate().to(baseURL + "/guest-user/login-form");
		Thread.sleep(500);
		locationPopUpPage.getCloseButton().click();
		Thread.sleep(500);

		loginPage.userLogIn(email, password);
		Assert.assertTrue(notificationSystemPage.getMessageContent().contains("Login Successfull"),
				"[ERROR]The 'Login Sucessful' message is not visible.");
		Thread.sleep(500);

		driver.navigate().to(baseURL + "/member/profile");
		Thread.sleep(500);
		profilePage.setBasicinfo("Aleksandra", "Milenkovic", "123", "123", "654", "United Kingdom", "London", "Leyton");
		Assert.assertTrue(notificationSystemPage.getMessageContent().contains("Setup Successful"),
				"[ERROR}The 'Setup Successfull' message is not visible.");
		Thread.sleep(500);

		aughtPage.LogOut();
		Assert.assertTrue(notificationSystemPage.getMessageContent().contains("Logout Successfull"),
				"[ERROR]The 'Logout Successfull' message is not visible.");
	}

	@Test
	public void changeProfileImageTest() throws InterruptedException, IOException {

		String imgPath = new File("img/Foto_IT-Bootcamp_.jpg").getCanonicalPath();

		driver.navigate().to(baseURL + "/guest-user/login-form");
		Thread.sleep(500);
		locationPopUpPage.getCloseButton().click();
		Thread.sleep(500);

		loginPage.userLogIn(email, password);
		Assert.assertTrue(notificationSystemPage.getMessageContent().contains("Login Successfull"),
				"[ERROR]The 'Login Sucessful' message is not visible.");
		Thread.sleep(500);

		driver.navigate().to(baseURL + "/member/profile");
		Thread.sleep(500);
		profilePage.changeImage(imgPath);
		Assert.assertTrue(notificationSystemPage.getMessageContent().contains("Profile Image Uploaded Successfully"),
				"[ERROR}The 'Image Uploaded Successfull'y message is not visible.");
		Thread.sleep(500);
		notificationSystemPage.waitForTheMessageToDisappear();

		profilePage.removeImage();
		Assert.assertTrue(notificationSystemPage.getMessageContent().contains("Profile Image Deleted Successfully"),
				"[ERROR]The 'Profile Image Deleted Sucessfully' message is not visible.");
		notificationSystemPage.waitForTheMessageToDisappear();

		aughtPage.LogOut();
		Assert.assertTrue(notificationSystemPage.getMessageContent().contains("Logout Successfull!"),
				"[ERROR]The 'Logout Successfull' message is not visible.");
		Thread.sleep(500);

	}

}
