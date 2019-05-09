package tests;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface MessagesTypes extends ExtendedWebElement {
    @FindBy("//a[contains(@class, 'ns-view-id-64')]")
    @Description("Входящие сообщения")
    ExtendedWebElement incomingMessages();
}
