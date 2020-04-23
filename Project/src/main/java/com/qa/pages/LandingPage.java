package com.qa.pages;

import com.qa.base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;

import java.time.Duration;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import org.openqa.selenium.WebElement;
import org.testng.asserts.SoftAssert;


import static java.util.concurrent.TimeUnit.SECONDS;

public class LandingPage extends BaseTest {

    private int ramdomNumber;
    private String priceBefore, priceAfter, descBefore, descAfter;
    private String itemLink;

    // Using @findBy annotations from Pagefactory

    @FindBy(xpath = "//div[@id ='nav-logo']")
    WebElement logo;

    @FindBy(xpath = "//a[@id='nav-link-accountList']")
    WebElement LoginLink;
    // title-Amazon Sign-In

    @FindBy(xpath = "//*[@id=\"ap_email\"]")
//		@FindBy(xpath = "//a[@id='ap_email']")
            WebElement EmailTextBox;

    @FindBy(xpath = "//*[@id=\"ap_password\"]")
    WebElement PasswordTextBox;

    @FindBy(xpath = "//*[@id=\"continue\"]")
    WebElement continue_btn;

    @FindBy(xpath = "//*[@id=\"twotabsearchtextbox\"]")
    WebElement search_Text_Box;

    @FindBy(xpath = "//input[@type='submit' and @class='nav-input' and @value='Go']")
    WebElement search_submit;

    @FindBy(xpath = "//*[@id=\"signInSubmit\"]")
    WebElement signin_btn;

    @FindBy(xpath = "//*[@id=\"nav-global-location-slot\"]/span/a")
    WebElement regionSelect;

    @FindBy(xpath = "//*[@id=\"GLUXZipUpdateInput\"]")
    WebElement pincodeText;

    @FindBy(xpath = "//*[@id=\"GLUXZipUpdate\"]/span/input")
    WebElement ApplyBtn;

    //    @FindBy(xpath = "//input[@type='submit' and @id=\"GLUXConfirmClose\"]")
    //    @FindBy(xpath = "//[@class='a-popover-3']//input[@type='submit']")
//    @FindBy(xpath = "//*[@class='a-popover-header']/..//input[@type='submit' & @contains[text()='Done']]")
    @FindBy(xpath = "//*[@class='a-popover-header']/..//button[@class='a-button-text' and contains(@name,'Done')]")
    WebElement regionContinue;

    @FindBy(xpath = "//*[@id='priceblock_saleprice' or @id='priceblock_ourprice' or @id='priceblock_dealprice']")
    WebElement priceTextBefore;

    @FindBy(xpath = "//*[@id=\"add-to-cart-button\"]")
    WebElement addToCartBtn;

    @FindBy(xpath = "//*[@id=\"huc-page-container\"]//*[contains(text(),'Added to Cart')]")
    WebElement addedToCartText;

    @FindBy(xpath = "//*[@id=\"attach-warranty-pane\"]//*[contains(text(),'Added to Cart')]")
    WebElement addedToCartWarrantyText;

    @FindBy(xpath = "//*[@id=\"attach-warranty-pane\"]")
    WebElement warrantyPane;

    @FindBy(xpath = "//*[@id=\"attach-warranty-pane\"]//button[contains(text(), 'No Thanks')]")
    WebElement warrantyPaneCloseButton;

    @FindBy(xpath = "//*[@class=\"a-popover-wrapper\"]//button[contains(text(), 'No Thanks')]")
    WebElement warrantyWrapperCloseButton;

    @FindBy(xpath = "//*[@id='sc-subtotal-amount-buybox']")
    WebElement priceTextAfter;

    @FindBy(xpath = "//a[@class='nav-a nav-a-2 a-popover-trigger a-declarative']//*[@id='glow-ingress-line2']")
    WebElement Region;

    /*@FindBy(xpath = "//*[@class='a-size-medium a-text-bold']")
    WebElement addedCartText;*/

    @FindBy(xpath = "//*[@id='productTitle']")
    WebElement beforeCartDesc;

    @FindBy(xpath = "//*[@class='sc-list-item-content']//*[@class='a-size-medium sc-product-title']")
    WebElement afterCartDesc;

    @FindBy(xpath = "//*[@class='nav-line-4' and contains(text(),'Account & Lists')]]")
    WebElement accountList;

    @FindBy(xpath = "//*[@id='nav-item-signout' ]")
    WebElement signOutBtn;


    // Initializing Page Factory
    public LandingPage() {
        PageFactory.initElements(driver, this);
    }

    // Defining ACTIONS
    public String Get_Title() {
        return driver.getTitle();
    }

    public void signIn() {
        signin_btn.click();

    }

    public Boolean checkimage() {
        return logo.isDisplayed();
    }

    public void Click_LoginLink() {
        LoginLink.click();
    }

    public LoginPage Login_Btn_Click() {
        return new LoginPage();
    }

    public void enterEmailId() {
        EmailTextBox.sendKeys(prop.getProperty("username"));
        continue_btn.click();
    }

    public void enterPasssword() {
        PasswordTextBox.sendKeys(prop.getProperty("password"));
    }

    public void selectRegion() {
        driver.manage().timeouts().implicitlyWait(5, SECONDS);

        if (Region.getText().equalsIgnoreCase("India")) {
            regionSelect.click();
            driver.manage().timeouts().implicitlyWait(5, SECONDS);
            pincodeText.sendKeys(prop.getProperty("pin"));
            ApplyBtn.click();
            regionContinue.click();
        }

    }

    public void fetchItem() {
        enterItem();
        getItem();
    }

    private void enterItem() {
        search_Text_Box.sendKeys(prop.getProperty("item"));
        driver.manage().timeouts().implicitlyWait(5, SECONDS);
        search_submit.click();
    }

    private void getItem() {
        List<WebElement> cameraList = driver.findElements(By.xpath(("//*[@class='s-include-content-margin s-border-bottom s-latency-cf-section']//a[@class='a-size-base a-link-normal a-text-normal']")));
        ramdomNumber = new Random().nextInt(cameraList.size());
        cameraList.get(ramdomNumber).click();
    }

    public void getItemDetails() {
        itemLink = driver.getCurrentUrl();
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        priceBefore = priceTextBefore.getText();
        Reporter.log("BEFORE PRICE  = " + priceTextBefore.getText(), true);
        descBefore = beforeCartDesc.getText();
        Reporter.log("BEFORE DESCRIPTION = " + beforeCartDesc.getText(), true);
        Reporter.log("ITEM LINK = " + itemLink, true);

    }

    public void addToCart() {
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        addToCartBtn.click();


        /*String cartTitle = driver.getTitle();
        if (!cartTitle.equals("Amazon.com Shopping Cart")) {
            driver.navigate().to("https://www.amazon.com/gp/cart/view.html");
        }*/

        driver.manage().timeouts().implicitlyWait(10, SECONDS);

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (driver.findElements(By.xpath("//*[@class=\"a-popover-wrapper\"]")).size() != 0) {
            warrantyWrapperCloseButton.click();
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(addedToCartText));
            Assert.assertTrue(addedToCartText.isDisplayed());
        } else if (driver.findElements(By.xpath("//*[@id=\"attach-warranty-pane\"]")).size() != 0) {
            warrantyPaneCloseButton.click();
            /*WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(addedToCartWarrantyText));*/
            SoftAssert softAssert = new SoftAssert();
            softAssert.assertTrue(addedToCartWarrantyText.isDisplayed());
        } else {
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(ExpectedConditions.visibilityOf(addedToCartText));
            Assert.assertTrue(addedToCartText.isDisplayed());
        }


//        WebElement foo = wait.until(new Function() {
//            public WebElement apply(WebDriver driver) {
//                return driver.findElement(By.id("foo"));
//            }
//        });

//        Wait<WebDriver> waitS = new FluentWait<WebDriver>(driver).withTimeout(Duration.ofSeconds(30)).pollingEvery(Duration.ofSeconds(2))
//                .ignoring(Exception.class);
//
//        Function<WebDriver, WebElement> f = new Function<WebDriver, WebElement>() {
//            @Override
//            public WebElement apply(WebDriver driver) {
//                return null;
//            }
//        };
//
//        WebElement foo = wait.until(new Function<WebDriver, WebElement>() {
//
//            @Override
//            public WebElement apply(WebDriver driver) {
//                return driver.findElement(By.);
//            }
//
//            @Override
//            public <V> Function<V, WebElement> compose(Function<? super V, ? extends WebDriver> before) {
//                return null;
//            }
//
//            @Override
//            public <V> Function<WebDriver, V> andThen(Function<? super WebElement, ? extends V> after) {
//                return null;
//            }
//        })

    }

    public void getCartDetails() {

        driver.manage().timeouts().implicitlyWait(15, SECONDS);
        driver.navigate().to("https://www.amazon.com/gp/cart/view.html");
        priceAfter = priceTextAfter.getText();
        descAfter = afterCartDesc.getText();
        Reporter.log("AFTER PRICE = " + priceTextAfter.getText(), true);
        Reporter.log("After description = " + afterCartDesc.getText(), true);
        Assert.assertEquals(priceBefore, priceAfter);
        Assert.assertEquals(descBefore, descAfter);
    }

    public void signOut() {
        Actions act = new Actions(driver);
        act.moveToElement(LoginLink).perform();
        WebDriverWait wait = new WebDriverWait(driver, 5);
        wait.until(ExpectedConditions.visibilityOf(signOutBtn));
        signOutBtn.click();


    }


}

