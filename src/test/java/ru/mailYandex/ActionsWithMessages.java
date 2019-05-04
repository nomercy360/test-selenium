package ru.mailYandex;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Sleeper;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class ActionsWithMessages {
    private WebDriver driver;
    private WebDriverWait wait;

    public ActionsWithMessages(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    private By box = By.cssSelector("[data-nb=\"checkbox\"]");
    private By name = By.cssSelector("[class=\"mail-MessageSnippet-FromText\"]");
    private By deleteButton = By.cssSelector("[class=\"mail-Toolbar-Item-Text js-toolbar-item-title js-toolbar-item-title-delete\"]");
    private By check = By.xpath("//a[@class='mail-NestedList-Item-Info-Link js-folder-info-link']");

    private int prev;
    private String previously;
    public void select () {
        List<WebElement> boxes = driver.findElements(box);
        List<WebElement> names = driver.findElements(name);
        prev = boxes.size();
        previously = driver.findElement(check).getText();
        for (int i = 0; i < boxes.size(); i++) {
            if (names.get(i).getText().equals("Максим Кадочников")) {
                boxes.get(i).click();
            }
        }
        System.out.println("messages selected successfully");

    }
    public void delete () {
        driver.findElement(deleteButton).click();


    }
    public void successCheck() throws InterruptedException {

        System.out.println(previously);
        Thread.sleep(3000);
        String actual = driver.findElement(check).getText();
        System.out.println("Number of your posts before: " + previously);
        System.out.println("Number of your posts now: " + actual);
        Assert.assertNotEquals(actual, previously);
        System.out.println("messages deleted successfully");





    }
}
