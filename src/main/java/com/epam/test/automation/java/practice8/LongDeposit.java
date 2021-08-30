package com.epam.test.automation.java.practice8;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class LongDeposit extends Deposit implements Prolongable {

    private static final int IDLE_PERIOD = 6;
    private static final int INTEREST = 15;
    private static final int MAXIMUM_PERIOD_FOR_PROLONGATION = 36;

    protected LongDeposit(BigDecimal deposit, int period) {
        super(deposit, period);
    }

    @Override
    public BigDecimal getFinalDepositAmount() {
        if (period > IDLE_PERIOD) {
            return amount.multiply(BigDecimal.ONE.add(BigDecimal.valueOf(INTEREST).movePointLeft(2)).pow(period - IDLE_PERIOD));
        }
        return amount;
    }

    @Override
    public BigDecimal income() {
        if (period > IDLE_PERIOD) {
            return getFinalDepositAmount().subtract(amount).setScale(2, RoundingMode.HALF_DOWN);
        }
        return BigDecimal.ZERO;
    }

    @Override
    public boolean canToProlong() {
        return period < MAXIMUM_PERIOD_FOR_PROLONGATION;
    }

    @Override
    public int compareTo(Deposit o) {
        return getFinalDepositAmount().compareTo(o.getFinalDepositAmount());
    }
}