package ru.mailYandex.firstTests;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SendMessagePage {
        private WebDriver driver;
        private WebDriverWait wait;
        public SendMessagePage(WebDriver driver) {
            this.driver = driver;
            wait = new WebDriverWait(driver, 15);

    }
    private  By mailComposeButton = By.cssSelector("[class=\"mail-ComposeButton-Text\"]");
    private  By nameTo = By.cssSelector("[name=\"to\"]");
    private  By theme = By.cssSelector("[name=\"subj-13101d00d1206f7efb2a77c952d7e892f97a556e\"]");
    private  By sendButton = By.cssSelector("[class=\"ui-button-text\"]");
    private  By success = By.cssSelector("[class=\"mail-Done-Title js-title-info\"]");
    private  By doneTitle = By.cssSelector("[class=\"mail-Done-Title js-title-info\"]");
    private  By noneEmailError = By.cssSelector("[data-key=\"view=compose-field-to-error\"]");


    public void open() {

        driver.findElement(mailComposeButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameTo));
    }
    public void fillTheme(){
        driver.findElement(theme).click();
        driver.findElement(theme).sendKeys("Weather is Fine!");
    }
    public void sendMessage() {
        driver.findElement(sendButton).click();
    }
    public void fillSender() {
        driver.findElement(nameTo).sendKeys("maximkadocnikov@yandex.ru");

    }
    public void successCheck() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(doneTitle));
        String successText = driver.findElement(success).getText();
        Assert.assertTrue(successText.equals("Письмо отправлено."));
        System.out.println("Message successfully send");
    }
    public void failureNoneEmailCheck () {
        wait.until(ExpectedConditions.visibilityOfElementLocated(noneEmailError));
        String noneEmailErrorText = driver.findElement(noneEmailError).getText();
        Assert.assertTrue(noneEmailErrorText.equals("Поле не заполнено. Необходимо ввести адрес."));
        System.out.println("Message doesn't send");

    }
}
