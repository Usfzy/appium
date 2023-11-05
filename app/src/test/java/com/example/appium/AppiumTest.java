package com.example.appium;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.concurrent.TimeUnit;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;

public class AppiumTest {
    @Test
    public void testInputFieldsAndButtonPress() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("deviceName", "Pixel_5_API_33");
        capabilities.setCapability("platformName", "Android");

        capabilities.setCapability("appPackage", "com.example.appium");
        capabilities.setCapability("appActivity", "com.example.appium.MainActivity");

        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");

        AppiumDriver driver = new AndroidDriver(capabilities);

        // Wait for the app to launch
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Enter the first name and last name
        driver.findElement(By.id("inputFirstName")).sendKeys("John");
        driver.findElement(By.id("inputLastName")).sendKeys("Doe");

        // Click the button to display the data
        driver.findElement(By.id("btSubmit")).click();

        // Wait for the data to be displayed
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Assert that the first name and last name are displayed
        String fullName = driver.findElement(By.id("tvFullName")).getText();
        Assert.assertEquals(fullName, "John Doe");
    }
}
