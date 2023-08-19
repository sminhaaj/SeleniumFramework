package command_providers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class ElementAssertions {

    private static final Logger LOGGER = LogManager.getLogger(ElementActions.class);

    private WebDriver driver;

    private By locator;

    public ElementAssertions(WebDriver driver, By locator){
        this.driver = driver;
        this.locator = locator;
    }

    public ElementAssertions elementIsDisplayed(){
        boolean displayed = driver.findElement(locator).isDisplayed();
        Assert.assertTrue(displayed, "The expected element does not exist");
        return this;
    }

}
