package pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RegistrationPage extends BasePage{

    public RegistrationPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id="dwfrm_registration_customer_email")
    WebElement emailAddressInput;

    public void inputEmailAddress(String emailAddress){
        emailAddressInput.sendKeys(emailAddress);
    }

}
