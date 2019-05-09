package tests;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface SettingsControl extends ExtendedWebElement {
    @FindBy("//a[@id='nb-3']")
    @Description("Переход в меню настроек")
    ExtendedWebElement jumpToPopUpMenu();
}
