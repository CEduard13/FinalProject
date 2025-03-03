package Session;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class ActionsDemo {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        driver.get("https://kitchen.applitools.com/ingredients/drag-and-drop");
        Actions actions = new Actions(driver);

        WebElement elementToDrag = driver.findElement(By.id("menu-fried-chicken"));
        WebElement dragLocation = driver.findElement((By.id("plate-items")));

        actions.dragAndDrop(elementToDrag, dragLocation).build().perform();
    }
}
