import AutoFramework.ItemDetails;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PaymentPage {
    WebDriver driver;
    WebDriverWait wait;

    By itemName = By.xpath("//table[@id = 'cart_summary']//td[@class = 'cart_description']/p[@class = 'product-name']/a");
    By itemPrice = By.xpath("//table[@id = 'cart_summary']//td[@class = 'cart_unit']/span/span[@class = 'price']");
    By totalPrice = By.id("total_price");
    By bankWire = By.partialLinkText("Pay by bank wire");
    By submitButton = By.xpath("//button[@class = 'button btn btn-default button-medium' and @type = 'submit']");

    public PaymentPage(WebDriver dr) {
        this.driver = dr;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void checkItemName(String name){
        WebElement itemNameElement = wait.until(ExpectedConditions.visibilityOfElementLocated(itemName));
        Assert.assertEquals(name,itemNameElement.getText());
    }

    public void checkItemPrice(String price){
        WebElement itemPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(itemPrice));
        String newPrice = itemPriceElement.getText().replace("$","");
        Assert.assertEquals(Double.parseDouble(price),Double.parseDouble(newPrice),0.1);
    }

    public void checkTotalPrice(ItemDetails item){
        double deliveryTax = 2;
        WebElement totalPriceElement = wait.until(ExpectedConditions.visibilityOfElementLocated(totalPrice));
        String totalPriceFromSite = totalPriceElement.getText().replace("$","");
        double total = Double.parseDouble(item.quantity) * Double.parseDouble(item.price) + deliveryTax;
        Assert.assertEquals(total,Double.parseDouble(totalPriceFromSite),0.1);
    }

    public void pay(){
        wait.until(ExpectedConditions.visibilityOfElementLocated(bankWire)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(submitButton)).click();
    }
}
