package pages;

import base.BasePage;
import org.openqa.selenium.By;

public class LoginPage extends BasePage {
    private By usernameField = By.id("id_user");
    private By passwordField = By.id("id_password");
    private By loginButton = By.id("loginButton");
    private By NextButton = By.xpath("//button[@id='change-btn-text']");
    private By SignInButton = By.xpath(" //button[@id='change-btn-text' and text()='Sign in']");


    public LoginPage() {
        super(); // Call BasePage's protected constructor
    }
    public void login(String username, String password) {

        driver.findElement(usernameField).sendKeys(username);
        driver.findElement(NextButton).click();

        driver.findElement(passwordField).sendKeys(password);

        driver.findElement(SignInButton).click();

    }
}
