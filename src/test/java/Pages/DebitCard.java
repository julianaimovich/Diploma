package Pages;

import Data.DataHelper;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Assertions;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class DebitCard {
    private SelenideElement debitCardNumber = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[1]/span/span/span[2]/input"));
    private SelenideElement debitCardMonth = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[2]/input"));
    private SelenideElement debitCardYear = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[2]/input"));
    private SelenideElement debitCardOwner = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input"));
    private SelenideElement debitCardCVV = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[2]/span/span/span[2]/input"));
    private SelenideElement sendPaymentButton = $(byText("Продолжить"));
    private SelenideElement errorCardMessage = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[1]/span/span/span[3]"));
    private SelenideElement errorMonthMessage = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[3]"));
    private SelenideElement errorYearMessage = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[3]"));
    private SelenideElement errorOwnerMessage = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[3]"));
    private SelenideElement errorCVVMessage = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[2]/span/span/span[3]"));
    private SelenideElement errorExpiredCardMessage = $(byText("Истёк срок действия карты"));

    public void makePayment () {
        debitCardNumber.setValue(DataHelper.getCardNumber());
        debitCardMonth.setValue(DataHelper.monthCalculating());
        debitCardYear.setValue(DataHelper.yearCalculating());
        debitCardOwner.setValue(DataHelper.cardOwner);
        debitCardCVV.setValue(DataHelper.getCVV());
        sendPaymentButton.click();
    }

    public void makePaymentWithFirstCard () {
        debitCardNumber.setValue(DataHelper.firstGivenCardNumber);
        debitCardMonth.setValue(DataHelper.monthCalculating());
        debitCardYear.setValue(DataHelper.yearCalculating());
        debitCardOwner.setValue(DataHelper.cardOwner);
        debitCardCVV.setValue(DataHelper.getCVV());
        sendPaymentButton.click();
    }

    public void makePaymentWithSecondCard () {
        debitCardNumber.setValue(DataHelper.secondGivenCardNumber);
        debitCardMonth.setValue(DataHelper.monthCalculating());
        debitCardYear.setValue(DataHelper.yearCalculating());
        debitCardOwner.setValue(DataHelper.cardOwner);
        debitCardCVV.setValue(DataHelper.getCVV());
        sendPaymentButton.click();
    }

    public void charactersInsteadOfNumber () {
        debitCardNumber.setValue(DataHelper.characters);
        debitCardMonth.setValue(DataHelper.monthCalculating());
        debitCardYear.setValue(DataHelper.yearCalculating());
        debitCardOwner.setValue(DataHelper.cardOwner);
        debitCardCVV.setValue(DataHelper.getCVV());
        sendPaymentButton.click();
        errorCardMessage.shouldBe(visible);
    }

    public void makePaymentWithShortCardNumber () {
        debitCardNumber.setValue(DataHelper.getShortCardNumber());
        debitCardMonth.setValue(DataHelper.monthCalculating());
        debitCardYear.setValue(DataHelper.yearCalculating());
        debitCardOwner.setValue(DataHelper.cardOwner);
        debitCardCVV.setValue(DataHelper.getCVV());
        sendPaymentButton.click();
        errorCardMessage.shouldBe(visible);
    }

    public void fillFormWithLongCardNumber () {
        debitCardNumber.setValue(DataHelper.cardNumberMaestro);
        debitCardMonth.setValue(DataHelper.monthCalculating());
        debitCardYear.setValue(DataHelper.yearCalculating());
        debitCardOwner.setValue(DataHelper.cardOwner);
        debitCardCVV.setValue(DataHelper.getCVV());
        Assertions.assertEquals(DataHelper.cardNumberMaestro, debitCardNumber.getValue());
    }

    public void symbolsInsteadOfNumber () {
        debitCardNumber.setValue(DataHelper.symbols);
        debitCardMonth.setValue(DataHelper.monthCalculating());
        debitCardYear.setValue(DataHelper.yearCalculating());
        debitCardOwner.setValue(DataHelper.cardOwner);
        debitCardCVV.setValue(DataHelper.getCVV());
        sendPaymentButton.click();
        errorCardMessage.shouldBe(visible);
    }

    public void makePaymentWithExpiredCard () {
        debitCardNumber.setValue(DataHelper.getCardNumber());
        debitCardMonth.setValue(DataHelper.monthCalculating());
        debitCardYear.setValue(DataHelper.oldYearCalculating());
        debitCardOwner.setValue(DataHelper.cardOwner);
        debitCardCVV.setValue(DataHelper.getCVV());
        sendPaymentButton.click();
        errorExpiredCardMessage.shouldBe(visible);
    }

    public void fillOwnerFieldOnlyWithName () {
        debitCardNumber.setValue(DataHelper.getCardNumber());
        debitCardMonth.setValue(DataHelper.monthCalculating());
        debitCardYear.setValue(DataHelper.yearCalculating());
        debitCardOwner.setValue(DataHelper.cardOwnerName);
        debitCardCVV.setValue(DataHelper.getCVV());
        sendPaymentButton.click();
        errorOwnerMessage.shouldBe(visible);
    }

    public void fillOwnerFieldWithRussianName () {
        debitCardNumber.setValue(DataHelper.getCardNumber());
        debitCardMonth.setValue(DataHelper.monthCalculating());
        debitCardYear.setValue(DataHelper.yearCalculating());
        debitCardOwner.setValue(DataHelper.cardOwnerRussian);
        debitCardCVV.setValue(DataHelper.getCVV());
        sendPaymentButton.click();
        errorOwnerMessage.shouldBe(visible);
    }

    public void fillOwnerFieldWithNumber () {
        debitCardNumber.setValue(DataHelper.getCardNumber());
        debitCardMonth.setValue(DataHelper.monthCalculating());
        debitCardYear.setValue(DataHelper.yearCalculating());
        debitCardOwner.setValue(DataHelper.randomNumber);
        debitCardCVV.setValue(DataHelper.getCVV());
        sendPaymentButton.click();
        errorOwnerMessage.shouldBe(visible);
    }

    public void fillOwnerFieldWithSymbols () {
        debitCardNumber.setValue(DataHelper.getCardNumber());
        debitCardMonth.setValue(DataHelper.monthCalculating());
        debitCardYear.setValue(DataHelper.yearCalculating());
        debitCardOwner.setValue(DataHelper.symbols);
        debitCardCVV.setValue(DataHelper.getCVV());
        sendPaymentButton.click();
        errorOwnerMessage.shouldBe(visible);
    }

    public void fillOwnerFieldWithRandomCharacters () {
        debitCardNumber.setValue(DataHelper.getCardNumber());
        debitCardMonth.setValue(DataHelper.monthCalculating());
        debitCardYear.setValue(DataHelper.yearCalculating());
        debitCardOwner.setValue(DataHelper.characters);
        debitCardCVV.setValue(DataHelper.getCVV());
        sendPaymentButton.click();
        errorOwnerMessage.shouldBe(visible);
    }

    public void fillCVVWithSymbols () {
        debitCardNumber.setValue(DataHelper.getCardNumber());
        debitCardMonth.setValue(DataHelper.monthCalculating());
        debitCardYear.setValue(DataHelper.yearCalculating());
        debitCardOwner.setValue(DataHelper.cardOwner);
        debitCardCVV.setValue(DataHelper.symbols);
        sendPaymentButton.click();
        errorCVVMessage.shouldBe(visible);
    }

    public void fillCVVWithCharacters () {
        debitCardNumber.setValue(DataHelper.getCardNumber());
        debitCardMonth.setValue(DataHelper.monthCalculating());
        debitCardYear.setValue(DataHelper.yearCalculating());
        debitCardOwner.setValue(DataHelper.cardOwner);
        debitCardCVV.setValue(DataHelper.characters);
        sendPaymentButton.click();
        errorCVVMessage.shouldBe(visible);
    }

    public void fillCVVWithLongerNumber () {
        debitCardNumber.setValue(DataHelper.getCardNumber());
        debitCardMonth.setValue(DataHelper.monthCalculating());
        debitCardYear.setValue(DataHelper.yearCalculating());
        debitCardOwner.setValue(DataHelper.cardOwner);
        debitCardCVV.setValue(DataHelper.randomNumber);
        sendPaymentButton.click();
        Assertions.assertNotEquals(DataHelper.randomNumber, debitCardCVV.getValue());
    }

    public void fillCVVWith2Digits () {
        debitCardNumber.setValue(DataHelper.getCardNumber());
        debitCardMonth.setValue(DataHelper.monthCalculating());
        debitCardYear.setValue(DataHelper.yearCalculating());
        debitCardOwner.setValue(DataHelper.cardOwner);
        debitCardCVV.setValue("13");
        sendPaymentButton.click();
        errorCVVMessage.shouldBe(visible);
    }

    public void sendEmptyPaymentForm () {
        sendPaymentButton.click();
        errorCardMessage.shouldBe(visible);
        errorMonthMessage.shouldBe(visible);
        errorYearMessage.shouldBe(visible);
        errorOwnerMessage.shouldBe(visible);
        errorCVVMessage.shouldBe(visible);
    }
}