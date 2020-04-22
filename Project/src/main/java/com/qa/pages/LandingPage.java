package com.qa.pages;

	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.support.FindBy;
	import org.openqa.selenium.support.PageFactory;

	import com.qa.base.BaseTest;

	public class LandingPage extends BaseTest {

		// Using @findBy annotations from Pagefactory

		@FindBy(xpath = "//div[@id ='nav-logo']")
		WebElement logo;
		@FindBy(xpath = "//a[@id='nav-link-accountList']")
		WebElement LoginLink;
		// title-Amazon Sign-In
		@FindBy(xpath = "//a[@id='ap_email']")
		WebElement EmailTextBox;
		@FindBy(xpath = "//a[@id='password']")
		WebElement PasswordTextBox;

		// Initializing Page Factory
		public LandingPage() {
			PageFactory.initElements(driver, this);

		}
		// Defining ACTIONS

		public Boolean checkimage() {
			return logo.isDisplayed();
		}

		public void Click_LoginLink() {
			LoginLink.click();
		}

		public LoginPage Login_Btn_Click() {
			return new LoginPage();
		}

		public void enter_Emailid() {

			EmailTextBox.sendKeys(prop.getProperty("username"));
		}

		public void enter_password() {
			PasswordTextBox.sendKeys(prop.getProperty("password"));
		}

		public String Get_Title() {
			return driver.getTitle();
		}

	}

