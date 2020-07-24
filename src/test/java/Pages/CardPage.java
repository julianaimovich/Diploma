package Pages;

import Data.DataHelper;
import Data.DataHelper.CardInfo;
import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class CardPage {
    private SelenideElement cardNumberField = $(byXpath("//div[1]//span[2]/input"));
    private SelenideElement cardMonthField = $(byXpath("//div[2]//span[1]//span[2]/input"));
    private SelenideElement cardYearField = $(byXpath("//div[2]//span[2]//span[2]/input"));
    private SelenideElement cardOwnerField = $(byXpath("//div[3]//span[1]//span[2]/input"));
    private SelenideElement cardCVVField = $(byXpath("//div[3]//span[2]//span[2]/input"));
    private SelenideElement continueButton = $(byText("Продолжить"));
    private SelenideElement errorCardMessage = $(byXpath("//div[1]//span[3]"));
    private SelenideElement errorMonthMessage = $(byXpath("//div[2]//span[1]//span[3]"));
    private SelenideElement errorYearMessage = $(byXpath("//div[2]//span[2]//span[3]"));
    private SelenideElement errorOwnerMessage = $(byXpath("//div[3]//span[1]//span[3]"));
    private SelenideElement errorCVVMessage = $(byXpath("//div[3]//span[2]//span[3]"));
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

    public void sendEmptyForm () {
        continueButton.click();
        errorCardMessage.shouldBe(visible);
        errorMonthMessage.shouldBe(visible);
        errorYearMessage.shouldBe(visible);
        errorOwnerMessage.shouldBe(visible);
        errorCVVMessage.shouldBe(visible);
    }
}
