package Tests;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ProfileTest extends BasicTest {

	@Test
	public void editProfileTest() throws InterruptedException {
		
		driver.navigate().to(baseURL + "/guest-user/login-form");
		Thread.sleep(1000);
		locationPopUpPage.getCloseButton().click();
		loginPage.userLogIn(email, password);

		Assert.assertTrue(notificationSystemPage.getMessageContent().contains("Login Successfull"),
				"The Loging Sucessful message is not visible.");
		
		Thread.sleep(5000);
		driver.navigate().to(baseURL + "/member/profile");
		
		profilePage.setBasicinfo("Aleksandra", "Milenkovic", "123", "123", "654", "United Kingdom", "London", "Leyton");
		Assert.assertTrue(notificationSystemPage.getMessageContent().contains("Setup Successful"),
				"The Setup Successfull message is not visible.");
		
		aughtPage.LogOut();
		Assert.assertTrue(notificationSystemPage.getMessageContent().contains("Logout Successfull"),
				"The Logout Successfull message is not visible.");
	}
	
	@Test
	public void changeProfileImageTest() throws InterruptedException, IOException {
		
		driver.navigate().to(baseURL + "/guest-user/login-form");
		Thread.sleep(1000);
		locationPopUpPage.getCloseButton().click();
		loginPage.userLogIn(email, password);

		Assert.assertTrue(notificationSystemPage.getMessageContent().contains("Login Successfull"),
				"The Loging Sucessful message is not visible.");
		Thread.sleep(5000);

		driver.navigate().to(baseURL + "/member/profile");
		
		String imgPath = new File("img/Foto_IT-Bootcamp_.jpg").getCanonicalPath();
		profilePage.changeImage(imgPath);
		
		Assert.assertTrue(notificationSystemPage.getMessageContent().contains("Profile Image Uploaded Successfully"),
				"The Image Uploaded Successfully message is not visible.");
		notificationSystemPage.waitForTheMessageToDisappear();
		profilePage.removeImage();
		
		Assert.assertTrue(notificationSystemPage.getMessageContent().contains("Profile Image Deleted Successfully"),
				"The profile Image Deleted Sucessfully message is not visible.");
		notificationSystemPage.waitForTheMessageToDisappear();
		aughtPage.LogOut();
		Assert.assertTrue(notificationSystemPage.getMessageContent().contains("Logout Successfull!"),
				"The Logout Successful message is not visible.");
		Thread.sleep(1000);
		
		
	}

}
