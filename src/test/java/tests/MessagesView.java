package tests;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface MessagesView extends ExtendedWebElement {
    @FindBy(".//div[contains(@class, 'item-wrap')]")
    @Description("Все письма по отдельности")
    ExtendedWebElement allMessages();


}
