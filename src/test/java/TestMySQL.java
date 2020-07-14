import Data.DataHelper;
import Data.MySQLHelper;
import Pages.MainPage;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import lombok.val;
import org.junit.jupiter.api.*;

import java.sql.SQLException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMySQL {

    private SelenideElement declineMessage = $(withText("Ошибка"));
    private SelenideElement approveMessage = $(withText("Успешно"));

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @AfterAll
    static void cleaningMySQL() throws SQLException {
        MySQLHelper.cleanDBMySQL();
    }

    @BeforeEach
    public void openSource() {
        open("http://localhost:8080");
    }

    @Test
    @DisplayName("Payment card info can be sent and saved by MySQL")
    void paymentCardInfoCanBeSentAndSavedByMySQL() throws SQLException {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        debitCard.makePayment();
        declineMessage.waitUntil(visible, 10000);
        assertEquals(MySQLHelper.getCreatedOrderStatusMySQL(), MySQLHelper.getCreatedPaymentStatusMySQL());
    }

    @Test
    @ DisplayName("Credit card info can be sent and saved by MySQL")
    void creditCardInfoCanBeSentAndSavedByMySQL () throws SQLException {
        val mainPage = new MainPage();
        val creditCard = mainPage.sendCreditRequest();
        creditCard.sendRequest();
        declineMessage.waitUntil(visible,10000);
        assertEquals(MySQLHelper.getCreatedOrderStatusMySQL(), MySQLHelper.getCreatedRequestStatusMySQL());
    }

    @Test
    @DisplayName("Payment with first given card should be approved and saved by MySQL")
    void paymentWithFirstGivenCardShouldBeApprovedAndSavedByMySQL () throws SQLException {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        debitCard.makePaymentWithFirstCard();
        approveMessage.waitUntil(visible,10000);
        assertEquals(DataHelper.approvedStatus, MySQLHelper.getPaymentStatusMySQL());
    }

    @Test
    @DisplayName("Payment with second given card should be declined and saved by MySQL")
    void paymentWithSecondGivenCardShouldBeDeclinedAndSavedByMySQL () throws SQLException {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        debitCard.makePaymentWithSecondCard();
        sleep(10000);
        assertEquals(DataHelper.declinedStatus, MySQLHelper.getPaymentStatusMySQL());
        declineMessage.shouldBe(visible);
    }
}
