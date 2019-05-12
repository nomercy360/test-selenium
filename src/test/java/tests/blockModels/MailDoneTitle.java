package tests.blockModels;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface MailDoneTitle extends ExtendedWebElement {
    @FindBy("//div[@class='mail-Done-Title js-title-info']")
    @Description("Сообщение успешной отправки сообщения")
    ExtendedWebElement messageDoneTitle();

}
