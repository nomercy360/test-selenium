package tests.blockModels;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface MailDonePage extends ExtendedWebElement {
    @FindBy("//div[@class='mail-Done-Redirect']")
    @Description("Сообщение успешной отправки сообщения")
    ExtendedWebElement messageDoneTitle();

}
