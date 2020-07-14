import Data.DataHelper;
import Data.PsqlHelper;
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

public class TestPostgreSQL {
    private SelenideElement declineMessage = $(withText("Ошибка"));
    private SelenideElement approveMessage = $(withText("Успешно"));

    @BeforeAll
    static void setUpAll () {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll () {
        SelenideLogger.removeListener("allure");
    }

    @AfterAll
    static void cleaningPsql () throws SQLException {
        PsqlHelper.cleanDBPsql();
    }

    @BeforeEach
    public void openSource () {
        open("http://localhost:8080");
    }

    @Test
    @DisplayName("Payment card info can be sent and saved by PostgreSQL")
    void paymentCardInfoCanBeSentAndSavedByPostgreSQL () throws SQLException {
        val mainPage = new MainPage();
        val debitCard = mainPage.payWithDebitCard();
        debitCard.makePayment();
        declineMessage.waitUntil(visible, 10000);
        assertEquals(PsqlHelper.getCreatedOrderStatusPsql(), PsqlHelper.getCreatedPaymentStatusPsql());
    }

    @Test
    @DisplayName("Credit card info can be sent and saved by PostgreSQL")
    void creditCardInfoCanBeSentAndSavedByPostgreSQL () throws SQLException {
        val mainPage = new MainPage();
        val creditCard = mainPage.sendCreditRequest();
        creditCard.sendRequest();
        declineMessage.waitUntil(visible,10000);
        assertEquals(PsqlHelper.getCreatedOrderStatusPsql(), PsqlHelper.getCreatedRequestStatusPsql());
    }

    @Test
    @DisplayName("Credit request with first given card should be approved and saved by PostgreSQL")
    void creditRequestWithFirstGivenCardShouldBeApprovedAndSavedByPostgreSQL () throws SQLException {
        val mainPage = new MainPage();
        val creditCard = mainPage.sendCreditRequest();
        creditCard.sendRequestWithFirstCard();
        approveMessage.waitUntil(visible,10000);
        assertEquals(DataHelper.approvedStatus, PsqlHelper.getRequestStatusPsql());
    }

    @Test
    @DisplayName("Credit request with second given card should be declined and saved by PostgreSQL")
    void creditRequestWithSecondGivenCardShouldBeDeclinedAndSavedByPostgreSQL () throws SQLException {
        val mainPage = new MainPage();
        val creditCard = mainPage.sendCreditRequest();
        creditCard.sendRequestWithSecondCard();
        sleep(10000);
        assertEquals(DataHelper.declinedStatus, PsqlHelper.getRequestStatusPsql());
        declineMessage.shouldBe(visible);
    }
}
