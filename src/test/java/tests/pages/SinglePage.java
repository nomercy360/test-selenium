package tests.pages;


import io.qameta.htmlelements.WebPage;
import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.extension.page.BaseUrl;
import tests.blockModels.*;

@BaseUrl("https://yandex.ru/")
public interface SinglePage extends WebPage {

    @FindBy("//div[@class='desk-notif-card__card']")
    @Description("Блок входа")
    Authorization authorization();

    @FindBy("//div[@class='passp-auth']")
    @Description("Блок ввода логина и пароля")
    PassLoginForm passLoginForm();

    @FindBy("//div[contains(@class, 'Pane-Wrap')]")
    @Description("Блок отправки и обновления сообщений")
    MessagesView messagesView();

    @FindBy("//div[contains(@class, 'ns-view-toolbar-buttons')]")
    @Description("Панель инструментов")
    ToolBar toolbar();

    @FindBy("//div[contains(@class, 'mail-ComposeButton-Holder')]")
    @Description("Панель написания письма")
    ComposeButtonHolder composeButtonHolder();

    @FindBy("//div[contains(@class, 'ns-view-head-settings-controls')]")
    @Description("Настройки управления")
    SettingsControl settingControl();

    @FindBy("//div[@aria-describedby='settings-dropdown']")
    @Description("Раскрывающееся меню настроек")
    PopUpMenu popUpmenu();

    @FindBy("//div[@class='b-mail-dropdown__box__content']")
    @Description("Список доступных языков")
    LangDropDownBox langDropDownBox();

    @FindBy("//span[@class='b-selink ns-action']")
    @Description("Меню выбора языка")
    LangSettings langSettings();

    @FindBy("//div[contains(@class, 'ns-view-folders-box')]")
    @Description("Блок сообщений")
    MessagesTypes messagesTypes();

    @FindBy("//div[@class='mail-Compose-Fields']")
    @Description("Поле отправки сообщения")
    MailComposeField mailComposeField();

    @FindBy("//div[contains(@class, 'ns-view-done ns-view')]")
    @Description("страница после отправки")
    MailDonePage mailDonePage();
}
