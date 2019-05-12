package mailYandex;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.util.List;

class MailYandexTests extends SetUp {
    private static final Logger LOGGER = Logger.getLogger(MailYandexTests.class);

    private static int prev;
    private static int del;
    private static String previously;
    private static String s;


    private void goToSettings() {
        driver.findElement(By.xpath("//a[@href='#setup']")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class=\"settings-popup-title-content\"]")));
        driver.findElement(By.xpath("//span[@class='settings-popup-title-content']")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='b-selink__link mail-Settings-Lang']")));


    }

    private void goToSendMessage() {
        driver.findElement(By.xpath("//a[@class='mail-ComposeButton js-main-action-compose']")).click();
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//span[@class='ui-button-text']")));


    }

    private void fillEmailAdress(String emailAddress) {
        driver.findElement(By.xpath("//div[@name='to']")).sendKeys(emailAddress);


    }

    private void fillTheme(String theme) {
        driver.findElement(By.xpath("//input[@name='subj-13101d00d1206f7efb2a77c952d7e892f97a556e']")).sendKeys(theme);

    }

    private void sendAMessage() {
        driver.findElement(By.xpath("//span[@class='ui-button-text']")).click();
    }

    private void switchLanguage(Language val) {
        String currentLanguage = driver.findElement(By.xpath("//span[@class='b-selink__link mail-Settings-Lang']")).getText();
        if (currentLanguage.equals(val.getLanguageName())) {
            LOGGER.info("You're on " + val.getLanguageName() + " now.");
            s = "noteDone";


        } else {
            driver.findElement(By.xpath("//span[@class='b-selink__link mail-Settings-Lang']")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(val.getPath())));
            driver.findElement(By.xpath(val.getPath())).click();
            s = "done";

        }
    }

    private void selectMessa2ges(String emailFrom) {
        List<WebElement> boxes = driver.findElements(By.xpath("//span[@class='_nb-checkbox-flag _nb-checkbox-normal-flag']"));
        List<WebElement> names = driver.findElements(By.xpath("//span[@class='mail-MessageSnippet-FromText']"));
        previously = driver.findElement(By.xpath("//span[@class='mail-NestedList-Item-Info-Link-Text']")).getText();

        previously = previously.replaceAll("[^0-9]+", "");
        prev = Integer.parseInt(previously);

        for (int i = 0; i < boxes.size(); i++) {
            System.out.println(names.get(i).getAttribute("title"));
            if (names.get(i).getAttribute("title").equals(emailFrom)) {
                boxes.get(i).click();
                del++;
            }
        }
        if (del == 0) {
            LOGGER.info("There no messages from: " + emailFrom);
        } else {
            LOGGER.info("Messages from:" + emailFrom + " were successfully selected");
        }

    }

    private void delete2Messages() {
        driver.findElement(By.xpath("//div[contains(@title , '(Delete)')]")).click();

    }

    private void sendMessageCheck() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@class='mail-Done-Title js-title-info']")));
        Assert.assertTrue(driver.findElement(By.xpath("//div[@class='mail-Done-Title js-title-info']")).isDisplayed());
    }

    private void checkDeleteLetters() {
        if (del == 0) {
            LOGGER.info("There're no messages to delete");
        } else {
            driver.navigate().refresh();
            wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//a[@class='mail-ComposeButton js-main-action-compose']")));
            String actual = driver.findElement(By.xpath("//span[@class='mail-NestedList-Item-Info-Link-Text']")).getText();
            int act = Integer.parseInt(actual);
            LOGGER.info("Number of your posts before: " + prev + "Number of your posts now: " + act);
            Assert.assertTrue(prev - del == act);
            LOGGER.info("Messages deleted successfully");
        }
    }

    private void checkSwitchLanguage(Language val) {

        if (s.equals("done")) {

            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@class='b-mail-dropdown__box__content']")));
            String currentLanguage1 = driver.findElement(By.xpath("//span[@class='b-selink__link mail-Settings-Lang']")).getText();
            Assert.assertEquals(currentLanguage1, val.getLanguageName());
            LOGGER.info("Now your language is: " + val.getLanguageName());
        } else {
            LOGGER.info("You're already on: " + val.getLanguageName());
        }
    }

    private void wrongEmailCheck(String emailAdress) {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@data-key='view=compose-field-to-error']")));
        String error = driver.findElement(By.xpath("//div[@data-key='view=compose-field-to-error']")).getText();
        Assert.assertTrue(error.contains(": " + emailAdress));
    }

    private void nullEmailCheck() {
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath("//div[@data-key='view=compose-field-to-error']")));
        String error = driver.findElement(By.xpath("//div[@data-key='view=compose-field-to-error']")).getText();
        Assert.assertTrue(error.contains("."));
    }

    @Test(groups = "MS-1")
    public void sendMessageWithEmail() {

        goToSendMessage();
        fillEmailAdress("maximkadocnikov@yandex.ru");
        sendAMessage();
        sendMessageCheck();

    }

    @Test(groups = "MS-2")
    public void sendMessageWrongEmail() {
        goToSendMessage();
        fillEmailAdress("asadas");
        sendAMessage();
        wrongEmailCheck("asadas");


    }

    @Test(groups = "MS-3")
    public void sendMessageWithoutEmail() {

        goToSendMessage();
        sendAMessage();
        nullEmailCheck();


    }

    @Test(groups = "MS-4")
    public void sendFullMessage() {

        goToSendMessage();
        fillEmailAdress("maximkadocnikov@gmail.ru");
        fillTheme("hello");
        sendAMessage();
        sendMessageCheck();
    }

    @Test(groups = "SW-1")
    public void switchLanguageToRus() {

        goToSettings();
        switchLanguage(Language.RUS);
        checkSwitchLanguage(Language.RUS);

    }

    @Test(groups = "SW-2")
    public void switchLanguageToEng() {

        goToSettings();
        switchLanguage(Language.ENG);
        checkSwitchLanguage(Language.ENG);
    }

    @Test(groups = "DL-1")
    public void selectMessa2ge() {
        selectMessa2ges("maximkadocnikov@yandex.ru");


    }

    @Test(groups = "DL-2")
    public void messagesDelete() {
        selectMessa2ges("maximkadocnikov@yandex.ru");
        delete2Messages();
        checkDeleteLetters();
    }

    public enum Language {
        RUS("//a[@data-params='lang=ru']", "Русский"),
        ENG("//a[@data-params='lang=en']", "English");
        private String xpath;
        private String languageName;

        Language(String xpath, String languageName) {
            this.xpath = xpath;
            this.languageName = languageName;
        }

        public String getPath() {
            return xpath;
        }

        public String getLanguageName() {

            return languageName;
        }
    }
}



