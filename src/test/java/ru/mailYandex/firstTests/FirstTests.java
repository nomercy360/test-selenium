package ru.mailYandex.firstTests;
import org.junit.Test;
import org.openqa.selenium.support.PageFactory;
import ru.mailYandex.signIn.WebDriverSettings;



public class FirstTests extends WebDriverSettings {


    @Test
    public void sendMessageWithoutAny() {
        SendMessagePage sendMessagePage = PageFactory.initElements(driver, SendMessagePage.class);
        sendMessagePage.open();
        sendMessagePage.sendMessage();
        sendMessagePage.failureNoneEmailCheck();


    }

    @Test
    public void sendMessageWithoutText() {
        SendMessagePage sendMessagePage = PageFactory.initElements(driver, SendMessagePage.class);
        sendMessagePage.open();
        sendMessagePage.fillSender();
        sendMessagePage.sendMessage();
        sendMessagePage.successCheck();

    }

    @Test
    public void sendMessageWithTheme() {
        SendMessagePage sendMessagePage = PageFactory.initElements(driver, SendMessagePage.class);
        sendMessagePage.open();
        sendMessagePage.fillTheme();
        sendMessagePage.sendMessage();
        sendMessagePage.failureNoneEmailCheck();

    }
    @Test
    public void sendMessageWrongEmail () {
        SendMessagePage sendMessagePage = PageFactory.initElements(driver, SendMessagePage.class);
        sendMessagePage.open();
        sendMessagePage.fillWrongSender();
        sendMessagePage.failureWrongEmailCheck();

    }

    @Test
    public void sendFullMessage() {
        SendMessagePage sendMessagePage = PageFactory.initElements(driver, SendMessagePage.class);
        sendMessagePage.open();
        sendMessagePage.fillSender();
        sendMessagePage.fillTheme();
        sendMessagePage.sendMessage();
        sendMessagePage.successCheck();


    }


    @Test
    public void messageSelection() {
        ActionsWithMessages actionsWithMessages = PageFactory.initElements(driver, ActionsWithMessages.class);
        actionsWithMessages.select();


    }

    @Test
    public void deleteMessages() throws InterruptedException {

        ActionsWithMessages actionsWithMessages = PageFactory.initElements(driver, ActionsWithMessages.class);
        actionsWithMessages.select();
        actionsWithMessages.delete();
        actionsWithMessages.successCheck();




    }



    @Test
    public void switchLanguage() {

        LanguageChange languageChange = PageFactory.initElements(driver, LanguageChange.class);
        languageChange.enteringSetting();
        languageChange.switchLanguage(Language.RUS);
        languageChange.switchLanguageCheck(Language.RUS);




    }

}





