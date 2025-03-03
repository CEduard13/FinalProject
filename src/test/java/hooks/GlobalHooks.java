package hooks;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.concurrent.TimeUnit;

public class GlobalHooks {

    public static WebDriver driver;

    @Before
    public void setup(){
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-search-engine-choice-screen");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://infinity-fashion.ro/");
//        options.addArguments("--disable-gpu");
//        options.addArguments("--no-sandbox");
// options.addArguments("--headless"); // try disabling headless if enabled


    }

    //@After
    public void tearDown(){
        driver.quit();
    }
}
