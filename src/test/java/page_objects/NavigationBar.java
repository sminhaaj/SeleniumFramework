package page_objects;

import command_providers.ActOn;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import utilities.ScreenCapture;

public class NavigationBar {

    public WebDriver driver;
    private static final Logger LOGGER = LogManager.getLogger(NavigationBar.class);

    private final By RatesLink = By.linkText("Rates");
    private final By RealAprLink = By.linkText("Real APR");
    private final By MortgageCalculatorLogo = By.xpath("//a/img[@alt='Logo']");

    public NavigationBar(WebDriver driver){
        this.driver = driver;
    }
    public NavigationBar mouseHoverToRates(){
        LOGGER.info("Mouse hover to the Rates link");
        ActOn.element(driver, RatesLink).mouseHover();
        return this;
    }

    public RealApr navigateToRealApr(){
        LOGGER.info("Clicking on the Real APR link");
        ActOn.element(driver, RealAprLink).click();
        return new RealApr(driver);
    }

    public Home navigateToHome(){
        LOGGER.info("Clicking on the Mortgage Calculator logo");
        ActOn.element(driver, MortgageCalculatorLogo).click();
        return new Home(driver);
    }


}