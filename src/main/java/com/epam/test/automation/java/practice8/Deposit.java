package com.epam.test.automation.java.practice8;

import java.math.BigDecimal;

public abstract class Deposit implements Comparable<Deposit> {
    private static final String DEPOSIT_NOT_INITIALIZED = "Deposit is no initialized!";
    private static final String DEPOSIT_NOT_NEGATIVE = "Deposit can not be negative!";
    private static final String PERIOD_NOT_NEGATIVE = "Period can not be negative!";
    public final BigDecimal amount;
    public final int period;

    protected Deposit(BigDecimal amount, int period) {
        validateDeposit(amount);
        if (period < 0) throw new IllegalArgumentException(PERIOD_NOT_NEGATIVE);
        this.amount = amount;
        this.period = period;
    }

    void validateDeposit(BigDecimal deposit) {
        if (null == deposit) throw new IllegalArgumentException(DEPOSIT_NOT_INITIALIZED);
        if (deposit.signum() <= 0) throw new IllegalArgumentException(DEPOSIT_NOT_NEGATIVE);
    }

    public abstract BigDecimal getFinalDepositAmount();

    public abstract BigDecimal income();
}