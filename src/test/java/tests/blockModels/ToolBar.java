package tests.blockModels;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface ToolBar extends ExtendedWebElement {
    @FindBy("//div[contains(@class, 'button-delete')]")
    @Description("Кнопка удаления")
    ExtendedWebElement deleteButton();
}
