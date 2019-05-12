package tests.blockModels;

import io.qameta.htmlelements.annotation.Description;
import io.qameta.htmlelements.annotation.FindBy;
import io.qameta.htmlelements.annotation.Param;
import io.qameta.htmlelements.element.ExtendedList;
import io.qameta.htmlelements.element.ExtendedWebElement;

public interface MessagesView extends ExtendedWebElement {
    @FindBy(".//div[contains(@class, 'item-wrap')]")
    @Description("Все письма по отдельности")
    ExtendedWebElement allMessages();

    @FindBy(".//span[@title='{{ adress }}']//../..//span[contains(@class,'nb-checkbox-normal')]")
    @Description("письма с выбранным адресом")
    ExtendedList<MessagesView> checkBoxes(@Param("adress") String adress);


}
