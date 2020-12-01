package testHome;

import home.ExpediaHome;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;

public class ExpediaHomeTest extends ExpediaHome {
    @Test (groups = {})
    public void chooseCityToVisit(){
        ExpediaHome expediaHome = PageFactory.initElements(driver,ExpediaHome.class);
        expediaHome.clickOnBlankHeader();
        expediaHome.clickOnFlightsHeader();
        expediaHome.clickOnLeavingFromButton();
        expediaHome.enterLeavingFromLocation("New York (JFK - John F. Kennedy Intl.)");
        expediaHome.clickOnGoingToButton();
        expediaHome.enterGoingToLocation();
        expediaHome.clickOnTravelersMenu();
        expediaHome.clickOnDecreaseAdultPassengers();
        expediaHome.clickConfirmPassengers();
        expediaHome.clickSearchForFlightsButton();
        expediaHome.chooseMultipleListingSort();
        //testFindLinks();
    }
    @Test()
    public void calendar() {
        ExpediaHome expediaHome = PageFactory.initElements(driver,ExpediaHome.class);
        expediaHome.clickOnBlankHeader();
        expediaHome.clickOnFlightsHeader();
        expediaHome.clickOnLeavingFromButton();
        expediaHome.enterLeavingFromLocation();
        expediaHome.clickOnGoingToButton();
        expediaHome.enterGoingToLocation();
        expediaHome.clickOnDepartureDateField();
        expediaHome.getListOfValidDates("30","5");
    }
    @Test(groups = {})
    public  void whatever(){
        ExpediaHome expediaHome = PageFactory.initElements(driver,ExpediaHome.class);
        expediaHome.clickOnBlankHeader();
        expediaHome.clickOnFlightsHeader();
        expediaHome.clickOnLeavingFromButton();
        expediaHome.enterLeavingFromLocation();
    }
}
