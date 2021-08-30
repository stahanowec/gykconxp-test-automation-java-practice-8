package com.epam.test.automation.java.practice8;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SpecialDeposit extends Deposit implements Prolongable {

    private static final int MINIMUM_AMOUNT_FOR_PROLONGATION = 1000;

    protected SpecialDeposit(BigDecimal deposit, int period) {
        super(deposit, period);
    }

    @Override
    public BigDecimal getFinalDepositAmount() {
        var finalDeposit = amount.add(BigDecimal.ZERO);
        for (int i = 1; i <= period; i++) {
            finalDeposit = finalDeposit.multiply(BigDecimal.ONE.add(BigDecimal.valueOf(i).movePointLeft(2)));
        }
        return finalDeposit;
    }

    @Override
    public BigDecimal income() {
        return getFinalDepositAmount().subtract(amount).setScale(2, RoundingMode.HALF_DOWN);
    }

    @Override
    public boolean canToProlong() {
        return amount.compareTo(BigDecimal.valueOf(MINIMUM_AMOUNT_FOR_PROLONGATION)) > 0;
    }

    @Override
    public int compareTo(Deposit o) {
        return getFinalDepositAmount().compareTo(o.getFinalDepositAmount());
    }
}