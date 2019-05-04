package ru.mailYandex.signIn;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class WebDriverSettings {
    public ChromeDriver driver;
    public WebDriverWait wait;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Maxfactor\\IdeaProjects\\test-selenium\\chrome\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mail.yandex.ru");
        String title = driver.getTitle();
        Assert.assertTrue(title.equals("Яндекс.Почта — бесплатная и надежная электронная почта"));
        SignIn sigIn = PageFactory.initElements(driver, SignIn.class);
        sigIn.clickSignInButton();
        sigIn.fillLogin();
        sigIn.fillPassword();
        sigIn.logIn();


        String title1 =  driver.getTitle();
        //Assert.assertTrue(title1.contains("Входящие — Яндекс.Почта"));
    }
    @After
    public void close(){

        driver.quit();
    }

}
