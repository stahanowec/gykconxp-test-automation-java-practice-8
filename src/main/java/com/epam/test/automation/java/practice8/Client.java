package com.epam.test.automation.java.practice8;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.*;

public class Client implements Iterable<Deposit> {
    private static final int DEFAULT_DEPOSITS_LENGTH = 10;
    private static final String DEPOSIT_NUMBER_CAN_NOT_BE_NEGATIVE = "Deposit number can not be negative!";
    private final Deposit[] deposits;
    private int count = 0;

    public Client() {
        this.deposits = new Deposit[DEFAULT_DEPOSITS_LENGTH];
    }

    public boolean addDeposit(Deposit deposit) {
        if (deposit == null) throw new IllegalArgumentException("Deposit is not initialized!");
        if (count == DEFAULT_DEPOSITS_LENGTH) return false;
        deposits[count++] = deposit;
        return true;
    }

    public BigDecimal totalIncome() {
        var result = Arrays.stream(deposits).filter(Objects::nonNull)
                .map(Deposit::income)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return result.setScale(2, RoundingMode.HALF_EVEN);
    }

    public BigDecimal maxIncome() {
        return Arrays.stream(deposits)
                .filter(Objects::nonNull)
                .map(Deposit::income)
                .max(Comparator.naturalOrder())
                .stream()
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public BigDecimal getIncomeByNumber(int number) {
        if (number < 0) throw new IllegalArgumentException(DEPOSIT_NUMBER_CAN_NOT_BE_NEGATIVE);
        if (number >= count) return BigDecimal.ZERO;
        var value = deposits[number];
        if (null == value) {
            return BigDecimal.ZERO;
        }
        return deposits[number].income();
    }

    public void sortDeposits() {
        Arrays.sort(deposits, 0, count, Comparator.reverseOrder());
    }

    public int countPossibleToProlongDeposit() {
        return (int) Arrays.stream(deposits)
                .filter(Objects::nonNull)
                .filter(this::isProlongable)
                .filter(this::filterCanProlong)
                .count();
    }

    @Override
    public Iterator<Deposit> iterator() {
        return new DepositIterator();
    }

    private <T> boolean isProlongable(T objects) {
        return objects instanceof Prolongable;
    }

    private boolean filterCanProlong(Deposit object) {
        return ((Prolongable) object).canToProlong();
    }

    class DepositIterator implements Iterator<Deposit> {
        private static final String THERE_ARE_NO_MORE_DEPOSITS = "There are no more deposits!";
        private int current = -1;

        @Override
        public boolean hasNext() {
            return count > 0 && current < deposits.length - 1;
        }

        @Override
        public Deposit next() {
            if (hasNext()) return deposits[++current];
            else throw new NoSuchElementException(THERE_ARE_NO_MORE_DEPOSITS);
        }
    }
}
