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
import static com.codeborne.selenide.Selenide.sleep;
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
        val card = mainPage.payWithDebitCard();
        card.makePayment();
        mainPage.checkIfTransactionWasDeclined();
        assertEquals(SQLHelper.getCreatedOrderStatus(), SQLHelper.getCreatedPaymentStatus());
    }

    @Test
    @DisplayName("Credit card info can be sent and saved by database")
    void creditCardInfoCanBeSentAndSavedByDatabase () throws SQLException {
        val mainPage = new MainPage();
        val card = mainPage.sendCreditRequest();
        card.sendRequest();
        mainPage.checkIfTransactionWasDeclined();
        assertEquals(SQLHelper.getCreatedOrderStatus(), SQLHelper.getCreatedRequestStatus());
    }

    @Test
    @DisplayName("Payment with first given card should be approved and saved by database")
    void paymentWithFirstGivenCardShouldBeApprovedAndSavedByDatabase () throws SQLException {
        val mainPage = new MainPage();
        val card = mainPage.payWithDebitCard();
        card.makePaymentWithFirstCard();
        mainPage.checkIfTransactionWasApproved();
        assertEquals(DataHelper.approvedStatus, SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("Payment with second given card should be declined and saved by database")
    void paymentWithSecondGivenCardShouldBeDeclinedAndSavedByDatabase () throws SQLException {
        val mainPage = new MainPage();
        val card = mainPage.payWithDebitCard();
        card.makePaymentWithSecondCard();
        mainPage.checkIfTransactionWasDeclined();
        assertEquals(DataHelper.declinedStatus, SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("Credit request with first given card should be approved and saved by database")
    void creditRequestWithFirstGivenCardShouldBeApprovedAndSavedByDatabase () throws SQLException {
        val mainPage = new MainPage();
        val card = mainPage.sendCreditRequest();
        card.sendRequestWithFirstCard();
        mainPage.checkIfTransactionWasApproved();
        assertEquals(DataHelper.approvedStatus, SQLHelper.getRequestStatus());
    }

    @Test
    @DisplayName("Credit request with second given card should be declined and saved by database")
    void creditRequestWithSecondGivenCardShouldBeDeclinedAndSavedByDatabase () throws SQLException {
        val mainPage = new MainPage();
        val creditCard = mainPage.sendCreditRequest();
        creditCard.sendRequestWithSecondCard();
        sleep(10000);
        assertEquals(DataHelper.declinedStatus, SQLHelper.getRequestStatus());
        mainPage.checkIfTransactionWasDeclined();
    }

    @Test
    @DisplayName("Should show error message if card filled with characters")
    void shouldShowErrorMessageIfCardFilledWithCharacters () {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        debitCard.charactersInsteadOfNumber();
    }

    @Test
    @DisplayName("Should show error message if card filled with short number")
    void shouldShowErrorMessageIfCardFilledWithShortNumber() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        debitCard.makePaymentWithShortCardNumber();
    }

    @Test
    @DisplayName("Should accept long card number")
    void shouldAcceptLongCardNumber() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        debitCard.fillFormWithLongCardNumber();
    }

    @Test
    @DisplayName("Should show error message if card filled with symbols")
    void shouldShowErrorMessageIfCardFilledWithSymbols() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        debitCard.symbolsInsteadOfNumber();
    }

    @Test
    @DisplayName("Should show error message if card is expired")
    void shouldShowErrorMessageIfCardIsExpired() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        debitCard.makePaymentWithExpiredCard();
    }

    @Test
    @DisplayName("Should show error message if owner filled only with first name")
    void shouldShowErrorMessageIfOwnerFilledOnlyWithFirstName() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        debitCard.fillOwnerFieldOnlyWithName();
    }

    @Test
    @DisplayName("Should show error message if owner filled with name in russian")
    void shouldShowErrorMessageIfOwnerFilledWithNameInRussian() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        debitCard.fillOwnerFieldWithRussianName();
    }

    @Test
    @DisplayName("Should show error message if owner filled with number")
    void shouldShowErrorMessageIfOwnerFilledWithNumber() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        debitCard.fillOwnerFieldWithNumber();
    }

    @Test
    @DisplayName("Should show error message if owner filled with symbols")
    void shouldShowErrorMessageIfOwnerFilledWithSymbols() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        debitCard.fillOwnerFieldWithSymbols();
    }

    @Test
    @DisplayName("Should show error message if owner filled with random characters")
    void shouldShowErrorMessageIfOwnerFilledWithRandomCharacters() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        debitCard.fillOwnerFieldWithRandomCharacters();
    }

    @Test
    @DisplayName("Should show error message if CVV filled with symbols")
    void shouldShowErrorMessageIfCVVFilledWithSymbols() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        debitCard.fillCVVWithSymbols();
    }

    @Test
    @DisplayName("Should show error message if CVV filled with characters")
    void shouldShowErrorMessageIfCVVFilledWithCharacters() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        debitCard.fillCVVWithCharacters();
    }

    @Test
    @DisplayName("Should show error message if CVV filled with longer number")
    void shouldShowErrorMessageIfCVVFilledWithLongerNumber() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        debitCard.fillCVVWithLongerNumber();
    }

    @Test
    @DisplayName("Should show error message if CVV filled with 2 digits")
    void shouldShowErrorMessageIfCVVFilledWith2Digits() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        debitCard.fillCVVWith2Digits();
    }

    @Test
    @DisplayName("Should show error messages if empty payment form sent")
    void shouldShowErrorMessageIfEmptyPaymentFormSent() {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        debitCard.sendEmptyPaymentForm();
    }

    @Test
    @DisplayName("Should show error messages if empty credit request form sent")
    void shouldShowErrorMessageIfEmptyCreditRequestFormSent() {
        val mainPage = new MainPage();
        val creditCard = mainPage.sendCreditRequest();
        creditCard.sendEmptyCreditRequest();
    }
}
