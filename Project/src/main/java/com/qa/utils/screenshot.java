package com.qa.utils;

import com.qa.base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.File;
import java.io.IOException;


public class screenshot extends BaseTest {

    public static void takeScreenshots(String methodname) throws IOException {
        TakesScreenshot scrShot = ((TakesScreenshot) driver);
        /*File srcFile = scrShot.getScreenshotAs(OutputType.FILE);
//        File destFile = new File( prop.getProperty("screenshotPath")+ methodname +"_"+ LocalTime.now() + ".jpg");
        File destFile = new File( "C:\\Users\\Rishabh\\IdeaProjects\\BhaskarAssignment\\Project\\src\\main\\java\\com\\qa\\screenshots\\ss_"+ methodname +"_"+ LocalTime.now() + ".jpg");
        FileUtils.copyFile(srcFile, destFile);*/

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File(".\\Project\\src\\main\\java\\com\\qa\\screenshots\\file_" + methodname + ".jpg");
        try {
            FileUtils.copyFile(srcFile, destFile);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }
}
