package tests.blockModels;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface MailComposeField extends ExtendedWebElement {
    @FindBy("//div[@name='to']")
    @Description("Поля для ввода адреса получателя")
    ExtendedWebElement recipientAddress();

    @FindBy("//button[@id='nb-16']")
    @Description("Кнопка отправки письма")
    ExtendedWebElement sendButton();

    @FindBy("//div[contains(@class, 'ns-view-compose-field-to-error')]")
    @Description("Сообщение об ошибки")
    ExtendedWebElement mailErrorMessage();

    @FindBy("//input[contains(@name, 'subj')]")
    @Description("Тема сообщения")
    ExtendedWebElement messageTheme();
}
