package tests;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface LangDropDownBox extends ExtendedWebElement {
    @FindBy("//a[@data-params='lang={{lang}}']")
    @Description("Кнопка выбранного языка")
    ExtendedWebElement selectLang();
}
