import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class NavigationMenuPage {
    WebDriver driver;
    WebDriverWait wait;

    By woman = By.xpath("//div[@id = 'block_top_menu']/ul/li/a[@title = 'Women']");
    By dresses = By.xpath("//div[@id = 'block_top_menu']/ul/li/a[@title = 'Dresses']");
    By WomanCasualDresses = By.xpath("//div[@id='block_top_menu']/ul/li[1]/ul/li[2]/ul/li[1]/a");
    By WomanEveningDresses = By.xpath("//div[@id='block_top_menu']/ul/li[1]/ul/li[2]/ul/li[2]/a");
    By WomanSummerDresses = By.xpath("//div[@id='block_top_menu']/ul/li[1]/ul/li[2]/ul/li[3]/a");
    By eveningDresses = By.xpath("//div[@id='block_top_menu']/ul/li[2]/ul/li[2]");

    public NavigationMenuPage(WebDriver dr){
        driver = dr;
        wait = new WebDriverWait(driver,10);
    }

    public void NavigationToWomanMenuToCasualDresses() throws InterruptedException {
        Actions actions = new Actions(driver);

        WebElement menuOption = driver.findElement(woman);
        actions.moveToElement(menuOption).perform();

        WebElement menuSubOption = wait.until(ExpectedConditions.visibilityOfElementLocated(WomanCasualDresses));
        actions.moveToElement(menuSubOption).perform();
        menuSubOption.click();
    }

    public void NavigationToWomanMenuToEveningDresses()  {
        Actions actions = new Actions(driver);

        WebElement menuOption = driver.findElement(woman);
        actions.moveToElement(menuOption).perform();

        WebElement menuSubOption = wait.until(ExpectedConditions.visibilityOfElementLocated(WomanEveningDresses));
        actions.moveToElement(menuSubOption).perform();
        menuSubOption.click();
    }

    public void NavigationDressesToEveningDresses()  {
        Actions actions = new Actions(driver);

        WebElement menuOption = driver.findElement(dresses);
        actions.moveToElement(menuOption).perform();

        WebElement menuSubOption = wait.until(ExpectedConditions.visibilityOfElementLocated(eveningDresses));
        actions.moveToElement(menuSubOption).perform();
        menuSubOption.click();

    }

    public void closePage(){
        driver.close();
    }
}
