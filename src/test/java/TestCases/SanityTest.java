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


    @Test
    public void sanityTest() throws IOException, InterruptedException {
        HomePage homePage = new HomePage(driver,this.getUsername());
        homePage.navigateTo(this.getMainURL());
        LoginPage loginPage = homePage.openSignInPage();
        loginPage.login(this.getUsername(),this.getPassword());
        loginPage.goToHomePage();
        homePage.goToAllItems();
        ProductDetailsPage product = homePage.openItem(item.itemName);
//        ProductDetailsPage product = homePage.openCurrentItem("Printed Dress");
        product.addToCard(item);
        product.proceedToCheckOut();
        //TODO: make a separate page SummaryPage
//        product.proceedToCheckOutInSummary();
//        AddressPage addressPage = new AddressPage(this.driver);
//        String nameFromInput = addressPage.getTextFromNameField();
//        Assert.assertEquals(this.getGetUsernameLoggedInfo(),nameFromInput);
//        addressPage.setOrderAddress("varna, Alaska 12345");
//        addressPage.proceedToCheckOut();
//        ShippingPage shippingPage = new ShippingPage(driver);
//        shippingPage.acceptAndProceed();
//        PaymentPage paymentPage = new PaymentPage(driver);
//        paymentPage.checkItemName(item.itemName);
//        paymentPage.checkItemPrice(item.price);
//        paymentPage.checkTotalPrice(item);
//        paymentPage.pay();
    }

}
