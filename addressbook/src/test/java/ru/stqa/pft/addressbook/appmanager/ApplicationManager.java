package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.BrowserType;

import java.util.concurrent.TimeUnit;

public class ApplicationManager {

    WebDriver driver;

    private NavigationHelper navigationHelper;
    private GroupHelper groupHelper;
    private SessionHelper sessionHelper;
    private HelperBase helperBase;
    private String browser;
    private ContactHelper contactHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;
    }

    public GroupHelper group() {
        return groupHelper;
    }

    public NavigationHelper goTo() {
        return navigationHelper;
    }

    public SessionHelper getSessionHelper() {
        return sessionHelper;
    }

    public HelperBase getHelperBase() {
        return helperBase;
    }

    public ContactHelper contact() {
        return contactHelper;
    }

    public void init() {
        if (browser.equals(BrowserType.FIREFOX)){
            System.setProperty("webdriver.gecko.driver", "geckodriver");
            driver = new FirefoxDriver();
        } else if (browser.equals(BrowserType.CHROME)){
            System.setProperty("webdriver.chrome.driver", "/Users/max/sandbox/Addressbook/addressbook/src/test/resources/chromedriver_76");
//            System.setProperty("webdriver.chrome.driver", "src/test/resources/chromedriver_76");
            driver = new ChromeDriver();
        } else if (browser.equals(BrowserType.IE)){
            driver = new InternetExplorerDriver();
        }
        driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        driver.get("http://localhost/addressbook/index.php");
        driver.manage().window().setSize(new Dimension(1399, 877));
        groupHelper = new GroupHelper(driver);
        navigationHelper = new NavigationHelper(driver);
        sessionHelper = new SessionHelper(driver);
        helperBase = new HelperBase(driver);
        sessionHelper.login("admin", "secret");
    }

    public void stop() {
        driver.quit();
    }



}
