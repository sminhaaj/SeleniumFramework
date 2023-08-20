package utilities;

import io.github.bonigarcia.wdm.WebDriverManager;
import net.bytebuddy.asm.MemberSubstitution;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

public class DriverFactory {

    private DriverFactory(){

    }

    private static final DriverFactory instance = new DriverFactory();

    public static DriverFactory getInstance(){
        return instance;
    }

    ThreadLocal<WebDriver> driver = ThreadLocal.withInitial(() -> {
        String environment = System.getProperty("environment") == null? "local": System.getProperty("environment");
        String browser = System.getProperty("browser") == null? "chrome": System.getProperty("browser");
        URL gridUrl = null;

        try{
            gridUrl = new URL(ReadConfigFiles.getPropertyValues("GridUrl"));
        } catch(Exception e){
            e.printStackTrace();
        }

        if(environment.equals("remote") && browser.equals("chrome")){
            ChromeOptions chromeOptions = new ChromeOptions();
            chromeOptions.addArguments("--remote-allow-origins=*");
            return new RemoteWebDriver(gridUrl, chromeOptions);
        } else if(environment.equals("remote") && browser.equals("firefox")){
            FirefoxOptions firefoxOptions = new FirefoxOptions();
            return new RemoteWebDriver(gridUrl, firefoxOptions);
        } else if(environment.equals("remote") && browser.equals("edge")){
            EdgeOptions edgeOptions = new EdgeOptions();
            edgeOptions.addArguments("--remote-allow-origins=*");
            return new RemoteWebDriver(gridUrl, edgeOptions);
        } else{
            WebDriverManager.chromedriver().setup();
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            return new ChromeDriver(options);
        }
    });

    public WebDriver getDriver(){
        return driver.get();
    }

    public void removeDriver(){
        driver.get().quit();
        driver.remove();
    }


}
