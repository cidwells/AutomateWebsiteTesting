package practice;

import base.CommonApi;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class LetsKodeItPractice extends CommonApi {
    @FindBy (how = How.XPATH, using = "//a[@href='/pages/practice']")
    private WebElement webElementPracticePageLink;
    @FindBy(how =How.ID, using ="openwindow")
    private WebElement webElementOpenWindowButton;
    @FindBy(how = How.ID, using ="search-courses")
    private WebElement webElementSearchBox;
    @FindBy(how =How.ID, using = "name")
    private WebElement webElementNameInputField;
    @FindBy(how= How.XPATH, using ="//input[@id='bmwradio']")
    private static WebElement webElementBmwRadioButton;
    @FindBy(how= How.ID, using ="courses-iframe" )
    private static WebElement webElementCoursesiframe;
    @FindBy(how = How.ID, using = "alertbtn")
    private static WebElement webElementAlertButton;
    @FindBy(how =How.ID, using="displayed-text")
    private static WebElement webElementHideShowInputField;
    @FindBy(how = How.XPATH, using = "//a[@href='https://letskodeit.teachable.com']")
    private static WebElement webElementStaleLinks;

    public static WebElement getWebElementBmwRadioButton() {
        return webElementBmwRadioButton;
    }

    public static WebElement getWebElementCoursesiframe() {
        return webElementCoursesiframe;
    }

    public static WebElement getWebElementAlertButton() {
        return webElementAlertButton;
    }

    public static WebElement getWebElementHideShowInputField() {
        return webElementHideShowInputField;
    }

    public static WebElement getWebElementStaleLinks() {
        return webElementStaleLinks;
    }

    public void clickPracticeLink(){
      webElementPracticePageLink.click();
        System.out.println("went to practice page");
    }
    public void clickOpenWindowButton(){
        webElementOpenWindowButton.click();
        System.out.println("opened a new window");
    }
    public void searchCourse(String course){
        webElementSearchBox.sendKeys(course +Keys.ENTER);
        System.out.println("searched course");
    }
    public void enterInNameInputField(String name){
        webElementNameInputField.sendKeys(Keys.chord(name,Keys.ENTER)); // sending a combination of keys
        System.out.println("typed name");
    }
    public void clickOnAnElement(WebElement webElement){
        explicitWaitUsingClickable(webElement,3);
        webElement.click();
        System.out.println("clicked " +webElement);
    }
    public void enterTexInHideShowField(WebElement webElement, String anyText){ // sending combination of keystrokes using action object
        Actions actions = new Actions(driver);
        webElement.sendKeys(anyText);
        System.out.println("typed " +anyText);
        actions.keyDown(webElement,Keys.CONTROL).sendKeys("a").keyUp(Keys.CONTROL).build().perform();
        actions.moveToElement(webElement).keyDown(Keys.CONTROL).sendKeys("c").keyUp(Keys.CONTROL).build().perform();
        webElement.clear();
        webElement.sendKeys("bye");
        webElement.clear();
        actions.moveToElement(webElement).keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).build().perform();



    }

}
