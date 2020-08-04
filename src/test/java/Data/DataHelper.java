package Data;

import com.github.javafaker.CreditCardType;
import com.github.javafaker.Faker;
import lombok.Value;
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

    private static String getCardNumber () {
        String cardNum = faker.finance().creditCard(CreditCardType.MASTERCARD);
        String cardNumber = cardNum.replaceAll("-", "");
        return cardNumber;
    }

    private static String monthCalculating() {
        LocalDate date = LocalDate.now();
        DateTimeFormatter monthFormatter = DateTimeFormatter.ofPattern("MM");
        String cardMonth = date.format(monthFormatter);
        return cardMonth;
    }

    private static int yearCalculating() {
        LocalDate cardDate = LocalDate.now();
        DateTimeFormatter yearFormatter = DateTimeFormatter.ofPattern("yy");
        int cardYear = Integer.valueOf(cardDate.format(yearFormatter));
        return cardYear;
    }

    private static String getCVV () {
        int code = 100 + (int) (Math.random() * 900);
        String CVV = String.valueOf(code);
        return CVV;
    }

    private static String getShortCardNumber () {
        String shortCardNum = faker.finance().creditCard(CreditCardType.AMERICAN_EXPRESS);
        String shortCardNumber = shortCardNum.replaceAll("-", "");
        return shortCardNumber;
    }

    @Value
    public static class CardInfo {
        private String cardNumber;
        private String cardMonth;
        private String cardYear;
        private String cardOwner;
        private String cardCVV;
    }

    public static CardInfo getCardInfoSample () {
        return new CardInfo(getCardNumber(), monthCalculating(), String.valueOf(yearCalculating()+2), cardOwner, getCVV());
    }

    public static CardInfo getFirstGivenCard () {
        return new CardInfo(firstGivenCardNumber, monthCalculating(), String.valueOf(yearCalculating()+2), cardOwner, getCVV());
    }

    public static CardInfo getSecondGivenCard () {
        return new CardInfo(secondGivenCardNumber, monthCalculating(), String.valueOf(yearCalculating()+2), cardOwner, getCVV());
    }

    public static CardInfo getCharactersInsteadOfCardNumber () {
        return new CardInfo(characters, monthCalculating(), String.valueOf(yearCalculating()+2), cardOwner, getCVV());
    }

    public static CardInfo getCardWithShortNumber () {
        return new CardInfo(getShortCardNumber(), monthCalculating(), String.valueOf(yearCalculating()+2), cardOwner, getCVV());
    }

    public static CardInfo getCardWithLongNumber () {
        return new CardInfo(cardNumberMaestro, monthCalculating(), String.valueOf(yearCalculating()+2), cardOwner, getCVV());
    }

    public static CardInfo getSymbolsInsteadOfCardNumber () {
        return new CardInfo(symbols, monthCalculating(), String.valueOf(yearCalculating()+2), cardOwner, getCVV());
    }

    public static CardInfo getExpiredCardInfo () {
        return new CardInfo(getCardNumber(), monthCalculating(), String.valueOf(yearCalculating()-4), cardOwner, getCVV());
    }

    public static CardInfo getOnlyOwnerName () {
        return new CardInfo(getCardNumber(), monthCalculating(), String.valueOf(yearCalculating()+2), cardOwnerName, getCVV());
    }

    public static CardInfo getRussianOwnerName () {
        return new CardInfo(getCardNumber(), monthCalculating(), String.valueOf(yearCalculating()+2), cardOwnerRussian, getCVV());
    }

    public static CardInfo getNumberInsteadOfName () {
        return new CardInfo(getCardNumber(), monthCalculating(), String.valueOf(yearCalculating()+2), randomNumber, getCVV());
    }

    public static CardInfo getSymbolsInsteadOfName () {
        return new CardInfo(getCardNumber(), monthCalculating(), String.valueOf(yearCalculating()+2), symbols, getCVV());
    }

    public static CardInfo getRandomCharactersInsteadOfName () {
        return new CardInfo(getCardNumber(), monthCalculating(), String.valueOf(yearCalculating()+2), characters, getCVV());
    }

    public static CardInfo getSymbolsInsteadOfCVV () {
        return new CardInfo(getCardNumber(), monthCalculating(), String.valueOf(yearCalculating()+2), cardOwner, symbols);
    }

    public static CardInfo getCharactersInsteadOfCVV () {
        return new CardInfo(getCardNumber(), monthCalculating(), String.valueOf(yearCalculating()+2), cardOwner, characters);
    }

    public static CardInfo getCVVLongerThanThree () {
        return new CardInfo(getCardNumber(), monthCalculating(), String.valueOf(yearCalculating()+2), cardOwner, randomNumber);
    }

    public static CardInfo getCVVShorterThanThree () {
        return new CardInfo(getCardNumber(), monthCalculating(), String.valueOf(yearCalculating()+2), cardOwner, "13");
    }
}
