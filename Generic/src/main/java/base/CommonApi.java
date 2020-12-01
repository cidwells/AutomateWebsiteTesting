package base;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class CommonApi {
    public static WebDriver driver= null;
    public String  browserstack_username= "cid5";
    public String browserstack_accesskey = "GNxmBhgNBfNJ1s895puT";
    public String saucelabs_username = "";
    public String saucelabs_accesskey = "";
    // public WebDriverWait wait;   this is the WebDriverWait instantiated
    @Parameters({"useCloudEnv", "cloudEnvName", "os", "os_version", "browserName", "browserVersion", "url"})
    @BeforeMethod

    public void setUp(@Optional("false") boolean useCloudEnv, @Optional("browserstack") String cloudEnvName,
                      @Optional("Windows") String os, @Optional("10") String os_version, @Optional("firefox") String browserName,
                      @Optional("82") String browserVersion, @Optional("https://www.expedia.com/") String url) throws IOException {
        if (useCloudEnv== true) {
            if (cloudEnvName.equalsIgnoreCase("browserstack")) {
                getCloudDriver(cloudEnvName,browserstack_username, browserstack_accesskey, os, os_version, browserName, browserVersion);
            }else if (cloudEnvName.equalsIgnoreCase("saucelabs")) {
                getCloudDriver(cloudEnvName, saucelabs_username, saucelabs_accesskey, os, os_version, browserName, browserVersion);
            }
        } else {
            getLocalDriver(browserName);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.get(url);
        System.out.println(driver.manage().window().getSize());
        System.out.println(driver.getCurrentUrl());
    }

    public void getLocalDriver(String browserName) {
        if (browserName.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "..\\Generic\\src\\driver\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browserName.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "..\\Generic\\src\\driver\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
    }
    public WebDriver getCloudDriver(String envName, String envUsername, String envAccessKey, String os,
                                    String os_version, String browserName,
                                    String browserVersion) throws IOException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("browser", browserName);
        cap.setCapability("browser_version", browserVersion);
        cap.setCapability("os", os);
        cap.setCapability("os_version", os_version);
        if (envName.equalsIgnoreCase("Saucelabs")) {
            //resolution for Saucelabs
            driver = new RemoteWebDriver(new URL("https://" + envUsername + ":" + envAccessKey +
                    "@ondemand.saucelabs.com:80/wd/hub"), cap);
        } else if (envName.equalsIgnoreCase("Browserstack")) {
        //  cap.setCapability("resolution", "1024x768");
            driver = new RemoteWebDriver(new URL("https://" + envUsername + ":" + envAccessKey +
                    "@hub-cloud.browserstack.com/wd/hub"), cap);

        }
        return driver;
    }
    @AfterMethod
    public void testAndItsStatus(ITestResult testResult){
        if(testResult.getStatus()== ITestResult.FAILURE) {
            System.out.println("failed: " + testResult.getMethod().getMethodName());
        }else if(testResult.getStatus()== ITestResult.SUCCESS){
            System.out.println("Passed: " +testResult.getName());
        }
    } 
    @AfterMethod
    public void cleanUp() {
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.quit();
    }

    //*********************Helper methods****************************

    //hover on element
    public void hoverOnElement(WebElement webElement) {
        Actions hover = new Actions(driver);
        hover.moveToElement(webElement).perform();
    }

    // explicit wait
    public void explicitWaitUsingVisibility(WebElement webElement, int timeInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeInSec);
        wait.until(ExpectedConditions.visibilityOf(webElement));
    }

    // we can also instantiate the WebDriver wait outside the the method line 25
   /* public void explicitWaitVisibility(WebElement webElement, int timeInSec){
        this.wait= new WebDriverWait(driver,timeInSec);
        this.wait.until(ExpectedConditions.visibilityOf(webElement));
    }*/
    public void explicitWaitUsingClickable(WebElement webElement, int timeInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeInSec);
        wait.until(ExpectedConditions.elementToBeClickable(webElement));
    }
    public void explicitWaitUntilOverlappedElementIsInvisible(WebElement overlappingWebElement, int timeInSec){
        WebDriverWait wait = new WebDriverWait(driver,timeInSec);
        wait.until(ExpectedConditions.invisibilityOf(overlappingWebElement));
    }

    //Get window handle
    public void getCurrentPageHandle() {
        String currentPageHandle = driver.getWindowHandle();
        System.out.println("Current page handle: " + currentPageHandle);
    }

    //switch to new window and can be used to switch back to previous window as well
    public void switchToOtherHandle() {
        String currentPageHandle = driver.getWindowHandle();
        System.out.println("Current page handle: " + currentPageHandle);
        Set<String> handles = driver.getWindowHandles();
        for (String handle : handles) {
            System.out.println(handle);
            if (!handle.equalsIgnoreCase(currentPageHandle)) {
                driver.switchTo().window(handle);
            }
        }
    }

    //Switch to iframe
    public void goToiframe(String locator, WebElement webElement) {
        try {
            driver.switchTo().frame(locator);
            System.out.println("used string locator to find frame");
        } catch (Exception ex) {
            System.out.println("couldn't switch to iframe using Id or Name");
        }
        try {
            driver.switchTo().frame(webElement);
            System.out.println("used WebElement to find frame");
        } catch (Exception ex1) {
            System.out.println("couldn't switch to iframe using WebElement");
        }
    }

    public void goToiframeUsingiframeIndex(int index) {
        driver.switchTo().frame(index);
        System.out.println("switched frame using locator");
    }

    //exit iframe
    public void exitFromiframe() {
        driver.switchTo().defaultContent();
    }

    //switch to javascript popup
    public void switchToJavascriptPopupAndAccept() {
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    public void switchToJavascriptPopupAndDismiss() {
        Alert alert = driver.switchTo().alert();
        alert.dismiss();
    }
    //javascript click
    public void javascriptClick(WebElement webElement){
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].click();", webElement);
    }
    //scroll to an element
    public void scrollByElement(WebElement webElement) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        javascriptExecutor.executeScript("arguments[0].scrollIntoView();", webElement);
        javascriptExecutor.executeScript("window.scrollBy(0,-200);");
        System.out.println("scrolled into view");
    }
    //mouse hover
    public void mouseHover(WebElement webElement){
        Actions actions = new Actions(driver);
        actions.moveToElement(webElement).perform();
    }
    //drag and drop
    public void dragAndDrop(WebElement sourceWebElement, WebElement targetWebElement){
        Actions actions = new Actions(driver);
        actions.dragAndDrop(sourceWebElement,targetWebElement).build().perform();
    }
    public void dragSlider(WebElement webElement, int horizontalOffset, int verticalOffset){
        Actions actions = new Actions(driver);
        actions.dragAndDropBy(webElement,horizontalOffset,verticalOffset).perform();
    }
    //click an element
    public void clickOnElement(String locator) {
        try {
            driver.findElement(By.id(locator)).click();
        } catch (Exception ex) {
            try {
                driver.findElement(By.name(locator)).click();
            } catch (Exception ex2) {
                try {
                    driver.findElement(By.cssSelector(locator)).click();
                } catch (Exception ex3) {
                    try {
                        driver.findElement(By.xpath(locator)).click();
                    } catch (Exception ex4) {
                        try {
                            driver.findElement(By.linkText(locator)).click();
                        } catch (Exception ex5) {
                            System.out.println("no such element found");
                        }
                    }
                }
            }
        }
    }
    public WebElement webElementByXpath(String locator){
       return driver.findElement(By.xpath(locator));
    }
    // get all links and check
    public static List<WebElement> clickableLinks(WebDriver driver) {
        List<WebElement> linksToClick = new ArrayList<WebElement>();
        List<WebElement> elements = driver.findElements(By.tagName("a"));
        elements.addAll(driver.findElements(By.tagName("img")));

        for (WebElement e : elements) {
            if (e.getAttribute("href") != null) {
                linksToClick.add(e);
            }
        }
        return linksToClick;
    }

    public static String linkStatus(URL url) {
        // http://download.java.net/jdk7/archive/b123/docs/api/java/net/HttpURLConnection.html#getResponseMessage%28%29
        try {
            HttpURLConnection http = (HttpURLConnection) url.openConnection();
            http.connect();
            String responseMessage = http.getResponseMessage();
            http.disconnect();
            return responseMessage;
        }
        catch (Exception e) {
            return e.getMessage();
        }
    }
    //test the links
    public void testFindLinks(WebElement staleWebElement) {
        WebDriverWait wait = new WebDriverWait(driver,5);
        wait.until(ExpectedConditions.stalenessOf(staleWebElement));// created a explicit wait because of sync issue with stale webelement

        List<WebElement> linksList = clickableLinks(driver);
        for (WebElement link : linksList) {
            String href = link.getAttribute("href");
            try {
                System.out.println("URL " + href + " returned " + linkStatus(new URL(href)));
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
