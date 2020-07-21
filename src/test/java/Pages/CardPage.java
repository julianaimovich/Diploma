package Pages;

import Data.DataHelper;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CardPage {
    private SelenideElement cardNumber = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[1]/span/span/span[2]/input"));
    private SelenideElement cardMonth = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[2]/input"));
    private SelenideElement cardYear = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[2]/input"));
    private SelenideElement cardOwner = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input"));
    private SelenideElement cardCVV = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[2]/span/span/span[2]/input"));
    private SelenideElement continueButton = $(byText("Продолжить"));
    private SelenideElement errorCardMessage = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[1]/span/span/span[3]"));
    private SelenideElement errorMonthMessage = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[3]"));
    private SelenideElement errorYearMessage = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[3]"));
    private SelenideElement errorOwnerMessage = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[3]"));
    private SelenideElement errorCVVMessage = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[2]/span/span/span[3]"));
    private SelenideElement errorExpiredCardMessage = $(byText("Истёк срок действия карты"));

    public void sendRequest () {
        cardNumber.setValue(DataHelper.getCardNumber());
        cardMonth.setValue(DataHelper.monthCalculating());
        cardYear.setValue(DataHelper.yearCalculating());
        cardOwner.setValue(DataHelper.cardOwner);
        cardCVV.setValue(DataHelper.getCVV());
        continueButton.click();
    }

    public void sendRequestWithFirstCard () {
        cardNumber.setValue(DataHelper.firstGivenCardNumber);
        cardMonth.setValue(DataHelper.monthCalculating());
        cardYear.setValue(DataHelper.yearCalculating());
        cardOwner.setValue(DataHelper.cardOwner);
        cardCVV.setValue(DataHelper.getCVV());
        continueButton.click();
    }

    public void sendRequestWithSecondCard () {
        cardNumber.setValue(DataHelper.secondGivenCardNumber);
        cardMonth.setValue(DataHelper.monthCalculating());
        cardYear.setValue(DataHelper.yearCalculating());
        cardOwner.setValue(DataHelper.cardOwner);
        cardCVV.setValue(DataHelper.getCVV());
        continueButton.click();
    }

    public void sendEmptyCreditRequest () {
        continueButton.click();
        errorCardMessage.shouldBe(visible);
        errorMonthMessage.shouldBe(visible);
        errorYearMessage.shouldBe(visible);
        errorOwnerMessage.shouldBe(visible);
        errorCVVMessage.shouldBe(visible);
    }

    public void makePayment () {
        cardNumber.setValue(DataHelper.getCardNumber());
        cardMonth.setValue(DataHelper.monthCalculating());
        cardYear.setValue(DataHelper.yearCalculating());
        cardOwner.setValue(DataHelper.cardOwner);
        cardCVV.setValue(DataHelper.getCVV());
        continueButton.click();
    }

    public void makePaymentWithFirstCard () {
        cardNumber.setValue(DataHelper.firstGivenCardNumber);
        cardMonth.setValue(DataHelper.monthCalculating());
        cardYear.setValue(DataHelper.yearCalculating());
        cardOwner.setValue(DataHelper.cardOwner);
        cardCVV.setValue(DataHelper.getCVV());
        continueButton.click();
    }

    public void makePaymentWithSecondCard () {
        cardNumber.setValue(DataHelper.secondGivenCardNumber);
        cardMonth.setValue(DataHelper.monthCalculating());
        cardYear.setValue(DataHelper.yearCalculating());
        cardOwner.setValue(DataHelper.cardOwner);
        cardCVV.setValue(DataHelper.getCVV());
        continueButton.click();
    }

    public void charactersInsteadOfNumber () {
        cardNumber.setValue(DataHelper.characters);
        cardMonth.setValue(DataHelper.monthCalculating());
        cardYear.setValue(DataHelper.yearCalculating());
        cardOwner.setValue(DataHelper.cardOwner);
        cardCVV.setValue(DataHelper.getCVV());
        continueButton.click();
        errorCardMessage.shouldBe(visible);
    }

    public void makePaymentWithShortCardNumber () {
        cardNumber.setValue(DataHelper.getShortCardNumber());
        cardMonth.setValue(DataHelper.monthCalculating());
        cardYear.setValue(DataHelper.yearCalculating());
        cardOwner.setValue(DataHelper.cardOwner);
        cardCVV.setValue(DataHelper.getCVV());
        continueButton.click();
        errorCardMessage.shouldBe(visible);
    }

    public void fillFormWithLongCardNumber () {
        cardNumber.setValue(DataHelper.cardNumberMaestro);
        cardMonth.setValue(DataHelper.monthCalculating());
        cardYear.setValue(DataHelper.yearCalculating());
        cardOwner.setValue(DataHelper.cardOwner);
        cardCVV.setValue(DataHelper.getCVV());
        assertEquals(DataHelper.cardNumberMaestro, cardNumber.getValue());
    }

    public void symbolsInsteadOfNumber () {
        cardNumber.setValue(DataHelper.symbols);
        cardMonth.setValue(DataHelper.monthCalculating());
        cardYear.setValue(DataHelper.yearCalculating());
        cardOwner.setValue(DataHelper.cardOwner);
        cardCVV.setValue(DataHelper.getCVV());
        continueButton.click();
        errorCardMessage.shouldBe(visible);
    }

    public void makePaymentWithExpiredCard () {
        cardNumber.setValue(DataHelper.getCardNumber());
        cardMonth.setValue(DataHelper.monthCalculating());
        cardYear.setValue(DataHelper.oldYearCalculating());
        cardOwner.setValue(DataHelper.cardOwner);
        cardCVV.setValue(DataHelper.getCVV());
        continueButton.click();
        errorExpiredCardMessage.shouldBe(visible);
    }

    public void fillOwnerFieldOnlyWithName () {
        cardNumber.setValue(DataHelper.getCardNumber());
        cardMonth.setValue(DataHelper.monthCalculating());
        cardYear.setValue(DataHelper.yearCalculating());
        cardOwner.setValue(DataHelper.cardOwnerName);
        cardCVV.setValue(DataHelper.getCVV());
        continueButton.click();
        errorOwnerMessage.shouldBe(visible);
    }

    public void fillOwnerFieldWithRussianName () {
        cardNumber.setValue(DataHelper.getCardNumber());
        cardMonth.setValue(DataHelper.monthCalculating());
        cardYear.setValue(DataHelper.yearCalculating());
        cardOwner.setValue(DataHelper.cardOwnerRussian);
        cardCVV.setValue(DataHelper.getCVV());
        continueButton.click();
        errorOwnerMessage.shouldBe(visible);
    }

    public void fillOwnerFieldWithNumber () {
        cardNumber.setValue(DataHelper.getCardNumber());
        cardMonth.setValue(DataHelper.monthCalculating());
        cardYear.setValue(DataHelper.yearCalculating());
        cardOwner.setValue(DataHelper.randomNumber);
        cardCVV.setValue(DataHelper.getCVV());
        continueButton.click();
        errorOwnerMessage.shouldBe(visible);
    }

    public void fillOwnerFieldWithSymbols () {
        cardNumber.setValue(DataHelper.getCardNumber());
        cardMonth.setValue(DataHelper.monthCalculating());
        cardYear.setValue(DataHelper.yearCalculating());
        cardOwner.setValue(DataHelper.symbols);
        cardCVV.setValue(DataHelper.getCVV());
        continueButton.click();
        errorOwnerMessage.shouldBe(visible);
    }

    public void fillOwnerFieldWithRandomCharacters () {
        cardNumber.setValue(DataHelper.getCardNumber());
        cardMonth.setValue(DataHelper.monthCalculating());
        cardYear.setValue(DataHelper.yearCalculating());
        cardOwner.setValue(DataHelper.characters);
        cardCVV.setValue(DataHelper.getCVV());
        continueButton.click();
        errorOwnerMessage.shouldBe(visible);
    }

    public void fillCVVWithSymbols () {
        cardNumber.setValue(DataHelper.getCardNumber());
        cardMonth.setValue(DataHelper.monthCalculating());
        cardYear.setValue(DataHelper.yearCalculating());
        cardOwner.setValue(DataHelper.cardOwner);
        cardCVV.setValue(DataHelper.symbols);
        continueButton.click();
        errorCVVMessage.shouldBe(visible);
    }

    public void fillCVVWithCharacters () {
        cardNumber.setValue(DataHelper.getCardNumber());
        cardMonth.setValue(DataHelper.monthCalculating());
        cardYear.setValue(DataHelper.yearCalculating());
        cardOwner.setValue(DataHelper.cardOwner);
        cardCVV.setValue(DataHelper.characters);
        continueButton.click();
        errorCVVMessage.shouldBe(visible);
    }

    public void fillCVVWithLongerNumber () {
        cardNumber.setValue(DataHelper.getCardNumber());
        cardMonth.setValue(DataHelper.monthCalculating());
        cardYear.setValue(DataHelper.yearCalculating());
        cardOwner.setValue(DataHelper.cardOwner);
        cardCVV.setValue(DataHelper.randomNumber);
        continueButton.click();
        Assertions.assertNotEquals(DataHelper.randomNumber, cardCVV.getValue());
    }

    public void fillCVVWith2Digits () {
        cardNumber.setValue(DataHelper.getCardNumber());
        cardMonth.setValue(DataHelper.monthCalculating());
        cardYear.setValue(DataHelper.yearCalculating());
        cardOwner.setValue(DataHelper.cardOwner);
        cardCVV.setValue("13");
        continueButton.click();
        errorCVVMessage.shouldBe(visible);
    }

    public void sendEmptyPaymentForm () {
        continueButton.click();
        errorCardMessage.shouldBe(visible);
        errorMonthMessage.shouldBe(visible);
        errorYearMessage.shouldBe(visible);
        errorOwnerMessage.shouldBe(visible);
        errorCVVMessage.shouldBe(visible);
    }
}
