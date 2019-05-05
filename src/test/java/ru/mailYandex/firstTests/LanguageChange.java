package ru.mailYandex.firstTests;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class LanguageChange {





    private WebDriver driver;
    private WebDriverWait wait;

    public LanguageChange(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);

    }

    private By setup = By.cssSelector("[href=\"#setup\"]");
    private By popUpMenu = By.className("settings-popup-menu-item-content");
    private By currentLang = By.cssSelector("[class=\"b-selink__link mail-Settings-Lang\"]");
    public void enteringSetting() {
        driver.findElement(setup).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(popUpMenu));
        driver.findElement(popUpMenu).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(currentLang));


    }


    public void switchLanguage(Language val) {
        String currentLanguage = driver.findElement(currentLang).getText();
        if (currentLanguage.equals(val.getLanguageName())) {
            System.out.println("You're on " + val.getLanguageName() + " now.");


        } else {
            driver.findElement(currentLang).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(val.getPath())));
            driver.findElement(By.xpath(val.getPath())).click();

        }
    }

    public void switchLanguageCheck(Language val) {
        switch (val) {
            case ENG:
                wait.until(ExpectedConditions.titleIs("Yandex.Mail"));
                System.out.println("Your language is English");
            case RUS:
                wait.until(ExpectedConditions.titleIs("Яндекс.Почта"));
                System.out.println("Your language is Russian");

        }




    }
}




