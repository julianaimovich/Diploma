import Data.DataHelper;
import Data.SQLHelper;
import Pages.MainPage;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.sql.SQLException;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestClass {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() throws SQLException {
        SelenideLogger.removeListener("allure");
        SQLHelper.cleanDB();
    }

    @BeforeEach
    public void openSource() {
        open("http://localhost:8080");
    }

    @Test
    @DisplayName("Payment card info can be sent and saved by database")
    void paymentCardInfoCanBeSentAndSavedByDatabase () throws SQLException {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.cardInfoSample();
        debitCard.fillForm(cardInfo);
        mainPage.checkIfTransactionWasDeclined();
        assertEquals(SQLHelper.getCreatedOrderStatus(), SQLHelper.getCreatedPaymentStatus());
    }

    @Test
    @DisplayName("Credit card info can be sent and saved by database")
    void creditCardInfoCanBeSentAndSavedByDatabase () throws SQLException {
        val mainPage = new MainPage();
        val creditCard = mainPage.sendCreditRequest();
        val cardInfo = DataHelper.cardInfoSample();
        creditCard.fillForm(cardInfo);
        mainPage.checkIfTransactionWasDeclined();
        assertEquals(SQLHelper.getCreatedOrderStatus(), SQLHelper.getCreatedRequestStatus());
    }

    @Test
    @DisplayName("Payment with first given card should be approved and saved by database")
    void paymentWithFirstGivenCardShouldBeApprovedAndSavedByDatabase () throws SQLException {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.firstGivenCard();
        debitCard.fillForm(cardInfo);
        mainPage.checkIfTransactionWasApproved();
        assertEquals(DataHelper.approvedStatus, SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("Payment with second given card should be declined and saved by database")
    void paymentWithSecondGivenCardShouldBeDeclinedAndSavedByDatabase () throws SQLException {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.secondGivenCard();
        debitCard.fillForm(cardInfo);
        mainPage.checkIfTransactionWasDeclined();
        assertEquals(DataHelper.declinedStatus, SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("Credit request with first given card should be approved and saved by database")
    void creditRequestWithFirstGivenCardShouldBeApprovedAndSavedByDatabase () throws SQLException {
        val mainPage = new MainPage();
        val creditCard = mainPage.sendCreditRequest();
        val cardInfo = DataHelper.firstGivenCard();
        creditCard.fillForm(cardInfo);
        mainPage.checkIfTransactionWasApproved();
        assertEquals(DataHelper.approvedStatus, SQLHelper.getRequestStatus());
    }

    @Test
    @DisplayName("Credit request with second given card should be declined and saved by database")
    void creditRequestWithSecondGivenCardShouldBeDeclinedAndSavedByDatabase () throws SQLException {
        val mainPage = new MainPage();
        val creditCard = mainPage.sendCreditRequest();
        val cardInfo = DataHelper.secondGivenCard();
        creditCard.fillForm(cardInfo);
        mainPage.checkIfTransactionWasDeclined();
        assertEquals(DataHelper.declinedStatus, SQLHelper.getRequestStatus());
        mainPage.checkIfTransactionWasDeclined();
    }

    @Test
    @DisplayName("Should show error message if card filled with characters")
    void shouldShowErrorMessageIfCardFilledWithCharacters () {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.charactersInsteadOfCardNumber();
        debitCard.fillForm(cardInfo);
        debitCard.cardNumberErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if card filled with short number")
    void shouldShowErrorMessageIfCardFilledWithShortNumber() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.cardWithShortNumber();
        debitCard.fillForm(cardInfo);
        debitCard.cardNumberErrorCheck();
    }

    @Test
    @DisplayName("Should accept long card number")
    void shouldAcceptLongCardNumber() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.cardWithLongNumber();
        debitCard.fillForm(cardInfo);
    }

    @Test
    @DisplayName("Should show error message if card filled with symbols")
    void shouldShowErrorMessageIfCardFilledWithSymbols() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.symbolsInsteadOfCardNumber();
        debitCard.fillForm(cardInfo);
        debitCard.cardNumberErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if card is expired")
    void shouldShowErrorMessageIfCardIsExpired() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.expiredCardInfo();
        debitCard.fillForm(cardInfo);
        debitCard.cardDateErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if owner filled only with first name")
    void shouldShowErrorMessageIfOwnerFilledOnlyWithFirstName() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.onlyOwnerName();
        debitCard.fillForm(cardInfo);
        debitCard.ownerErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if owner filled with name in russian")
    void shouldShowErrorMessageIfOwnerFilledWithNameInRussian() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.russianOwnerName();
        debitCard.fillForm(cardInfo);
        debitCard.ownerErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if owner filled with number")
    void shouldShowErrorMessageIfOwnerFilledWithNumber() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.numberInsteadOfName();
        debitCard.fillForm(cardInfo);
        debitCard.ownerErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if owner filled with symbols")
    void shouldShowErrorMessageIfOwnerFilledWithSymbols() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.symbolsInsteadOfName();
        debitCard.fillForm(cardInfo);
        debitCard.ownerErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if owner filled with random characters")
    void shouldShowErrorMessageIfOwnerFilledWithRandomCharacters() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.randomCharactersInsteadOfName();
        debitCard.fillForm(cardInfo);
        debitCard.ownerErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if CVV filled with symbols")
    void shouldShowErrorMessageIfCVVFilledWithSymbols() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.symbolsInsteadOfCVV();
        debitCard.fillForm(cardInfo);
        debitCard.CVVErrorCheck();
    }

    @Test
    @DisplayName("Should show error message if CVV filled with characters")
    void shouldShowErrorMessageIfCVVFilledWithCharacters() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.charactersInsteadOfCVV();
        debitCard.fillForm(cardInfo);
        debitCard.CVVErrorCheck();
    }

    @Test
    @DisplayName("CVV field should not accept more than three digits")
    void CVVFieldShouldNotAcceptMoreThanThreeDigits() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.CVVLongerThanThree();
        debitCard.fillForm(cardInfo);
        debitCard.shouldGetThreeDigits();
    }

    @Test
    @DisplayName("Should show error message if CVV filled with 2 digits")
    void shouldShowErrorMessageIfCVVFilledWith2Digits() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.CVVShorterThanThree();
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