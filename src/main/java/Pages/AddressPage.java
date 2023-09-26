import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AddressPage {
    private WebDriver driver;
    private WebDriverWait wait;

    By name = By.xpath("//ul[@id='address_delivery']/li[@class='address_firstname address_lastname']");
    By addressTextArea = By.xpath("//div[@id='ordermsg']/textarea[@name='message']");
    By proceed_checkout_btn = By.xpath("//button[@name='processAddress']");

    public AddressPage(WebDriver dr){
        this.driver = dr;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public String getTextFromNameField(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(name)).getText();
    }

    public void setOrderAddressInTextArea(String address){
        WebElement area = wait.until(ExpectedConditions.visibilityOfElementLocated(addressTextArea));
        area.sendKeys(address);
        //get the value of the text field
        String actualResult = area.getAttribute("value");
        Assert.assertEquals(address,actualResult);
//        Assert.assertEquals(address, area.getText());   // notice that it is not getting the text itself
    }

    public void proceedToCheckOut() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(proceed_checkout_btn)).click();
    }
}
