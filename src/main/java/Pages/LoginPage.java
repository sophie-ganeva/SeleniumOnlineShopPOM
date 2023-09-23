import AutoFramework.Utilities.Log;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    WebDriverWait wait;
    String email;
    By loginForm = By.id("login_form");
    By emailField = By.id("email");
    By passwordField = By.id("passwd");
    By submitBtn = By.id("SubmitLogin");
    By accountInfo = By.xpath("//div[@class='header_user_info']/a[@class='account']");
    By logOutBtn = By.xpath("//div[@class='header_user_info']/a[@class='logout']");
    By passForgottenBtn = By.xpath("//a[contains(text(),'Forgot your password?')]");
    By alertBar = By.xpath("(//div[@class='alert alert-danger'])[1]");


    public LoginPage(WebDriver dr, String email) {
        driver = dr;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.email = email;
    }

    public void login(String email, String password)  {
        WebElement alertMessage = driver.findElement(alertBar);
        boolean isAlertShown = alertMessage.isDisplayed();

        if (validateEmail(email) && validatePassword(password) && !isAlertShown) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginForm));
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
            wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
            wait.until(ExpectedConditions.visibilityOfElementLocated(submitBtn)).click();
            System.out.println("Login passed");
            Log.info("Login passed");
        }else{
            Log.error("Wrong email or password input");
            System.out.println("Wrong email or password input");
            throw new IllegalArgumentException("Wrong email or password");
        }
    }

    public void checkAccountInfoByText(String expectedText){
        WebElement check = wait.until(ExpectedConditions.visibilityOfElementLocated(accountInfo));
        Assert.assertEquals(expectedText,check.getText());
        System.out.println("Check Account Info passed");
    }

    public ForgottenPasswordPage openForgottenPasswordPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(passForgottenBtn)).click();
        return new ForgottenPasswordPage(driver);
    }

    public void logOut(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(logOutBtn)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginForm));
        System.out.println("Logout passed");
    }

    public boolean validateEmail(String email){
        if(!email.contains("@")){
            return false;
        }
        return true;
    }
    public boolean validatePassword(String password){
        if(password.length() < 5){
            return false;
        }
        return true;
    }

    public void closePage(){
        driver.close();
    }
}
