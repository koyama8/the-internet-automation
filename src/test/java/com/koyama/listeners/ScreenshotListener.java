package com.koyama.listeners;

import java.io.ByteArrayInputStream;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.koyama.base.UiTestSupport;

import io.qameta.allure.Allure;

public class ScreenshotListener implements ITestListener {

    @Override
    public void onTestFailure(ITestResult result) {
        Object testInstance = result.getInstance();

        if (testInstance instanceof UiTestSupport) {
            UiTestSupport testSupport = (UiTestSupport) testInstance;
            WebDriver driver = testSupport.getDriver();

            if (driver != null) {
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);

                Allure.addAttachment(
                    "Screenshot - " + result.getName(),
                    new ByteArrayInputStream(screenshot)
                );
            }
        }
    }
}
