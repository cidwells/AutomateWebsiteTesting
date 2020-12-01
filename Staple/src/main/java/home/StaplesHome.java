package home;

import base.CommonApi;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class StaplesHome extends CommonApi{


    @FindBy(how = How.CSS , using = "#searchInput")
    private static WebElement webElementSearchBar;

    public void searchPaper(){
        webElementSearchBar.sendKeys("paper"+Keys.ENTER);
    }
}
