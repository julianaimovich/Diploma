package Pages;

import Data.DataHelper;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.byXpath;
import static com.codeborne.selenide.Selenide.$;

public class CreditCard {
    private SelenideElement creditCardNumber = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[1]/span/span/span[2]/input"));
    private SelenideElement creditCardMonth = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[2]/input"));
    private SelenideElement creditCardYear = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[2]/input"));
    private SelenideElement creditCardOwner = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[2]/input"));
    private SelenideElement creditCardCVV = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[2]/span/span/span[2]/input"));
    private SelenideElement requestCreditButton = $(byText("Продолжить"));
    private SelenideElement errorCardMessage = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[1]/span/span/span[3]"));
    private SelenideElement errorMonthMessage = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[1]/span/span/span[3]"));
    private SelenideElement errorYearMessage = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[2]/span/span[2]/span/span/span[3]"));
    private SelenideElement errorOwnerMessage = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[1]/span/span/span[3]"));
    private SelenideElement errorCVVMessage = $(byXpath("//*[@id=\"root\"]/div/form/fieldset/div[3]/span/span[2]/span/span/span[3]"));

    public void sendRequest () {
        creditCardNumber.setValue(DataHelper.getCardNumber());
        creditCardMonth.setValue(DataHelper.monthCalculating());
        creditCardYear.setValue(DataHelper.yearCalculating());
        creditCardOwner.setValue(DataHelper.cardOwner);
        creditCardCVV.setValue(DataHelper.getCVV());
        requestCreditButton.click();
    }

    public void sendRequestWithFirstCard () {
        creditCardNumber.setValue(DataHelper.firstGivenCardNumber);
        creditCardMonth.setValue(DataHelper.monthCalculating());
        creditCardYear.setValue(DataHelper.yearCalculating());
        creditCardOwner.setValue(DataHelper.cardOwner);
        creditCardCVV.setValue(DataHelper.getCVV());
        requestCreditButton.click();
    }

    public void sendRequestWithSecondCard () {
        creditCardNumber.setValue(DataHelper.secondGivenCardNumber);
        creditCardMonth.setValue(DataHelper.monthCalculating());
        creditCardYear.setValue(DataHelper.yearCalculating());
        creditCardOwner.setValue(DataHelper.cardOwner);
        creditCardCVV.setValue(DataHelper.getCVV());
        requestCreditButton.click();
    }

    public void sendEmptyCreditRequest () {
        requestCreditButton.click();
        errorCardMessage.shouldBe(visible);
        errorMonthMessage.shouldBe(visible);
        errorYearMessage.shouldBe(visible);
        errorOwnerMessage.shouldBe(visible);
        errorCVVMessage.shouldBe(visible);
    }
}