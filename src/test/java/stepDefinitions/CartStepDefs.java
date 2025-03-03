package stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;

import static hooks.GlobalHooks.driver;

public class CartStepDefs {

    // Scenario: Add a product to the cart

    @Given("Customer navigates to the Infinity Fashion homepage for shopping")
    public void navigateToHomepageForShopping() {
        driver.navigate().to("https://infinity-fashion.ro/");
    }

    @When("Customer searches for product {string}")
    public void searchForProduct(String productName) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Assume the search input has id "search-box"
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-box")));
        searchBox.clear();
        searchBox.sendKeys(productName);
        // Submit the search (e.g. by pressing ENTER)
        searchBox.sendKeys(Keys.ENTER);
    }

    @And("Customer hovers and clicks on the first product picture of the search results")
    public void hoverAndClickOnFirstProductPicture() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Assume product pictures have the class "product-picture"
        List<WebElement> pictures = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id=\"products-list\"]/div[2]")));
        Assert.assertFalse("No product pictures found in search results", pictures.isEmpty());
        // Hover over the first picture
        new Actions(driver).moveToElement(pictures.get(0)).perform();
        // Then click it
        pictures.get(0).click();
    }

    @And("Customer clicks on button {string}")
    public void clickOnButton(String buttonLabel) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Assume the size option is presented as a button with the exact text, for example "M"
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"sizes-wrapper\"]/span[3]")));
        button.click();
    }

    @And("Customer adds the first product to the cart")
    public void addFirstProductToCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Assume the product details page has an "Add to Cart" button with class "add-to-cart"
        WebElement addToCartButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"add-to-cart-wrapper\"]/a")));
        addToCartButton.click();
    }

    @Then("The product should be present in the cart")
    public void verifyProductInCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Assume the cart icon has id "cart-icon"
        WebElement cartIcon = wait.until(ExpectedConditions.elementToBeClickable(By.id("cart-icon")));
        cartIcon.click();
        // Verify that at least one cart item (with class "cart-item") is present
        List<WebElement> cartItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".cart-item")));
        Assert.assertFalse("Cart is empty despite adding a product", cartItems.isEmpty());
    }

    // Scenario: Remove a product from the cart

    @Given("Customer has a product in the cart")
    public void ensureProductInCart() {
        // Build a product in the cart using the add-to-cart scenario.
        navigateToHomepageForShopping();
        searchForProduct("rochie");
        hoverAndClickOnFirstProductPicture();
        clickOnButton("M");
        addFirstProductToCart();
        // The site now redirects automatically to the cart.
    }

    @When("Customer removes the product from the cart")
    public void removeProductFromCart() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Since we're already on the cart page, locate the cart items.
        List<WebElement> cartItems = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".cart-entry-container")));
        Assert.assertFalse("No products found in the cart", cartItems.isEmpty());
        // Assume each cart item has a remove button with class "remove-item"
        WebElement removeButton = cartItems.get(0).findElement(By.xpath("//*[@id=\"page-contents\"]/table/tbody/tr/td/div/div[8]/a"));
        removeButton.click();
        // Wait until the removed item is no longer present
        wait.until(ExpectedConditions.stalenessOf(cartItems.get(0)));
    }

    @Then("The cart should be empty")
    public void verifyCartIsEmpty() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // On the cart page, verify that no cart items are present.
        List<WebElement> cartItems = driver.findElements(By.cssSelector(".cart-item"));
        Assert.assertTrue("The cart is not empty", cartItems.isEmpty());
    }
}
