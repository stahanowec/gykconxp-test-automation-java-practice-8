package com.epam.test.automation.java.practice8;

import org.testng.annotations.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.testng.AssertJUnit.assertEquals;

public class SpecialDepositTest {
    @Test
    public void ifCalculateIncomeThenSuccess() {
        //given
        var specialDeposit = new SpecialDeposit(new BigDecimal(3000), 6);
        var expected = BigDecimal.valueOf(684.75).setScale(2, RoundingMode.HALF_EVEN);
        //when
        var income = specialDeposit.income();
        //then
        assertEquals(expected, income);

    }
}