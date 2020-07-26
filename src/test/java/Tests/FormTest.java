package Tests;

import Data.DataHelper;
import Pages.MainPage;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import static com.codeborne.selenide.Selenide.open;

public class FormTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openSource() {
        open("http://localhost:8080");
    }

    @Test
    @DisplayName("Should show error message if card filled with characters")
    void shouldShowErrorMessageIfCardFilledWithCharacters () {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.getCharactersInsteadOfCardNumber();
        debitCard.fillForm(cardInfo);
        debitCard.cardNumberErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if card filled with short number")
    void shouldShowErrorMessageIfCardFilledWithShortNumber() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.getCardWithShortNumber();
        debitCard.fillForm(cardInfo);
        debitCard.cardNumberErrorCheck();
    }

    @Test
    @DisplayName("Should accept long card number")
    void shouldAcceptLongCardNumber() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.getCardWithLongNumber();
        debitCard.fillForm(cardInfo);
        debitCard.checkIfCardNumberAccepted();
    }

    @Test
    @DisplayName("Should show error message if card filled with symbols")
    void shouldShowErrorMessageIfCardFilledWithSymbols() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.getSymbolsInsteadOfCardNumber();
        debitCard.fillForm(cardInfo);
        debitCard.cardNumberErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if card is expired")
    void shouldShowErrorMessageIfCardIsExpired() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.getExpiredCardInfo();
        debitCard.fillForm(cardInfo);
        debitCard.cardDateErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if owner filled only with first name")
    void shouldShowErrorMessageIfOwnerFilledOnlyWithFirstName() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.getOnlyOwnerName();
        debitCard.fillForm(cardInfo);
        debitCard.ownerErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if owner filled with name in russian")
    void shouldShowErrorMessageIfOwnerFilledWithNameInRussian() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.getRussianOwnerName();
        debitCard.fillForm(cardInfo);
        debitCard.ownerErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if owner filled with number")
    void shouldShowErrorMessageIfOwnerFilledWithNumber() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.getNumberInsteadOfName();
        debitCard.fillForm(cardInfo);
        debitCard.ownerErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if owner filled with symbols")
    void shouldShowErrorMessageIfOwnerFilledWithSymbols() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.getSymbolsInsteadOfName();
        debitCard.fillForm(cardInfo);
        debitCard.ownerErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if owner filled with random characters")
    void shouldShowErrorMessageIfOwnerFilledWithRandomCharacters() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.getRandomCharactersInsteadOfName();
        debitCard.fillForm(cardInfo);
        debitCard.ownerErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if CVV filled with symbols")
    void shouldShowErrorMessageIfCVVFilledWithSymbols() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.getSymbolsInsteadOfCVV();
        debitCard.fillForm(cardInfo);
        debitCard.CVVErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if CVV filled with characters")
    void shouldShowErrorMessageIfCVVFilledWithCharacters() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.getCharactersInsteadOfCVV();
        debitCard.fillForm(cardInfo);
        debitCard.CVVErrorCheck();
    }

    @Test
    @DisplayName("CVV field should not accept more than three digits")
    void CVVFieldShouldNotAcceptMoreThanThreeDigits() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.getCVVLongerThanThree();
        debitCard.fillForm(cardInfo);
        debitCard.shouldGetThreeDigits();
    }

    @Test
    @DisplayName("Should show error message if CVV filled with 2 digits")
    void shouldShowErrorMessageIfCVVFilledWith2Digits() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.getCVVShorterThanThree();
        debitCard.fillForm(cardInfo);
        debitCard.CVVErrorCheck();
    }

    @Test
    @DisplayName("Should show error messages if empty payment form sent")
    void shouldShowErrorMessageIfEmptyPaymentFormSent() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        debitCard.sendEmptyForm();
    }

    @Test
    @DisplayName("Should show error messages if empty credit request form sent")
    void shouldShowErrorMessageIfEmptyCreditRequestFormSent() {
        val mainPage = new MainPage();
        val creditCard = mainPage.sendCreditRequest();
        creditCard.sendEmptyForm();
    }
}