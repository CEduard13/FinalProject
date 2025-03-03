package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage{

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "onetrust-accept-btn-handler")
    WebElement acceptAllCookiesButton;

    @FindBy(css = "a[title='Sign in']")
    WebElement signInButton;

    @FindBy(id= "dwfrm_login_email")
    WebElement emailInput;

    @FindBy(id="dwfrm_login_password")
    WebElement passwordInput;

    @FindBy(css="button[data-tau='login_submit']")
    WebElement loginSubmit;

    public void acceptAll(){
        acceptAllCookiesButton.click();
    }

    public void clickSignIn(){
        signInButton.click();
    }

    public void inputUsername(String username){
        emailInput.sendKeys(username);
    }

    public void inputPassword(String password){
        passwordInput.sendKeys(password);
    }

    public void clickLoginButton(){
        loginSubmit.click();
    }
}
