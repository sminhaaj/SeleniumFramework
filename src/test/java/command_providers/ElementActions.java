package command_providers;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.interactions.Actions;
public class ElementActions {

    private static final Logger LOGGER = LogManager.getLogger(ElementActions.class);

    private WebDriver driver;

    private By locator;

    public ElementActions(WebDriver driver, By locator){
        this.driver = driver;
        this.locator = locator;
    }
    public WebElement getElement(){
        WebElement element = null;
        try{
            element = driver.findElement(locator);
        } catch(Exception e){
            LOGGER.error("Element Exception for the locator: " + locator + " and the exception is: " + e.getMessage());
        }

        return element;
    }

    public ElementActions click(){
        getElement().click();
        return this;
    }

    public ElementActions setValue(String value){
        getElement().clear();
        getElement().sendKeys(value);
        return this;
    }
    public ElementActions selectValue(String value){
        Select select = new Select(getElement());
        select.selectByVisibleText(value);
        return this;
    }

    public ElementActions mouseHover(){
        Actions actions = new Actions(driver);
        actions.moveToElement(getElement()).perform();
        return this;
    }

    public String getTextValue(){
        return getElement().getText();
    }

}