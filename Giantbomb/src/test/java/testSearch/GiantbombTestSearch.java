package testSearch;

import home.GiantbombHome;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class GiantbombTestSearch extends GiantbombHome {
    @Test()
    public void searchText(){
       GiantbombHome giantbombHome = PageFactory.initElements(driver, GiantbombHome.class);
       giantbombHome.searchPersona();
    }
}
