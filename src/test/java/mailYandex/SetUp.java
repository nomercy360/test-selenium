package mailYandex;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.concurrent.TimeUnit;


public class SetUp {

    static ChromeDriver driver;
    static WebDriverWait wait;

    @BeforeMethod
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\karab\\Downloads\\chromedriver1\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://mail.yandex.ru");
        wait = new WebDriverWait(driver, 15);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("/html/body//div/a[@data-reactid=\"24\"]")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='HeadBanner-Title']")).isDisplayed());


        driver.findElement(By.xpath("//a[@data-reactid='24']")).click();
        WebElement login = driver.findElement(By.xpath("//*[@id=\"passp-field-login\"]"));
        String loginName = "maximkadocnikov@yandex.ru";
        login.sendKeys(loginName);
        login.submit();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//*[@id='passp-field-passwd']")));
        String password = "Ex123465";
        WebElement pass = driver.findElement(By.xpath("//*[@id='passp-field-passwd']"));
        pass.sendKeys(password);
        pass.submit();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@data-key='view=footer-help-link']")));
        //Assert.assertTrue(driver.findElement(By.xpath("//a[@data-id='calendar']")).isDisplayed());


    }

    @AfterMethod
    public void close() {

        //driver.quit();
    }

}

