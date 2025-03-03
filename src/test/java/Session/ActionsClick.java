package Session;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;

public class ActionsClick {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://kitchen.applitools.com/");
        WebElement actionLink = driver.findElement(By.xpath("//*[@id=\"__next\"]/div/div/section/div/div/a[1]/h3"));


        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).click(actionLink).keyUp(Keys.CONTROL).build().perform();

        System.out.println("Windows handles: " + driver.getWindowHandles());
        ArrayList<String> tabs = new ArrayList<>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
    }
}
