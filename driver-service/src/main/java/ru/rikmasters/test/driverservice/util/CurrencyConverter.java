package ru.rikmasters.test.driverservice.util;

import ru.rikmasters.test.driverservice.enums.Currency;

public class CurrencyConverter {

    private static Double redToGreenRate = 2.5;
    private static Double redToBlue = 1.5; //2,5 * 0,6

    /**
     * Красный доллар RED принят за основную валюту
     * Счет водителя ведется в красных долларах
     * Любые операции со счетом проводятся через конвертацию в красный доллар
     * @param currencyFrom тип валюты для операции
     * @param amount сумма в красных долларах, в зависимости от первого параметра
     */
    public static Double getRedAmount(Currency currencyFrom, Double amount) {
        var result = switch (currencyFrom) {
            case RED -> amount;
            case GREEN -> amount / redToGreenRate;
            case BLUE -> amount / redToBlue;
        };
        return result;
    }
}
