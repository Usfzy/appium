package com.example.appium;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
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

        WebElement fromLocationSpinner = driver.findElement(By.id("spFromLocation"));
        fromLocationSpinner.click();

        WebElement firstLocationItem = driver.findElements(By.className("android.widget.TextView")).get(1);
        firstLocationItem.click();

        WebElement toLocationSpinner = driver.findElement(By.id("spToLocation"));
        toLocationSpinner.click();

        WebElement secondLocationItem = driver.findElements(By.className("android.widget.TextView")).get(2);
        secondLocationItem.click();

        WebElement fromDate = driver.findElement(By.id("inputFromDate"));
        fromDate.sendKeys("(01/01/2022)");

        WebElement toDate = driver.findElement(By.id("inputToDate"));
        toDate.sendKeys("(01/02/2022)");

        WebElement firstNameField = driver.findElement(By.id("inputFirstName"));
        firstNameField.sendKeys("John");

        WebElement lastNameField = driver.findElement(By.id("inputLastName"));
        lastNameField.sendKeys("Doe");

        WebElement submitButton = driver.findElement(By.id("btSubmit"));
        submitButton.click();

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        // Verify that the form was submitted successfully
        WebElement toastMessage = driver.findElement(By.xpath("//android.widget.Toast[1]"));
        assertEquals("Data submitted successfully", toastMessage.getText());

    }

    public void waitForElementToBeInteractive(AppiumDriver driver, By by, Duration timeout) {
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.elementToBeClickable(by));
    }
}
