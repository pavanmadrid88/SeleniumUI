package tests;

import base.BaseClass;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import pages.CoronaVirusPage;
import pages.HomePage;
import utils.TestUtils;

import java.io.IOException;

public class CoronaVirusTest extends BaseClass {


    CoronaVirusPage coronaVirusPage;
    HomePage homePage;
    TestUtils testUtils;

    public CoronaVirusTest() throws IOException {

        super();

    }

    @BeforeMethod
    public void setUpCoronaVirusTest() throws IOException {
        initialize();
        coronaVirusPage = new CoronaVirusPage();
        homePage = new HomePage();
        testUtils = new TestUtils();


    }

    @Test
    public void getCurrentTotalWorldWideCoronaVirusCases(){

        //navigate to corona virus page
        homePage.coronaVirusUpdatesLink.click();

        WebDriverWait webDriverWait = new WebDriverWait(driver,30);
        webDriverWait.until(ExpectedConditions.visibilityOf(coronaVirusPage.coronaVirusCasesElement));
        System.out.println("The total current Corona virus cases world wide:" + coronaVirusPage.coronaVirusCasesElement.getText());
    }



    @Test(dataProvider = "getCountryTestData" ,dataProviderClass = TestUtils.class)
    public void getCurrentCountrySpecificCases(String countryName) throws InterruptedException {

        //navigate to corona virus page
        homePage.coronaVirusUpdatesLink.click();

        System.out.println("CountryName from Excel:" + countryName);
        coronaVirusPage.clickOnCountryLink(countryName);

        WebDriverWait webDriverWait = new WebDriverWait(driver,30);
        webDriverWait.until(ExpectedConditions.visibilityOf(coronaVirusPage.coronaVirusCasesElement));
        System.out.println("The total current Corona virus cases in country:" + countryName  + " is " + coronaVirusPage.coronaVirusCasesElement.getText());

    }


    @AfterMethod
    public void tearDownCoronaVirusTest(){
        driver.quit();
    }

}
