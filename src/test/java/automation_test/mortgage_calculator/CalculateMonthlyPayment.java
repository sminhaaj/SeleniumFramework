package automation_test.mortgage_calculator;

import automation_test.BaseClass;
import command_providers.ActOn;
import command_providers.AssertThat;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import utilities.DateUtils;
import utilities.ScreenCapture;

public class CalculateMonthlyPayment extends BaseClass {

    //WebDriver driver;
    private final By HomeValueInputField = By.id("homeval");
    private final By DownPaymentInputField = By.id("downpayment");
    private final By DownPaymentTypeInDollar = By.name("param[downpayment_type]");
    private final By LoanAmountInputField = By.id("loanamt");
    private final By InterestRateInputField = By.id("intrstsrate");
    private final By LoanTermInputField = By.id("loanterm");
    private final By StartMonthDropDown = By.name("param[start_month]");
    private final By StartYearInputField = By.id("start_year");
    private final By PropertyTaxInputField = By.id("pptytax");
    private final By PmiInputField = By.id("pmi");
    private final By HomeInsuranceInputField = By.id("hoi");
    private final By MonthlyHoaInputField = By.id("hoa");
    private final By LoanTypeDropDown = By.name("param[milserve]");
    private final By BuyOrRefiDropDown = By.name("param[refiorbuy]");
    private final By CalculateButton = By.name("cal");
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
    public void enterData(){

        ActOn.element(driver, HomeValueInputField).setValue("300000");
        ActOn.element(driver, DownPaymentInputField).setValue("60000");
        ActOn.element(driver, DownPaymentTypeInDollar).click();
        ActOn.element(driver, LoanAmountInputField).setValue("240000");
        ActOn.element(driver, InterestRateInputField).setValue("3");
        ActOn.element(driver, LoanTermInputField).setValue("30");

        String[] date = DateUtils.returnNextMonth();
        ActOn.element(driver, StartMonthDropDown).selectValue(date[0]);
        ActOn.element(driver, StartYearInputField).setValue(date[1]);

        ActOn.element(driver, PropertyTaxInputField).setValue("5000");
        ActOn.element(driver, PmiInputField).setValue("0.5");
        ActOn.element(driver, HomeInsuranceInputField).setValue("1000");
        ActOn.element(driver, MonthlyHoaInputField).setValue("100");
        ActOn.element(driver, LoanTypeDropDown).selectValue("FHA");
        ActOn.element(driver, BuyOrRefiDropDown).selectValue("Buy");

    }
    @Test
    public void calculateMonthlyPayment(){
        enterData();

        ActOn.element(driver, CalculateButton).click();

        String expectedTotalMonthlyPayment = "1,611.85";
        String formattedXpath = String.format("//h3[text()='$%s']", expectedTotalMonthlyPayment);

        AssertThat.elementAssertions(driver, By.xpath(formattedXpath)).elementIsDisplayed();
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
