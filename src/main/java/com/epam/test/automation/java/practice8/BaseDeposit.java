package com.epam.test.automation.java.practice8;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class BaseDeposit extends Deposit {

    private static final int INTEREST = 5;

    protected BaseDeposit(BigDecimal deposit, int period) {
        super(deposit, period);
    }

    @Override
    public BigDecimal getFinalDepositAmount() {
        return amount.multiply(BigDecimal.ONE.add(BigDecimal.valueOf(INTEREST).movePointLeft(2)).pow(period));
    }

    @Override
    public BigDecimal income() {
        return getFinalDepositAmount().subtract(amount).setScale(2, RoundingMode.HALF_DOWN);
    }

    @Override
    public int compareTo(Deposit o) {
        return getFinalDepositAmount().compareTo(o.getFinalDepositAmount());
    }
}