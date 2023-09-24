import AutoFramework.ItemDetails;
import AutoFramework.MainTestSetUp;
import AutoFramework.Utilities.ReadFromXML;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.IOException;
import java.util.Collection;

@RunWith(Parameterized.class)
public class SanityTest extends MainTestSetUp {

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
     **************************************************************************************************/
    @Parameterized.Parameters
    public static Collection inputs() throws IOException {
        return ReadFromXML.readXml("C:\\Webdriver\\inits\\Item.xlsx");
    }

//    @Test
//    public void sanityTest() throws InterruptedException, IOException {
//        HomePage homePage = new HomePage(driver, this.getUsername());
//        homePage.navigateTo(this.getMainURL());
//        LoginPage loginPage = homePage.openSignInPage();
//        loginPage.login(this.getUsername(), this.getPassword());
//        loginPage.goToHomePage();
//    }

}
