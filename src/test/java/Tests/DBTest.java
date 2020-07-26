package Tests;

import Data.DataHelper;
import Data.SQLHelper;
import Pages.MainPage;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;
import java.sql.SQLException;
import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class DBTest {

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
        val cardInfo = DataHelper.getCardInfoSample();
        debitCard.fillForm(cardInfo);
        mainPage.checkIfTransactionWasDeclined();
        assertEquals(SQLHelper.getCreatedOrderStatus(), SQLHelper.getCreatedPaymentStatus());
    }

    @Test
    @DisplayName("Credit card info can be sent and saved by database")
    void creditCardInfoCanBeSentAndSavedByDatabase () throws SQLException {
        val mainPage = new MainPage();
        val creditCard = mainPage.sendCreditRequest();
        val cardInfo = DataHelper.getCardInfoSample();
        creditCard.fillForm(cardInfo);
        mainPage.checkIfTransactionWasDeclined();
        assertEquals(SQLHelper.getCreatedOrderStatus(), SQLHelper.getCreatedRequestStatus());
    }

    @Test
    @DisplayName("Payment with first given card should be approved and saved by database")
    void paymentWithFirstGivenCardShouldBeApprovedAndSavedByDatabase () throws SQLException {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.getFirstGivenCard();
        debitCard.fillForm(cardInfo);
        mainPage.checkIfTransactionWasApproved();
        assertEquals(DataHelper.approvedStatus, SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("Payment with second given card should be declined and saved by database")
    void paymentWithSecondGivenCardShouldBeDeclinedAndSavedByDatabase () throws SQLException {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        val cardInfo = DataHelper.getSecondGivenCard();
        debitCard.fillForm(cardInfo);
        mainPage.checkIfTransactionWasDeclined();
        assertEquals(DataHelper.declinedStatus, SQLHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("Credit request with first given card should be approved and saved by database")
    void creditRequestWithFirstGivenCardShouldBeApprovedAndSavedByDatabase () throws SQLException {
        val mainPage = new MainPage();
        val creditCard = mainPage.sendCreditRequest();
        val cardInfo = DataHelper.getFirstGivenCard();
        creditCard.fillForm(cardInfo);
        mainPage.checkIfTransactionWasApproved();
        assertEquals(DataHelper.approvedStatus, SQLHelper.getRequestStatus());
    }

    @Test
    @DisplayName("Credit request with second given card should be declined and saved by database")
    void creditRequestWithSecondGivenCardShouldBeDeclinedAndSavedByDatabase () throws SQLException {
        val mainPage = new MainPage();
        val creditCard = mainPage.sendCreditRequest();
        val cardInfo = DataHelper.getSecondGivenCard();
        creditCard.fillForm(cardInfo);
        mainPage.checkIfTransactionWasDeclined();
        assertEquals(DataHelper.declinedStatus, SQLHelper.getRequestStatus());
        mainPage.checkIfTransactionWasDeclined();
    }
}
