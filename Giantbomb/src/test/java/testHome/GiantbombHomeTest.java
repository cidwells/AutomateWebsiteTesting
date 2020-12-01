package testHome;

import home.GiantbombHome;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

import java.util.Objects;

public class GiantbombHomeTest extends GiantbombHome{
    @Test
    public void videoHeaderClick() throws Exception {
        GiantbombHome giantbombHome = PageFactory.initElements(driver, GiantbombHome.class);
        giantbombHome.clickOnVideoHeader();
    }
    @Test
    public void videoHeaderSubGiantbombInfiniteClick(){
        GiantbombHome giantbombHome= PageFactory.initElements(driver,GiantbombHome.class);
        giantbombHome.clickOnVideoHeaderSubGiantbombInfinite();
    }
}
