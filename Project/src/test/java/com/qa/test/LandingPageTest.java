package com.qa.test;

import com.qa.base.BaseTest;
import com.qa.pages.LandingPage;
import com.qa.pages.LoginPage;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

@Listeners({com.qa.utils.customListener.class})
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
//		WebDriverWait wait =new WebDriverWait(driver, 5);
//		//wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@id ='nav-logo']")));
        String title = landingpage.Get_Title();
        Assert.assertEquals(title,"Amazon.com: Online Shopping for Electronics, Apparel, Computers, Books, DVDs & more");
        landingpage.Click_LoginLink();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        landingpage.enterEmailId();


    }

    @Test()
    public void test1() {

        landingpage.enterPasssword();
        landingpage.signIn();
        landingpage.selectRegion();
        landingpage.fetchItem();
        landingpage.getItemDetails();
        landingpage.addToCart();
        landingpage.getCartDetails();



    }


    @AfterMethod()
    public void teardown() {
        landingpage.signOut();
        driver.quit();

    }

}
