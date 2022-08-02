package tests;

import static org.testng.Assert.assertTrue;

import java.time.Duration;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import utils.BaseTest;

public class LoginWithTestNgParameters extends BaseTest {
	
	@Parameters({"user","pass"})
	@Test
	public void loginWithParameters(String user, String pass) throws InterruptedException {
		page.navMenu.navigateTo(page.navMenu.loginLink);
		page.loginPage.loginInApp(user, pass);
		
		assertTrue(page.loginPage.succesLoginMessage.isDisplayed());
		page.loginPage.logoutFromApp();

	}

}
