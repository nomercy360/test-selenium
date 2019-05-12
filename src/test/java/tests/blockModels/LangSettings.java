package tests.blockModels;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface LangSettings extends ExtendedWebElement {
    @FindBy("//span[@class='b-selink__link mail-Settings-Lang']")
    @Description("Кнопка смены языка, так же показывает текущий язык")
    ExtendedWebElement changeLangButton();
}
