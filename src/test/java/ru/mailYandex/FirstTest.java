package ru.mailYandex;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;


public class FirstTest extends WebDriverSettings {




    @Test
    public void sendMessageWithoutAny() {
        driver.findElement(By.cssSelector("[class=\"mail-ComposeButton-Text\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name=\"to\"]")));
        driver.findElement(By.cssSelector("[class=\"ui-button-text\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-key=\"view=compose-field-to-error\"]")));
        String errorText = driver.findElement(By.cssSelector("[data-key=\"view=compose-field-to-error\"]")).getText();
        Assert.assertTrue(errorText.equals("Поле не заполнено. Необходимо ввести адрес."));


    }

    @Test
    public void sendMessageWithoutText() {
        driver.findElement(By.cssSelector("[class=\"mail-ComposeButton-Text\"]")).click();
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name=\"to\"]")));
        field.sendKeys("maximkadocnikov@yandex.ru");
        driver.findElement(By.cssSelector("[class=\"ui-button-text\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"mail-Done-Title js-title-info\"]")));
        String success = driver.findElement(By.cssSelector("[class=\"mail-Done-Title js-title-info\"]")).getText();
        Assert.assertTrue(success.equals("Письмо отправлено."));

    }

    @Test
    public void sendMessageWithTheme() {
        driver.findElement(By.cssSelector("[class=\"mail-ComposeButton-Text\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name=\"to\"]")));
        driver.findElement(By.cssSelector("[name=\"subj-13101d00d1206f7efb2a77c952d7e892f97a556e\"]")).click();
        driver.findElement(By.cssSelector("[name=\"subj-13101d00d1206f7efb2a77c952d7e892f97a556e\"]")).sendKeys("погода");
        driver.findElement(By.cssSelector("[class=\"ui-button-text\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[data-key=\"view=compose-field-to-error\"]")));
        String errorText = driver.findElement(By.cssSelector("[data-key=\"view=compose-field-to-error\"]")).getText();
        Assert.assertTrue(errorText.equals("Поле не заполнено. Необходимо ввести адрес."));

    }

    @Test
    public void sendFullMessage() {
        driver.findElement(By.cssSelector("[class=\"mail-ComposeButton-Text\"]")).click();
        WebElement field = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[name=\"to\"]")));
        field.sendKeys("maximkadocnikov@yandex.ru");
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//body/ul")));
        driver.findElement(By.xpath("//body/ul")).click();
        driver.findElement(By.cssSelector("[name=\"subj-13101d00d1206f7efb2a77c952d7e892f97a556e\"]")).click();
        driver.findElement(By.cssSelector("[name=\"subj-13101d00d1206f7efb2a77c952d7e892f97a556e\"]")).sendKeys("погода");
        driver.findElement(By.cssSelector("[class=\"ui-button-text\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"mail-Done-Title js-title-info\"]")));
        String success = driver.findElement(By.cssSelector("[class=\"mail-Done-Title js-title-info\"]")).getText();
        Assert.assertTrue(success.equals("Письмо отправлено."));

    }


    @Test
    public void messageSelection() {
        List<WebElement> box = driver.findElements(By.cssSelector("[data-nb=\"checkbox\"]"));
        List<WebElement> name = driver.findElements(By.cssSelector("[class=\"mail-MessageSnippet-FromText\"]"));
        System.out.println(box.size());
        for (int i = 0; i < box.size(); i++) {
            System.out.println(box.get(i).getText());
            if (name.get(i).getText().equals("Максим Кадочников")) {
                box.get(i).click();
            }
        }

    }

    @Test
    public void deleteMessages() {

        String prev = driver.findElement(By.cssSelector("[class=\"mail-NestedList-Item-Info-Extras\"]")).getText();
        List<WebElement> box = driver.findElements(By.cssSelector("[data-nb=\"checkbox\"]"));
        List<WebElement> name = driver.findElements(By.cssSelector("[class=\"mail-MessageSnippet-FromText\"]"));
        System.out.println(box.size());
        for (int i = 0; i < box.size(); i++) {
            System.out.println(box.get(i).getText());
            if (name.get(i).getText().equals("Максим Кадочников")) {
                box.get(i).click();
            }
        }
        driver.findElement(By.cssSelector("[class=\"mail-Toolbar-Item-Text js-toolbar-item-title js-toolbar-item-title-delete\"]")).click();
        String s = driver.findElement(By.cssSelector("[class=\"mail-NestedList-Item-Info-Extras\"]")).getText();
        Assert.assertFalse(s.equals(prev));
        System.out.println(s);




    }
    @Test
    public void switchToEng() {
        driver.findElement(By.cssSelector("[href=\"#setup\"]")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("settings-popup-menu-item-content")));
        driver.findElement(By.className("settings-popup-menu-item-content")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"b-selink__link mail-Settings-Lang\"]")));
        String currentLang = driver.findElement(By.cssSelector("[class=\"b-selink__link mail-Settings-Lang\"]")).getText();

        if (currentLang.equals("English")) {
            System.out.println("You`re on English now");
        } else {
            driver.findElement(By.cssSelector("[class=\"b-selink__link mail-Settings-Lang\"]")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"b-mail-dropdown__box__content\"]")));
            List<WebElement> language = driver.findElements(By.cssSelector("[data-click-action=\"common.change-lang\"]"));
            //System.out.println(language.size());
            for (int i = 0; i < language.size(); i++) {
                //System.out.println(language.get(i).getText());
                if (language.get(i).getText().equals(" English")) {
                    language.get(i).click();
                    break;
                }
            }
            wait.until(ExpectedConditions.titleContains("Yandex.Mail"));
            String title = driver.getTitle();
            Assert.assertTrue(title.equals("Yandex.Mail"));
            System.out.println("Language changed successfully");


        }
    }
        @Test
    public void switchToRus () {
            driver.findElement(By.cssSelector("[href=\"#setup\"]")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("settings-popup-menu-item-content")));
            driver.findElement(By.className("settings-popup-menu-item-content")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"b-selink__link mail-Settings-Lang\"]")));
            String currentLang = driver.findElement(By.cssSelector("[class=\"b-selink__link mail-Settings-Lang\"]")).getText();

            if (currentLang.equals("Русский")) {
                System.out.println("You`re on Russian now");
            } else {
                driver.findElement(By.cssSelector("[class=\"b-selink__link mail-Settings-Lang\"]")).click();
                wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[class=\"b-mail-dropdown__box__content\"]")));
                List<WebElement> language = driver.findElements(By.cssSelector("[data-click-action=\"common.change-lang\"]"));
                //System.out.println(language.size());
                for (int i = 0; i < language.size(); i++) {
                    //System.out.println(language.get(i).getText());
                    if (language.get(i).getText().equals(" Русский")) {
                        language.get(i).click();
                        break;
                    }
                }
                wait.until(ExpectedConditions.titleContains("Яндекс.Почта"));
                String title = driver.getTitle();
                Assert.assertTrue(title.equals("Яндекс.Почта"));
                System.out.println("Language changed successfully");


            }


        }





}