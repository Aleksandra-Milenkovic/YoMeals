package Tests;

import org.testng.annotations.Test;

public class DemoTest extends BasicTest{

	@Test
	public void Demotest () throws InterruptedException {
		locationPopUpPage.getCloseButton().click();
		Thread.sleep(1000);
		driver.navigate().to(this.baseURL+ "/guest-user/login-form");
				loginPage.userLogIn("customer@dummyid.com", "12345678a");
				Thread.sleep(1000);
			notificationSystemPage.getMessageElement();
			notificationSystemPage.getMessageContent();
			notificationSystemPage.waitForTheMessageToDisappear();
	}
}
