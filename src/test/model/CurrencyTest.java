package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CurrencyTest {
    Currency testCurrency;
    Currency otherTestCurrency;

    @BeforeEach
    void runBefore() {
    testCurrency = new Currency();
    otherTestCurrency = new Currency();
    otherTestCurrency.addCurrency(10000);
    }

    @Test
    void getCurrencyTest() {
        assertEquals(1000,testCurrency.getCurrency());
        assertEquals(11000,otherTestCurrency.getCurrency());
    }

    @Test
    void addCurrencyTest() {
        assertEquals(1000,testCurrency.getCurrency());
        testCurrency.addCurrency(1000);
        assertEquals(2000,testCurrency.getCurrency());
        testCurrency.addCurrency(0);
        assertEquals(2000,testCurrency.getCurrency());

        assertEquals(11000,otherTestCurrency.getCurrency());
        otherTestCurrency.addCurrency(4569);
        assertEquals(15569,otherTestCurrency.getCurrency());
    }

    @Test
    void subCurrencyTest() {
        assertEquals(1000,testCurrency.getCurrency());
        testCurrency.subCurrency(500);
        assertEquals(500,testCurrency.getCurrency());
        testCurrency.subCurrency(0);
        assertEquals(500,testCurrency.getCurrency());

        assertEquals(11000,otherTestCurrency.getCurrency());
        otherTestCurrency.subCurrency(20000);
        assertEquals(-9000,otherTestCurrency.getCurrency());
    }
}
