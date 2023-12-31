import AutoFramework.ItemDetails;
import AutoFramework.MainTestSetUp;
import AutoFramework.Utilities.Log;
import AutoFramework.Utilities.ReadFromXML;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SanityTest extends MainTestSetUp {

    //the fields are in the item object
    ItemDetails item;

    @Before
    public void SetUp()  {
        try {
            this.mainSetUp();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown(){
        this.mainTestTearDown();
    }

    public SanityTest(String itemName, String price, String quantity, String size, String color){
        item = new ItemDetails(itemName,price,quantity,size,color);
    }

    /**************************************************************************************************
     Read form excel file the items as for each item there is a separate test
     The number fields must be a Number format
     **************************************************************************************************/
    @Parameterized.Parameters
    public static Collection inputs() throws IOException {
        return ReadFromXML.readXml("C:\\Webdriver\\inits\\Item.xlsx");
    }


    /**************************************************************************************************
     Login, select all items one by one from "Item files", add it to the card
     Login with the credentials from the file "Credentials"
     **************************************************************************************************/
    @Test
    public void addItemsToTheCard() throws IOException, InterruptedException {
        HomePage homePage = new HomePage(driver, this.getUsername());
        homePage.navigateTo(this.getMainURL());
        LoginPage loginPage = homePage.openSignInPage();
        loginPage.login(this.getUsername(), this.getPassword());
        loginPage.goToHomePage();
        homePage.goToAllItems();
        ProductDetailsPage product = homePage.openItem(item.itemName);
        product.addToCard(item);
    }

    /**************************************************************************************************
     Login, select all items one by one from "Item files", add them to the card
     Proceed to check out.
     There are several steps needed to finish the order:
     - summary  - see the total price and items of the order
     - address  - get first & last name, write address in the text field and assert it
     - shipping - accept terms and conditions (checkbox)
     - payment - check items, their prices, total price and pay
     **************************************************************************************************/
    @Test
    public void sanityTest() throws IOException, InterruptedException {
        HomePage homePage = new HomePage(driver,this.getUsername());
        homePage.navigateTo(this.getMainURL());

        LoginPage loginPage = homePage.openSignInPage();
        loginPage.login(this.getUsername(),this.getPassword());
        loginPage.goToHomePage();
        homePage.goToAllItems();

        ProductDetailsPage product = homePage.openItem(item.itemName);
        product.addToCard(item);
        product.proceedToCheckOut();

        //TODO: make a separate page SummaryPage
        product.proceedToCheckOutInSummary();

        AddressPage addressPage = new AddressPage(this.driver);
        String nameFromInput = addressPage.getTextFromNameField();
        Assert.assertEquals(this.getGetUsernameLoggedInfo(),nameFromInput);
        addressPage.setOrderAddressInTextArea("Sitka, Alaska 10000");
        addressPage.proceedToCheckOut();

        ShippingPage shippingPage = new ShippingPage(driver);
        shippingPage.acceptAndProceed();
        shippingPage.proceedToCheckOut();

        PaymentPage paymentPage = new PaymentPage(driver);
        paymentPage.checkItemName(item.itemName);
        paymentPage.checkItemPrice(item.price);
        paymentPage.checkTotalPrice(item);
        paymentPage.pay();
    }

}
