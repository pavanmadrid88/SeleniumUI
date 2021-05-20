package tests;

import base.BaseClass;

import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.Reporter;
import org.testng.SkipException;
import org.testng.annotations.*;
import pages.HomePage;

import java.io.IOException;
import java.util.List;

public class SanityTest extends BaseClass {

    HomePage homePage;
    public  SanityTest() throws IOException {
        super();
    }

    @BeforeMethod
    public void setUpSanityTest() throws IOException {
        initialize();
        homePage = new HomePage();

    }




    @Test
    public void launchCheck() throws IOException {

//        extentTest = extentReports.createTest("WorldoMeters - launch check", "Checks if WorldoMeters portal is Up");
//        extentTest.log(Status.INFO, "Launching Worldometers");
        driver.get(properties.getProperty("url"));

        Assert.assertEquals(homePage.coronaVirusUpdatesLink.isDisplayed(),true);




    }

    @Test
    public void getCurrentWorldPopulation() {

        driver.get(properties.getProperty("url"));

        String population = "";
         List<WebElement> currentWorldPopulationElements = homePage.currentWorldPopulationWebelementList;
        System.out.println("Population element list:" + currentWorldPopulationElements.size());
        for (int i=0;i<currentWorldPopulationElements.size();i++) {
            for(int j=1;j<10;j++) {
                try {
                    String currentPopulationElementValue = currentWorldPopulationElements.get(i).getText();
                    population = population  + currentPopulationElementValue;
                    break;

                } catch (StaleElementReferenceException staleElementReferenceException) {

                }
            }
        }
        Reporter.log("The Current world population is:" + population);
        System.out.println("The Current World Pouplation is :" + population);


    }


    @AfterMethod
    public void tearDown() {
            driver.quit();
    }




}
