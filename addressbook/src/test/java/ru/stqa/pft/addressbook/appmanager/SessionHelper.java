package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SessionHelper extends HelperBase {

    public SessionHelper(WebDriver driver) {
        super(driver);
    }

    public void login(String login, String password) {
        type(By.name("user"), login);
        type(By.name("pass"), password);
        click(By.id("LoginForm"));
        click(By.name("pass"));
        click(By.cssSelector("input:nth-child(7)"));
//        driver.findElement(By.name("user")).sendKeys(login);
//        driver.findElement(By.name("pass")).sendKeys(password);
//        driver.findElement(By.id("LoginForm")).click();
//        driver.findElement(By.name("pass")).click();
//        driver.findElement(By.cssSelector("input:nth-child(7)")).click();
    }
}
