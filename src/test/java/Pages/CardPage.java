package Pages;

import Data.DataHelper;
import Data.DataHelper.CardInfo;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CardPage {
    private SelenideElement cardNumberField = $(byText("Номер карты")).parent().$(".input__control");
    private SelenideElement cardMonthField = $(byText("Месяц")).parent().$(".input__control");
    private SelenideElement cardYearField = $(byText("Год")).parent().$(".input__control");
    private SelenideElement cardOwnerField = $(byText("Владелец")).parent().$(".input__control");
    private SelenideElement cardCVVField = $(byText("CVC/CVV")).parent().$(".input__control");
    private SelenideElement continueButton = $(byText("Продолжить"));
    private SelenideElement errorCardMessage = $(byText("Номер карты")).parent().$(".input__sub");
    private SelenideElement errorMonthMessage = $(byText("Месяц")).parent().$(".input__sub");
    private SelenideElement errorYearMessage = $(byText("Год")).parent().$(".input__sub");
    private SelenideElement errorOwnerMessage = $(byText("Владелец")).parent().$(".input__sub");
    private SelenideElement errorCVVMessage = $(byText("CVC/CVV")).parent().$(".input__sub");
    private SelenideElement errorExpiredCardMessage = $(byText("Истёк срок действия карты"));

    public void fillForm(CardInfo cardInfo) {
        cardNumberField.setValue(cardInfo.getCardNumber());
        cardMonthField.setValue(cardInfo.getCardMonth());
        cardYearField.setValue(cardInfo.getCardYear());
        cardOwnerField.setValue(cardInfo.getCardOwner());
        cardCVVField.setValue(cardInfo.getCardCVV());
        continueButton.click();
}

    public void cardNumberErrorCheck () {
        errorCardMessage.shouldBe(visible);
    }

    public void cardDateErrorCheck () {
        errorExpiredCardMessage.shouldBe(visible);
    }

    public void ownerErrorCheck () {
        errorOwnerMessage.shouldBe(visible);
    }

    public void CVVErrorCheck () {
        errorCVVMessage.shouldBe(visible);
    }

    public void shouldGetThreeDigits () {
        assertNotEquals(DataHelper.randomNumber, cardCVVField.getValue());
    }

    public void checkIfCardNumberAccepted () {
        assertEquals(DataHelper.cardNumberMaestro, cardNumberField.getValue());
    }

    public void sendEmptyForm () {
        continueButton.click();
        errorCardMessage.shouldBe(visible);
        errorMonthMessage.shouldBe(visible);
        errorYearMessage.shouldBe(visible);
        errorOwnerMessage.shouldBe(visible);
        errorCVVMessage.shouldBe(visible);
    }
}
