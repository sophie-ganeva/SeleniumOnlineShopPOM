import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ForgottenPasswordPage {
    WebDriver driver;
    WebDriverWait wait;

    By ForgotYourPassBox = By.xpath("//div[@class='box']");
//    By emailField = By.id("email");

    By emailField = By.xpath("//div[@class='form-group']/input[@id='email']");
    By retrievePasswordBtn = By.xpath("//button[@class='btn btn-default button button-medium']");
    By alertMsgNoRegisteredAccount = By.xpath("//div[@class='alert alert-danger']/ol/li[contains(text(),'There is no account registered for this email address.')]");
//    By confirmationMsgValidAccount = By.xpath("(//div[@class='box']/p[contains(text(),'A confirmation email has been sent to your address:')])[0]");
    By confirmationMsgValidAccount = By.xpath("//div[@class='box']//p[@class='alert alert-success']");
//    By confirmationMsgValidAccount = By.xpath("//p[contains(text(),'A confirmation email has been sent to your address: ')]");
//    "//div[@class='alert alert-success']/ol/li[contains(text(),'A confirmation email has been sent to your address: ')]"


    public ForgottenPasswordPage(WebDriver dr){
        driver = dr;
        wait = new WebDriverWait(driver, Duration.ofSeconds(50));
    }

    //TODO: one way to optimize the functions
//    public void RetrievePassword(String message, String email, boolean isEmailExisting){
//        wait.until(ExpectedConditions.visibilityOfElementLocated(ForgotYourPassBox));
//        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(retrievePasswordBtn)).click();
//        WebElement alertMsg;
//        if(isEmailExisting){
//            alertMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(confirmationMsgValidAccount));
//        }else{
//            alertMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(alertMsgNoRegisteredAccount));
//        }
//        Assert.assertEquals(message, alertMsg.getText());
//    }
//    public void RetrievePasswordWithInvalidEmail(String email){
//        String expectedText = "There is no account registered for this email address.";
//        boolean isEmailExisting = true;
//        RetrievePassword(expectedText,email,isEmailExisting);
//        System.out.println("Retrieve Password With Invalid Email passed");
//    }
    //    public void RetrievePasswordWithValidEmail(String email){
//        String expectedText = "A confirmation email has been sent to your address: " + email;
//        boolean isEmailExisting = false;
//        RetrievePassword(expectedText,email,isEmailExisting);
//        System.out.println("Retrieve Password With Valid Email passed");
//    }

    public void RetrievePasswordWithInvalidEmail(String email){
        String expectedText = "There is no account registered for this email address.";
        wait.until(ExpectedConditions.visibilityOfElementLocated(ForgotYourPassBox));
        System.out.println("ForgotYourPassBox passed");
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(retrievePasswordBtn)).click();
        WebElement alertMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(alertMsgNoRegisteredAccount));
        System.out.println(alertMsg.getText());
        Assert.assertEquals(expectedText, alertMsg.getText());
        System.out.println("Retrieve Password With Invalid Email passed");
    }

    public void RetrievePasswordWithValidEmail(String email){
        String expectedText = "A confirmation email has been sent to your address: " + email;
        wait.until(ExpectedConditions.visibilityOfElementLocated(ForgotYourPassBox));
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailField)).sendKeys(email);
        wait.until(ExpectedConditions.visibilityOfElementLocated(retrievePasswordBtn)).click();
        WebElement confirmationElement = driver.findElement(confirmationMsgValidAccount);
        String confirmationMsg = confirmationElement.getText();
        System.out.println(confirmationMsg);
        Assert.assertEquals(expectedText, confirmationMsg);
        System.out.println("Retrieve Password With Valid Email passed");
    }

    public void closePage(){
        driver.close();
    }
}
