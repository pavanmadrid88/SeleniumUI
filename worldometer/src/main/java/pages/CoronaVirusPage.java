package pages;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.IOException;

public class CoronaVirusPage extends BaseClass {




    public  CoronaVirusPage() throws IOException {
        super();
        PageFactory.initElements(driver,this);
    }

    @FindBy(xpath = "//h1[contains(text(),'Coronavirus Cases')]/..//span")
    public WebElement coronaVirusCasesElement;


    @FindBy(xpath =  "//h1[contains(text(),'Deaths')]/..//span")
    public WebElement coronaVirusDeathsElement;

    public void clickOnCountryLink(String countryName) throws InterruptedException {
        String xpath = "//table[@id='main_table_countries_today']//a[text()='" + countryName + "']";
        WebDriverWait webDriverWait = new WebDriverWait(driver,30);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));

        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click();",driver.findElement(By.xpath(xpath)));

//        Actions actions = new Actions(driver);
//        actions.moveToElement(driver.findElement(By.xpath(xpath))).click(driver.findElement(By.xpath(xpath))).build().perform();

        Thread.sleep(5000);

       // WebElement countryLink = driver.findElement(By.xpath("//table[@id='main_table_countries_today']//a[text()='" + countryName + "']"));
       // countryLink.click();
    }



}
