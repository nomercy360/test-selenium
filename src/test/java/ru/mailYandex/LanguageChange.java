package ru.mailYandex;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;




public class LanguageChange {



    LanguageType current = LanguageType.RUS;







    private WebDriver driver;
    private WebDriverWait wait;

    public LanguageChange(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(driver, 15);
    }

    private By setup = By.cssSelector("[href=\"#setup\"]");
    private By popUpMenu = By.className("settings-popup-menu-item-content");
    private By currentLang = By.cssSelector("[class=\"b-selink__link mail-Settings-Lang\"]");
    private By changeLangButton = By.cssSelector("[data-click-action=\"common.change-lang\"]");
    private By dropDownBox = By.cssSelector("[class=\"b-mail-dropdown__box__content\"]");

    public void enteringSetting() {
        driver.findElement(setup).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(popUpMenu));
        driver.findElement(popUpMenu).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(currentLang));


    }

    public void switchLanguage() {
        switch (current) {
            case ENG:
                driver.findElement(currentLang).getText();
                if (currentLang.equals("English")) {
                    System.out.println("You`re on English now");
                } else {
                    driver.findElement(currentLang).click();
                    wait.until(ExpectedConditions.visibilityOfElementLocated(dropDownBox));
                    List<WebElement> language = driver.findElements(changeLangButton);
                    //System.out.println(language.size());
                    for (int i = 0; i < language.size(); i++) {
                        //System.out.println(language.get(i).getText());
                        if (language.get(i).getText().equals(" English")) {
                            language.get(i).click();
                            break;
                        }
                    }
                }
                break;
            case RUS:
                driver.findElement(currentLang).getText();
                if (currentLang.equals("Русский")) {
                    System.out.println("You`re on English now");

                } else {
                    driver.findElement(currentLang).click();
                    wait.until(ExpectedConditions.visibilityOfElementLocated(dropDownBox));
                    List<WebElement> language = driver.findElements(changeLangButton);
                    //System.out.println(language.size());
                    for (int i = 0; i < language.size(); i++) {
                        //System.out.println(language.get(i).getText());
                        if (language.get(i).getText().equals(" Русский")) {
                            language.get(i).click();
                            break;
                        }
                    }
                }
        }
    }
    public void switchLanguageCheck () {
        switch (current) {
            case RUS:
                wait.until(ExpectedConditions.titleContains("Яндекс.Почта"));
                System.out.println("Language changed successfully");
                break;
            case ENG:
                wait.until(ExpectedConditions.titleContains("Yandex.Post"));
                System.out.println("Language changed successfully");
                break;


        }
    }
}


