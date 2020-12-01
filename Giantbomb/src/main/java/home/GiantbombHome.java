package home;

import base.CommonApi;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class GiantbombHome extends CommonApi {

    @FindBy (how = How.XPATH , using = "//div[@class='masthead-row masthead-default']//div[@data-toggle='search']" )
    private static WebElement webElementSearchButtonExpand;
    @FindBy (how = How.XPATH , using = "//input[@placeholder='Search Giant Bomb']")
    private static WebElement webElementSearchBar;
    @FindBy (how = How.XPATH , using = "//div[@class='masthead-nav-item-wrap']/a[@href='/videos/']")
    private  static WebElement webElementVideoHeader;
    @FindBy (how = How.XPATH , using = "//div[@class='masthead-row masthead-default']//a[@href='/infinite/']")
    private  static WebElement webElementVideoHeaderSubGiantbombInfinite;

    public void searchPersona(){
        webElementSearchButtonExpand.click();
        webElementSearchBar.sendKeys("Persona 4"+ Keys.ENTER);
    }
    public void clickOnVideoHeader()throws Exception{
        webElementVideoHeader.click();
    }
    public void clickOnVideoHeaderSubGiantbombInfinite(){
        hoverOnElement(webElementVideoHeader);
        webElementVideoHeaderSubGiantbombInfinite.click();

    }
}
