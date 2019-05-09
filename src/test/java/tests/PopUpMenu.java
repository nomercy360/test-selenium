package tests;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface PopUpMenu extends ExtendedWebElement {
    @FindBy("//span[@class='settings-popup-title-content']")
    @Description("Переход ко всем найтрокам")
    ExtendedWebElement allSettings();
}
