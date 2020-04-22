package com.qa.base;

import java.io.FileInputStream;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	 public  static WebDriver driver;
	 public static Properties prop;

	 public BaseTest() {
		
		try {
			prop = new Properties();
			FileInputStream fis = new FileInputStream(
					"E://softwares//eclipse//Project//src//main//java//com//qa//config//config.properties");
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void initialise() {
		String browser = prop.getProperty("browser");
		if (browser.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			System.out.println(browser);}
	 else if (browser.equalsIgnoreCase("firefox")) {
		WebDriverManager.firefoxdriver().setup();

	} else {
			WebDriverManager.iedriver().setup();
		driver = new InternetExplorerDriver();}
		
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(3000, TimeUnit.MILLISECONDS);
		System.out.println(prop.getProperty("url"));
		driver.get(prop.getProperty("url"));
		
		

	}

	

}