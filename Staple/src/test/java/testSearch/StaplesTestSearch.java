package testSearch;

import home.StaplesHome;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class StaplesTestSearch extends StaplesHome {
    @Test
    public void searchText(){
        StaplesHome staplesHome = PageFactory.initElements(driver, StaplesHome.class);
        staplesHome.searchPaper();
    }
}
