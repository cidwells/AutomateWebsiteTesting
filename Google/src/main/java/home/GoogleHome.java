package home;

import base.CommonApi;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class GoogleHome extends CommonApi {
    @FindBy(how = How.XPATH, using = "//input[@name='q']")
    private WebElement webElementGoogleSearchBar;

    public void EnterTextGoogleSearchBar() {
        boolean isIt = webElementGoogleSearchBar.isEnabled();//this is to check if the webelement is enabled
        if (isIt) {                                         //if a webelement is not enabled you cannot use it
            System.out.println("Element is enabled");       //
        }
        Assert.assertEquals(true,webElementGoogleSearchBar.isDisplayed() );
        webElementGoogleSearchBar.sendKeys("Mustang"+ Keys.ENTER);
    }
}
