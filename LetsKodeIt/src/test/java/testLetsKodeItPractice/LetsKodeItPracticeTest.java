package testLetsKodeItPractice;

import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Test;
import practice.LetsKodeItPractice;

public class LetsKodeItPracticeTest extends LetsKodeItPractice {
    @Test(enabled = true)
    public void switchToNewWindow(){
        LetsKodeItPractice letsKodeItPractice = PageFactory.initElements(driver,LetsKodeItPractice.class);
        letsKodeItPractice.clickPracticeLink();
        letsKodeItPractice.clickOpenWindowButton();
        switchToOtherHandle();
        letsKodeItPractice.searchCourse("Javascript");
        switchToOtherHandle();
        letsKodeItPractice.enterInNameInputField("Sydul");
        scrollByElement(getWebElementHideShowInputField());
        letsKodeItPractice.enterTexInHideShowField(getWebElementHideShowInputField(),"hi");
    }
    @Test
    public void goIntoTheiframe(){
        LetsKodeItPractice letsKodeItPractice = PageFactory.initElements(driver,LetsKodeItPractice.class);
        letsKodeItPractice.clickPracticeLink();
        scrollByElement(getWebElementCoursesiframe());
        goToiframe(null,getWebElementCoursesiframe());
        letsKodeItPractice.searchCourse("python");
        exitFromiframe();
        javascriptClick(getWebElementBmwRadioButton());//javascript should only be used when a webElement isn't interactive, I only did this for an example here
    }
    @Test
    public void closeOutAlertButton(){
        LetsKodeItPractice letsKodeItPractice = PageFactory.initElements(driver,LetsKodeItPractice.class);
        letsKodeItPractice.clickPracticeLink();
        letsKodeItPractice.enterInNameInputField("Sydul");
        driver.findElement(By.id("alertbtn")).click();// Weird I had to find element here otherwise the created click method can't find the element
        switchToJavascriptPopupAndAccept();
        letsKodeItPractice.clickOnAnElement(getWebElementBmwRadioButton());
        driver.findElement(By.id("confirmbtn")).click();// same thing with this one, I wonder if it has to do with it being a javascript popup button
        switchToJavascriptPopupAndDismiss();
    }
    @Test
    public void checkValidLinks() throws InterruptedException {
        LetsKodeItPractice letsKodeItPractice = PageFactory.initElements(driver,LetsKodeItPractice.class);
        letsKodeItPractice.clickPracticeLink();
        testFindLinks(webElementByXpath("//a[@href='https://letskodeit.teachable.com']"));
        Thread.sleep(3000);
    }
}
