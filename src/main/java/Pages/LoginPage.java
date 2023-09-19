import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;
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

    public LoginPage(WebDriver dr, String email) {
        driver = dr;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.email = email;
    }

    public void login(String email, String password)  {
        if(validateEmail(email) && validatePassword(password)) {
            wait.until(ExpectedConditions.visibilityOfElementLocated(loginForm));
            wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
            wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField)).sendKeys(password);
            wait.until(ExpectedConditions.visibilityOfElementLocated(submitBtn)).click();
            System.out.println("Login passed");
        }else{
            System.out.println("Wrong email input");
        }
    }

    public void checkAccountInfoByText(String expectedText){
        WebElement check = wait.until(ExpectedConditions.visibilityOfElementLocated(accountInfo));
        Assert.assertEquals(expectedText,check.getText());
        System.out.println("Check Account Info passed");
    }

    public void openForgottenPasswordPage(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(passForgottenBtn)).click();
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
