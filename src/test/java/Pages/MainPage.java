package Pages;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private SelenideElement debitCardButton = $(byText("Купить"));
    private SelenideElement creditCardButton = $(byText("Купить в кредит"));
    private SelenideElement paymentFormName = $(byText("Оплата по карте"));
    private SelenideElement creditFormName = $(byText("Кредит по данным карты"));

    public DebitCard payWithDebitCard () {
        debitCardButton.click();
        paymentFormName.shouldBe(visible);
        return new DebitCard ();
    }

    public CreditCard sendCreditRequest () {
        creditCardButton.click();
        creditFormName.shouldBe(visible);
        return new CreditCard();
    }
}