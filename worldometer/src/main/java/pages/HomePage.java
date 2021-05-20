package pages;

import base.BaseClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;
import java.util.List;

public class HomePage extends BaseClass {

    public HomePage() throws IOException {
        super();
        PageFactory.initElements(driver,this);
    }




    @FindBy(xpath =  "//strong[text()='Coronavirus Updates']")
    public WebElement coronaVirusUpdatesLink;

    @FindBy(xpath = "//span[@class = 'rts-counter' and @rel = 'current_population']//span[contains(@class,'rts-nr-int')]")
    public List<WebElement> currentWorldPopulationWebelementList;











}
