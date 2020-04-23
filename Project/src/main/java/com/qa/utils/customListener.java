package com.qa.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestListener;
import org.testng.ITestResult;
import org.testng.Reporter;

import java.io.IOException;

public class customListener implements ITestListener , IRetryAnalyzer {
    int counter = 0;
    int retryLimit = 4;

    public boolean retry(ITestResult result) {
        if(counter<retryLimit){
            counter++;
            Reporter.log("Trying failed method"+counter,true);
            return true;
        }
        return false;
    }

    public void onTestSuccess(ITestResult result) {
        Reporter.log("Method passed------->"+result.getName(),true);

    }
    public void onTestFailure(ITestResult result)
    {
        Reporter.log("Method failed------->"+result.getName(),true);
        try {
            screenshot.takeScreenshots(result.getName());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
