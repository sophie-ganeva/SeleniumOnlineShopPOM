import AutoFramework.ItemDetails;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ProductDetailsPage {
    WebDriver driver;
    WebDriverWait wait;

    By quantity_wanted = By.id("quantity_wanted");
    By group_1 = By.id("group_1");
    By add_to_cart_btn = By.xpath("//p[@id='add_to_cart']/button[@name='Submit']");
    By proceed_checkout_btn = By.linkText("Proceed to checkout");
    By proceed_checkout_summary_btn = By.xpath("//a[@class = 'button btn btn-default standard-checkout button-medium' and @title = 'Proceed to checkout']");


    public ProductDetailsPage(WebDriver dr) {
        this.driver = dr;
        wait = new WebDriverWait(dr, Duration.ofSeconds(10));
    }

    public void addToCard(ItemDetails details) throws InterruptedException {
        Actions ac = new Actions(driver);
        WebElement quantity = wait.until(ExpectedConditions.visibilityOfElementLocated(quantity_wanted));
        quantity.clear();
        quantity.sendKeys(details.quantity);

        WebElement subjectElement = driver.findElement(group_1);
        Select sel = new Select(subjectElement);
        subjectElement.click();
        sel.selectByVisibleText(details.size);
        switch(details.size){
            case "S": sel.selectByValue("1"); break;
            case "M": sel.selectByValue("2"); break;
            case "L": sel.selectByValue("3"); break;
        }
        Thread.sleep(2000);
        String text = String.format("//ul[@id='color_to_pick_list']/li/a[@title='%s']",details.color);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(text))).click();
        Thread.sleep(2000);
        wait.until(ExpectedConditions.visibilityOfElementLocated(add_to_cart_btn)).click();
    }

    public void proceedToCheckOut() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(proceed_checkout_btn)).click();
    }



    public void proceedToCheckOutInSummary() throws InterruptedException {
        wait.until(ExpectedConditions.visibilityOfElementLocated(proceed_checkout_summary_btn)).click();
    }
}
