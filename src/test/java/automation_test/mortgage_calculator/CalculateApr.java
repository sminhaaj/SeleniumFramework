package automation_test.mortgage_calculator;

import automation_test.BaseClass;
import command_providers.ActOn;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.ScreenCapture;

import java.time.Duration;

public class CalculateApr extends BaseClass {

    //WebDriver driver;
    private final By RatesLink = By.linkText("Rates");
    private final By RealAprLink = By.linkText("Real APR");
    private final By CalculatorTab = By.xpath("//label[text()='Calculator']");
    private final By HomePriceInputField = By.name("HomeValue");
    private final By DownpaymentInputField = By.name("DownPayment");
    private final By DownpaymentInDollar = By.id("DownPaymentSel0");
    private final By InterestRateInputField = By.name("Interest");
    private final By CalculateRateButton = By.name("calculate");
    private final By ActualAprRate = By.xpath("//strong[text()='3.130%']");
/*
    @BeforeMethod
    public void openBrowser(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        ActOn.browser(driver).openBrowser("https://www.mortgagecalculator.org/");
    }

 */

    public void navigateToRealAprPage(){
        ActOn.element(driver, RatesLink).mouseHover();
        ActOn.element(driver, RealAprLink).click();
        ActOn.wait(driver, CalculatorTab).waitForElementToBeVisible();
    }

    public void enterData(){
        ActOn.element(driver, HomePriceInputField).setValue("200000");
        ActOn.element(driver, DownpaymentInDollar).click();
        ActOn.element(driver, DownpaymentInputField).setValue("15000");
        ActOn.element(driver, InterestRateInputField).setValue("3");
    }
    @Test
    public void calculateRealApr(){
        navigateToRealAprPage();
        enterData();

        ActOn.element(driver, CalculateRateButton).click();

        String actualRealAprRate = ActOn.element(driver, ActualAprRate).getTextValue();
        Assert.assertEquals(actualRealAprRate, "3.130%");
    }
/*
    @AfterMethod
    public void closeBrowser(ITestResult result){
        if(ITestResult.FAILURE == result.getStatus()){
            try{
                ScreenCapture.getScreenShot(driver);
            } catch(Exception e){
                System.out.println("Exception while taking the screenshot " + e.getMessage());
            }
        }
        ActOn.browser(driver).closeBrowser();
    }

 */
}
