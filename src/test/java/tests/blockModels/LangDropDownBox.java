package tests.blockModels;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.annotation.Param;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface LangDropDownBox extends ExtendedWebElement {
    @FindBy("{{ val }}")
    @Description("Кнопка выбранного языка")
    ExtendedWebElement selectLang(@Param("val") String val);
}
