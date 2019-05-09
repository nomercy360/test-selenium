package tests;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface ComposeButtonHolder extends ExtendedWebElement {
    @FindBy("//span[@class='mail-ComposeButton-Text']")
    @Description("Кнопка написать письмо")
    ExtendedWebElement composeButton();
}
