package stepDefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;
import java.time.Duration;
import java.util.List;
import static hooks.GlobalHooks.driver;

public class SearchStepDefs {

    @Given("Customer is on the homepage")
    public void openHomepageForSearch() {
        driver.navigate().to("https://infinity-fashion.ro/");
    }

    @When("Customer searches for {string}")
    public void performSearch(String query) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Locate the search input field (adjust locator as needed).
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("search-box")));
        searchBox.clear();
        searchBox.sendKeys(query);

        // Locate and click the search button.
        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"search-wrapper\"]/form/input[2]")
        ));
        searchBtn.click();
    }

    @Then("Search results should display items containing {string}")
    public void verifySearchResultsContain(String expectedTerm) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Wait for the search results container.
        WebElement resultsContainer = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.search-results")));
        // Find product items.
        List<WebElement> products = resultsContainer.findElements(By.cssSelector("div.product-item"));
        boolean matchFound = false;
        for (WebElement product : products) {
            if (product.getText().toLowerCase().contains(expectedTerm.toLowerCase())) {
                matchFound = true;
                break;
            }
        }
        Assert.assertTrue("No search results contain the term: " + expectedTerm, matchFound);
    }

    @Then("Search results should display a {string} message")
    public void verifyNoResultsMessage(String expectedMsg) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement msgElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div.no-results")));
        String actualMsg = msgElement.getText();
        Assert.assertTrue("Expected message not found. Actual: " + actualMsg, actualMsg.contains(expectedMsg));
    }
}
