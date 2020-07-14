package Data;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataHelper {
    static Faker faker = new Faker(new Locale("ru"));
    public static String cardOwner = "IVAN IVANOV";
    public static String cardOwnerName = "IVAN";
    public static String cardOwnerRussian = "ИВАН ИВАНОВ";
    public static String firstGivenCardNumber = "4444 4444 4444 4441";
    public static String secondGivenCardNumber = "4444 4444 4444 4442";
    public static String characters = "qwertyйцукен";
    public static String cardNumberMaestro = "4276 5500 1111 2222 33";
    public static String symbols = "/.&!:*@:#,";
    public static String randomNumber = "182155";
    public static String declinedStatus = "DECLINED";
    public static String approvedStatus = "APPROVED";


    public static String getCardNumber () {
        String cardNum = faker.finance().creditCard(CreditCardType.MASTERCARD);
        String cardNumber = cardNum.replaceAll("-", "");
        return cardNumber;
    }

    public static String monthCalculating() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
        String cardMonth = date.format(monthFormatter);
        return cardMonth;
    }

    public static String yearCalculating() {
        LocalDate today = LocalDate.now();
        LocalDate cardDate = today.plusYears(2);
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy");
        String cardYear = cardDate.format(yearFormatter);
        return cardYear;
    }

    public static String getCVV () {
        int code = 100 + (int) (Math.random() * 900);
        String CVV = String.valueOf(code);
        return CVV;
    }

    public static String getShortCardNumber () {
        String shortCardNum = faker.finance().creditCard(CreditCardType.AMERICAN_EXPRESS);
        String shortCardNumber = shortCardNum.replaceAll("-", "");
        return shortCardNumber;
    }

    public static String oldYearCalculating() {
        LocalDate today = LocalDate.now();
        LocalDate cardDate = today.minusYears(2);
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy");
        String cardYear = cardDate.format(yearFormatter);
        return cardYear;
    }
}
