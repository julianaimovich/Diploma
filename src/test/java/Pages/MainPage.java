package Pages;

import com.codeborne.selenide.SelenideElement;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;

public class MainPage {
    private SelenideElement debitCardButton = $(byText("Купить"));
    private SelenideElement creditCardButton = $(byText("Купить в кредит"));
    private SelenideElement paymentFormName = $(byText("Оплата по карте"));
    private SelenideElement creditFormName = $(byText("Кредит по данным карты"));
    private SelenideElement declineMessage = $(withText("Ошибка"));
    private SelenideElement approveMessage = $(withText("Успешно"));

    public CardPage payWithDebitCard () {
        debitCardButton.click();
        paymentFormName.shouldBe(visible);
        return new CardPage ();
    }

    public CardPage sendCreditRequest () {
        creditCardButton.click();
        creditFormName.shouldBe(visible);
        return new CardPage();
    }

    public void checkIfTransactionWasApproved () {
        approveMessage.waitUntil(visible, 10000);
    }

    public void checkIfTransactionWasDeclined () {
        declineMessage.waitUntil(visible,10000);
    }
}