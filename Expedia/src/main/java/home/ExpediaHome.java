package home;

import base.CommonApi;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ExpediaHome extends CommonApi {

    @FindBy(how = How.XPATH, using ="//section[@class='header-region no-stripe']")
    private WebElement webElementBlankHeaderSection;
    @FindBy(how = How.XPATH, using ="//a[@class='uitk-tab-anchor' and @aria-controls='wizard-flight-pwa']")
    private WebElement webElementFlightsHeader;
    @FindBy (how =How.XPATH, using ="//button[@aria-label='Leaving from']")
    private WebElement webElementLeavingFromButton;
    @FindBy (how =How.XPATH, using = "//input[@id='location-field-leg1-origin']")
    private WebElement webElementLeavingFromSearchBar;
    @FindBy(how =How.XPATH, using="//ul[@class='uitk-typeahead-results no-bullet']")
    private WebElement webElementLeavingFromSearchBarAutoCompleteList;
    @FindBy(how = How.XPATH, using =" //button[@aria-label='Going to']")
    private WebElement webElementGoingToButton;
    @FindBy(how =How.XPATH, using ="//input[@id='location-field-leg1-destination' and @placeholder='Where are you going to?']")
    private WebElement webElementGoingToSearchBar;
    @FindBy(how = How.XPATH, using ="//a[@data-testid='travelers-field']")
    private WebElement webElementTravelersMenu;
    @FindBy(how=How.XPATH, using="//div[@class='uitk-flex uitk-flex-item uitk-step-input-controls']//button[@class='uitk-button uitk-button-small uitk-flex-item uitk-step-input-button']")
    private WebElement webElementDecreaseAdultPassengers;
    @FindBy(how=How.XPATH, using="//div[@class='uitk-scrim guestsDoneBtn fade-button']//button[@type='button']")
    private WebElement webElementConfirmPassenger;
    @FindBy(how =How.XPATH, using = "//button[@data-testid='submit-button' and @type='submit']")
    private WebElement webElementSearchForFlightsButton;
    @FindBy(how=How.ID, using ="listings-sort")
    private WebElement webElementListingSort;
    @FindBy(how =How.XPATH, using="//button[@class='uitk-card-link']")
    private WebElement webElementFlightsAvailable;
    @FindBy(how =How.XPATH, using="//button[@data-name='d1']")
    private WebElement webElementDepartureDateField;
    @FindBy(how =How.XPATH, using="//div[@class='uitk-new-date-picker-month']//button[@data-day='18' and contains(@aria-label,'Nov')]")
    private WebElement webElementNov18;
    @FindBy(how=How.XPATH, using = "//div[@class='uitk-new-date-picker-month'][position()=1]/table")
    private  WebElement webElementFirstCalenderTable;

    public WebElement getWebElementFirstCalenderTable() {
        return webElementFirstCalenderTable;
    }

    public void setWebElementFirstCalenderTable(WebElement webElementFirstCalenderTable) {
        this.webElementFirstCalenderTable = webElementFirstCalenderTable;
    }

    public WebElement getWebElementLeavingFromSearchBarAutoCompleteList() {
        return webElementLeavingFromSearchBarAutoCompleteList;
    }

    public void clickOnBlankHeader(){
        WebDriverWait wait = new WebDriverWait(driver,3);
        wait.until(ExpectedConditions.visibilityOf(webElementBlankHeaderSection));
        webElementBlankHeaderSection.click();
    }
    public void clickOnFlightsHeader(){
        WebDriverWait wait = new WebDriverWait(driver,3);//initialization of a explicit wait
        wait.until(ExpectedConditions.elementToBeClickable(webElementFlightsHeader));//this is an explicit wait it will wait until the element is clickable at least for 30 sec
        webElementFlightsHeader.click();
        System.out.println("Clicked flights header");
    }
    public void clickOnLeavingFromButton(){
        webElementLeavingFromButton.click();
        System.out.println("Clicked flights button");
    }
    public void enterLeavingFromLocation(){
        webElementLeavingFromSearchBar.sendKeys("JFK"+ Keys.ENTER);
        System.out.println("entered flight leaving location");
    }
    public void enterLeavingFromLocation(String location){
        webElementLeavingFromSearchBar.sendKeys(location);
        List <WebElement> listOfLavingAirport = getWebElementLeavingFromSearchBarAutoCompleteList().findElements(By.xpath("//ul[@class='uitk-typeahead-results no-bullet']//strong"));
        for(WebElement airport: listOfLavingAirport){
            if(location.equalsIgnoreCase(airport.getText())){
                airport.click();
                System.out.println("clicked "+ location+" airport");
            }
            else System.out.println("not a correct airport");
        }
        System.out.println("entered flight leaving location");
    }
    public void clickOnGoingToButton(){
        webElementGoingToButton.click();
        System.out.println("clicked going to button");
    }
    public void enterGoingToLocation(){
        webElementGoingToSearchBar.sendKeys("MIA"+Keys.ENTER);
        System.out.println("entered mia");
    }
    public void clickOnTravelersMenu(){
        webElementTravelersMenu.click();
        System.out.println("clicked travelers menu");
    }
    public void clickOnDecreaseAdultPassengers(){
        WebDriverWait wait = new WebDriverWait(driver,3);
        wait.until(ExpectedConditions.elementToBeClickable(webElementDecreaseAdultPassengers));
        webElementDecreaseAdultPassengers.click();
        System.out.println("decreased travelers");
    }
    public void clickConfirmPassengers(){
        webElementConfirmPassenger.click();
        System.out.println("clicked on confirm passenger");
    }
    public void clickSearchForFlightsButton(){
        webElementSearchForFlightsButton.click();
        System.out.println("clicked on search for flight button");
    }
    public void chooseMultipleListingSort() {
        WebDriverWait wait = new WebDriverWait(driver,3);
        Select select = new Select(webElementListingSort);
        select.selectByIndex(3); // choosing by index, select index start at 0
        int optionsLength=select.getOptions().size();
        System.out.println(optionsLength);
        for(int i =0; i< optionsLength; i++){  //choosing all the options using a for loop, also i has to < optionLength because it already starts at 0
        wait.until(ExpectedConditions.visibilityOf(webElementFlightsAvailable));
        select.selectByIndex(i);
        }
        explicitWaitUsingVisibility(webElementFlightsAvailable,3); // calling a explicit wait method created in CommonApi
        select.selectByValue("PRICE_DECREASING");//choosing by the value
        explicitWaitUsingVisibility(webElementFlightsAvailable,3);
        select.selectByVisibleText("Price (Highest)");// choosing by the actual text on the webpage
        System.out.println("picked all sorting");
    }
    public void clickOnDepartureDateField(){
        webElementDepartureDateField.click();
        System.out.println("clicked departure date field");
    }
    public void getListOfValidDates(String startingDate, String endingDate) {
        System.out.println("starting");
        WebElement calMonth= driver.findElement(By.xpath("//div[@class='uitk-new-date-picker-month'][position()=1]/table"));
        WebElement calMonth2= driver.findElement(By.xpath("//div[@class='uitk-new-date-picker-month'][position()=2]/table"));
        List <WebElement> allStartingValidDates = calMonth.findElements(By.xpath("//div[@class='uitk-new-date-picker-month'][position()=1]/table//td[contains (@class,'uitk-new-date-picker-day-number')]//button"));
        List <WebElement> allEndingValidDates = calMonth2.findElements(By.xpath("//div[@class='uitk-new-date-picker-month'][position()=2]/table//td[contains (@class,'uitk-new-date-picker-day-number')]//button"));
        for(WebElement validStartingDate: allStartingValidDates){
            if(validStartingDate.getAttribute("class").equalsIgnoreCase("uitk-new-date-picker-day is-disabled")){
                System.out.println("not a valid start date");
            } else if(startingDate.equalsIgnoreCase(validStartingDate.getAttribute("data-day"))){
               validStartingDate.click();
               System.out.println("date "+startingDate+" clicked");
           }
        }
        for(WebElement validEndDate: allEndingValidDates){
            if(validEndDate.getAttribute("class").equalsIgnoreCase("uitk-new-date-picker-day is-disabled")){
            System.out.println("not a valid end date");
        }else if(endingDate.equalsIgnoreCase(validEndDate.getAttribute("data-day"))){
                validEndDate.click();
                System.out.println("date "+endingDate+" clicked");
            }
        }
    }
}
