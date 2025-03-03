package stepDefinitions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.Map;
import static hooks.GlobalHooks.driver;

public class AuthStepDefs {

    @Given("Customer is on the login page")
    public void navigateToLoginPage() {
        // Navigate directly to the login page.
        driver.navigate().to("https://infinity-fashion.ro/Clients/LogIn");
    }

    @When("Customer enters valid inputs")
    public void fillInLoginCredentials() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

        // Populate the email field.
        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email")));
        emailField.clear();
        emailField.sendKeys("fortrust@yahoo.com");

        // Populate the password field.
        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Password")));
        passwordField.clear();
        passwordField.sendKeys("parola123");
    }

    @And("Customer clicks login form button {string}")
    public void clickLoginFormButton(String buttonText) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//*[@id=\"login-container\"]/form/fieldset/div[6]/input")
        ));
        button.click();
    }



    @Then("Customer verifies the homepage is displayed")
    public void assertHomepageDisplayed() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        boolean isHomepage = wait.until(ExpectedConditions.urlToBe("https://infinity-fashion.ro/"));
        Assert.assertTrue("The homepage was not displayed", isHomepage);
    }



    // Registration steps

    @Given("Customer clicks on register link {string}")
    public void clickRegistrationLink(String linkIdentifier) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Locate the registration link (by its id in this case).
        WebElement regLink = wait.until(ExpectedConditions.elementToBeClickable(By.id("register-client")));
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('target');", regLink);
        regLink.click();
    }

    @When("Customer opens the registration page")
    public void assertOnRegisterPage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Check that the URL contains "Clients/Register".
        boolean onRegisterPage = wait.until(ExpectedConditions.urlContains("Clients/Register"));
        Assert.assertTrue("Not on the register page", onRegisterPage);
    }

    @And("Customer enters registration details using following data :")
    public void fillRegistrationDetails(DataTable dataTable) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Map<String, String> data = dataTable.asMap(String.class, String.class);

        WebElement emailField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Email\"]")));
        emailField.clear();
        emailField.sendKeys(data.get("Email Address"));

        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Name\"]")));
        nameField.clear();
        nameField.sendKeys(data.get("Name"));

        WebElement phoneField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Phone1\"]")));
        phoneField.clear();
        phoneField.sendKeys(data.get("Phone"));

        WebElement addressField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"DeliveryAddress\"]")));
        addressField.clear();
        addressField.sendKeys(data.get("Delivery Address"));

        WebElement passwordField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"Password\"]")));
        passwordField.clear();
        passwordField.sendKeys(data.get("Password"));

        WebElement passwordConfirmField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id=\"PasswordConfirm\"]")));
        passwordConfirmField.clear();
        passwordConfirmField.sendKeys(data.get("Password Confirm"));
    }


    @And("Customer clicks registration button {string}")
    public void clickRegistrationButton(String buttonIdentifier) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Locate the registration button.
        WebElement regButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"register-command\"]")));
        regButton.click();
    }

    @Then("Customer should receive a successful registration confirmation")
    public void assertRegistrationSuccessMessage() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // Verify that a success message element is displayed.
        WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".alert-success")));
        Assert.assertTrue("Registration success message not displayed", successMsg.isDisplayed());
    }
}
