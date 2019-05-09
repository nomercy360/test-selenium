package tests;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface Authorization extends ExtendedWebElement {
    @FindBy("//a[contains(@class , 'login-enter')]")
    @Description("Кнопка авторизации")
    ExtendedWebElement authorizationButton();

}
