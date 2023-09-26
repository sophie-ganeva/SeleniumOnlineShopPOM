import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ShippingPage {
    WebDriver driver;
    WebDriverWait wait;

    By proceedButton = By.xpath("//button[@name='processCarrier']");
    By checkboxAgree = By.id("cgv");
    By checkboxAccept = By.xpath("//div[@id = 'uniform-cgv']");

    public ShippingPage(WebDriver dr) {
        this.driver = dr;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    //check if the shipping option is selected
    public void agreeToTermsOfService(){
        WebElement agree = wait.until(ExpectedConditions.visibilityOfElementLocated(checkboxAgree));
        if(!agree.isSelected()){
            agree.click();
        }
    }

    public void proceedToCheckOut() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(proceedButton)).click();
    }

//    first way
//    public void acceptAndProceed()  {
//        WebElement agree = driver.findElement(checkboxAgree);
//        if(!agree.isSelected()){
//            agree.click();
//        }
//        wait.until(ExpectedConditions.visibilityOfElementLocated(proceedButton)).click();
//    }

    //second way - with actions
    public void acceptAndProceed()  {
        WebElement web = wait.until(ExpectedConditions.visibilityOfElementLocated(checkboxAccept));
        Actions actions = new Actions(this.driver);
        actions.moveToElement(web).perform();
        actions.click().perform();
        wait.until(ExpectedConditions.visibilityOfElementLocated(proceedButton)).click();
    }
}
