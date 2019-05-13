package tests.test;

import io.qameta.allure.Step;
import io.qameta.htmlelements.WebPageFactory;
import io.qameta.htmlelements.matcher.DisplayedMatcher;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.mailYandex.firstTests.Language;
import tests.pages.SinglePage;

import static org.hamcrest.Matchers.not;

public class YandexTest {
    private static Logger LOGGER = LoggerFactory.getLogger(YandexTest.class);
    WebPageFactory factory = new WebPageFactory();
    WebDriver driver;
    SinglePage page;
    private int dell;
    private int previously;
    private boolean pass;

    @BeforeMethod(alwaysRun = true, description = "зашли в почту")
    private void setUp() {
        driver = new ChromeDriver();
        page = factory.get(driver, SinglePage.class);
        page.getWrappedDriver().manage().window().maximize();
        page.go();
        page.authorization().
                waitUntil("ожидание окна авторизации", DisplayedMatcher.displayed(), 5);
        page.authorization().authorizationButton().click();
        page.passLoginForm().
                waitUntil("ожидание окна ввода логина", DisplayedMatcher.displayed(), 5);
        page.passLoginForm().loginField().sendKeys("maximkadocnikov@yandex.ru");
        page.passLoginForm().logInButton().click();
        page.passLoginForm().passField().
                waitUntil("ожидание окна ввода пароля", DisplayedMatcher.displayed(), 5);
        page.passLoginForm().passField().sendKeys("Ex123465");
        page.passLoginForm().logInButton().click();
        page.composeButtonHolder().
                waitUntil("Ожидание того что мы зашли", DisplayedMatcher.displayed(), 15);

    }

    @AfterMethod(alwaysRun = true, description = "вышли из браузера")
    private void close() {
        page.getWrappedDriver().close();

    }

    @Step("Переход в настройки")
    private void goToSettings() {
        page.settingControl().jumpToPopUpMenu().click();
        page.popUpmenu().
                waitUntil("ожидание появления меню с настройками",
                        DisplayedMatcher.displayed(), 5);
        page.popUpmenu().allSettings().click();
        page.langSettings().waitUntil("ожидание переход во все настройки",
                DisplayedMatcher.displayed(), 5);


    }

    @Step("Смена языка на {val}")
    private void languageSwitch(Language val) {
        String currentLang = page.langSettings().changeLangButton().getText();
        if (currentLang.equals(val.getLanguageName())) {
            LOGGER.info("Текущий язык и так: " + val.getLanguageName());

        } else {
            page.langSettings().changeLangButton().click();
            page.langDropDownBox()
                    .waitUntil("ожидание появления списка языков", DisplayedMatcher.displayed(), 5);
            page.langDropDownBox().selectLang(val.getPath()).click();
            LOGGER.info("Язык успешно сменен на: " + val.getLanguageName());

        }
    }

    @Step("Заходим в раздел отправки письма")
    private void goToWriteLetter() {
        page.composeButtonHolder().composeButton().click();
        page.mailComposeField()
                .waitUntil("поле отправки письма", DisplayedMatcher.displayed(), 5);

    }

    @Step("Проверка смены языка")
    private void languageSwitchCheck(Language selected) {
        page.langDropDownBox()
                .waitUntil("пока не изчезнет список языков", not(DisplayedMatcher.displayed()), 5);
        Assert.assertEquals(page.langSettings().changeLangButton().getText(),
                selected.getLanguageName());
    }

    @Step("Отправка сообщения")
    public void mailSend(String address, String theme) {
        page.mailComposeField().recipientAddress().sendKeys(address);
        page.mailComposeField().messageTheme().sendKeys(theme);
        page.mailComposeField().sendButton().click();
    }

    @Step("проверка отправки сообщения")
    private void mailSendCheck() {
        try {
            if (page.mailComposeField().mailErrorMessage().isDisplayed()) {
                if (page.mailComposeField().mailErrorMessage().getText().endsWith(".")) {
                    LOGGER.info("Адресс отсутсвует");
                    pass = false;

                } else if (page.mailComposeField().mailErrorMessage().getText().contains(":")) {
                    LOGGER.info("Неккоректный адрес");
                    pass = false;
                }

            }
        } catch (WebDriverException e) {
            LOGGER.error("В адресе ошибки не было обнаружено");
            pass = true;
        }

        if (pass) {
            page.mailDonePage().messageDoneTitle()
                    .waitUntil("Ожидания ссобщения об успешной отправки", DisplayedMatcher.displayed(), 5);
            Assert.assertTrue(page.mailDonePage().messageDoneTitle().isDisplayed());
            LOGGER.info("Сообщение успешно отправлено");

        }

    }


    @Step("Удаление сообщений")
    private void delMessages() {

        if (page.toolbar().deleteButton().isDisplayed()) {
            page.toolbar().deleteButton().click();
            dell = 1;


        } else {
            LOGGER.info("Сообщения не были выбраны");
            dell = 0;
        }
        previously = Integer
                .parseInt(page.messagesTypes().incomingMessages().getText().replaceAll("[^0-9]+", ""));
    }

    @Step("выделение писем по адресу : {address}")
    private void selMessages(String address) {
        page.messagesView().checkBoxes(address).forEach(WebElement::click);
    }

    @Step("Проверка удаления писем")
    private void deleteMessagesCheck() {

        if (dell == 0) {
            LOGGER.info("Нечего удалять");
        } else {
            driver.navigate().refresh();
            page.messagesTypes()
                    .waitUntil("обновление количества писем", DisplayedMatcher.displayed(), 5);
            int actual = Integer
                    .parseInt(page.messagesTypes().incomingMessages().getText().replaceAll("[^0-9]+", ""));
            Assert.assertEquals(previously, actual);
        }
    }

    @Test(groups = "MS-1")
    private void sendFullMessage() {
        goToWriteLetter();
        mailSend("maximkadocnikov@yandex.ru", "weather");
        mailSendCheck();
    }

    @Test(groups = "MS-2")
    private void noEmailMessage() {
        goToWriteLetter();
        mailSend("", "weather");
        mailSendCheck();
    }

    @Test(groups = "MS-3")
    private void wrongEmailMessage() {
        goToWriteLetter();
        mailSend("asdasd", "weather");
        mailSendCheck();
    }

    @Test(groups = "SW-1")
    private void languageChange() {
        goToSettings();
        languageSwitch(Language.ENG);
        languageSwitchCheck(Language.ENG);
    }

    @Test(groups = "SW-2")
    private void languageChange2() {
        goToSettings();
        languageSwitch(Language.RUS);
        languageSwitchCheck(Language.RUS);
    }

    @Test(groups = "DL-1")
    private void selectMessages() {
        selMessages("maximkadocnikov@yandex.ru");

    }

    @Test(groups = "DL-2")
    private void deleteMessages() {
        selMessages("maximkadocnikov@yandex.ru");
        delMessages();
        deleteMessagesCheck();


    }

}
