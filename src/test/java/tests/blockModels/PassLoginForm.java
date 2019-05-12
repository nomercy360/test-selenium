package tests.blockModels;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface PassLoginForm extends ExtendedWebElement {
    @FindBy("//input[@id='passp-field-login']")
    @Description("Поле ввода логина")
    ExtendedWebElement loginField();

    @FindBy("//input[@id='passp-field-passwd']")
    @Description("Поле ввода пароля")
    ExtendedWebElement passField();

    @FindBy(".//button[contains(@class, 'passp-form-button')]")
    @Description("Кнопка Войти")
    ExtendedWebElement logInButton();


}
