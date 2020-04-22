package com.qa.test;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.qa.base.BaseTest;
import com.qa.pages.LoginPage;
import com.qa.pages.LandingPage;

public class LandingPageTest extends BaseTest {
	LandingPage landingpage;
	 LoginPage homepage;

	 LandingPageTest() {
		super();
	}

	@BeforeMethod()
	public void setup() {
		initialise();
		landingpage = new LandingPage();
		WebDriverWait wait =new WebDriverWait(driver, 5);
		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id ='nav-logo']")));
		String title = landingpage.Get_Title();
		System.out.println(title);
		landingpage.Click_LoginLink();
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		landingpage.enter_Emailid();
		wait.until(ExpectedConditions.visibilityOf((WebElement) By.xpath("//a[@id='password']")));
		
		
}
	
	@Test()
	public void test1() {
	
		landingpage.enter_password();
		
	}
	
	
	@AfterMethod()
	public void teardown(){
		driver.quit();
		
	}

}
