package ru.mailYandex;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
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

        driver.findElement(By.xpath("/html/body//div/a[@data-reactid=\"24\"]")).click();
        wait  = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passp-field-login")));

        driver.findElement(By.id("passp-field-login")).sendKeys("maximkadocnikov@yandex.ru");
        driver.findElement(By.xpath("/html/body/div//form/div/button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("passp-field-passwd")));

        driver.findElement(By.id("passp-field-passwd")).sendKeys("Ex123465");
        driver.findElement(By.xpath("/html/body/div//form/div/button")).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("/html/body//span//span/a")));

        String title1 =  driver.getTitle();
        Assert.assertTrue(title1.contains("Входящие — Яндекс.Почта"));
    }
    @After
    public void close(){
        //driver.quit();
    }

}
