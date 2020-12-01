package testHome;

import base.CommonApi;
import home.GoogleHome;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class GoogleHomeTest extends GoogleHome {
    @Test
    public void searchUsingGoogleSearchBar(){
        GoogleHome googleHome = PageFactory.initElements(driver,GoogleHome.class);
        googleHome.EnterTextGoogleSearchBar();
    }

}

