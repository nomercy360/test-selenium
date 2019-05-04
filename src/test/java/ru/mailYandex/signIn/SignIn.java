package ru.mailYandex.signIn;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SignIn {

        private WebDriver driver;
        private WebDriverWait wait;

        public SignIn(WebDriver driver) {
            this.driver = driver;
            wait = new WebDriverWait(driver, 15);
        }

    private By signInButton = By.xpath("/html/body//div/a[@data-reactid=\"24\"]");
    private By loginField = By.id("passp-field-login");
    private By passwordField = By.id("passp-field-passwd");
    private By logInButton  = By.xpath("/html/body/div//form/div/button");
    private By mailPageView = By.xpath("/html/body//span//span/a");

    public void clickSignInButton() {
        driver.findElement(signInButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(loginField));



    }
    public void fillLogin () {
        driver.findElement(loginField).sendKeys("maximkadocnikov@yandex.ru");
        wait.until(ExpectedConditions.visibilityOfElementLocated(passwordField));
    }
    public void fillPassword() {
        driver.findElement(passwordField).sendKeys("Ex123465");
    }
    public void logIn () {
        driver.findElement(logInButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(mailPageView));
    }

}
