package com.aidou.util.tool;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Currency;

/**
 * Created by Administrator on 2017/3/22.
 */

public class Money implements Serializable, Comparable {

    public static final String DEFAULT_CURRENCY_CODE = "CNY";
    public static final int DEFAULT_ROUNDING_MODE = 6;
    public static final Money ZERO = new Money(0L);
    private static final int[] centFactors = {1, 10, 100, 1000};
    private static final long serialVersionUID = 3761410806910104373L;
    private long cent;
    private Currency currency;

    public Money() {
        this(0L);
    }

    public Money(double paramDouble) {
        this(paramDouble, Currency.getInstance("CNY"));
    }

    public Money(double paramDouble, Currency paramCurrency) {
        this.currency = paramCurrency;
        this.cent = Math.round(paramDouble * getCentFactor());
    }

    public Money(long paramLong) {
        this.currency = Currency.getInstance("CNY");
        this.cent = paramLong;
    }

    public Money(long paramLong, int paramInt) {
        this(paramLong, paramInt, Currency.getInstance("CNY"));
    }

    public Money(long paramLong, int paramInt, Currency paramCurrency) {
        this.currency = paramCurrency;
        this.cent = (paramLong * getCentFactor() + paramInt % getCentFactor());
    }

    public Money(String paramString) {
        this(paramString, Currency.getInstance("CNY"));
    }

    public Money(String paramString, Currency paramCurrency) {
        this(new BigDecimal(paramString), paramCurrency);
    }

    public Money(String paramString, Currency paramCurrency, int paramInt) {
        this(new BigDecimal(paramString), paramCurrency, paramInt);
    }

    public Money(BigDecimal paramBigDecimal) {
        this(paramBigDecimal, Currency.getInstance("CNY"));
    }

    public Money(BigDecimal paramBigDecimal, int paramInt) {
        this(paramBigDecimal, Currency.getInstance("CNY"), paramInt);
    }

    public Money(BigDecimal paramBigDecimal, Currency paramCurrency) {
        this(paramBigDecimal, paramCurrency, 6);
    }

    public Money(BigDecimal paramBigDecimal, Currency paramCurrency, int paramInt) {
        this.currency = paramCurrency;
        this.cent = rounding(paramBigDecimal.movePointRight(paramCurrency.getDefaultFractionDigits()), paramInt);
    }

    public Money add(Money paramMoney) {
        assertSameCurrencyAs(paramMoney);
        return newMoneyWithSameCurrency(this.cent + paramMoney.cent);
    }

    public Money addTo(Money paramMoney) {
        assertSameCurrencyAs(paramMoney);
        this.cent += paramMoney.cent;
        return this;
    }

    public Money[] allocate(int paramInt) {
        Money[] arrayOfMoney = new Money[paramInt];
        Money localMoney1 = newMoneyWithSameCurrency(this.cent / paramInt);
        Money localMoney2 = newMoneyWithSameCurrency(1L + localMoney1.cent);
        int i = (int) this.cent % paramInt;
        for (int j = 0; j < i; j++)
            arrayOfMoney[j] = localMoney2;
        for (int k = i; k < paramInt; k++)
            arrayOfMoney[k] = localMoney1;
        return arrayOfMoney;
    }

    public Money[] allocate(long[] paramArrayOfLong) {
        Money[] arrayOfMoney = new Money[paramArrayOfLong.length];
        long l1 = 0L;
        for (int i = 0; i < paramArrayOfLong.length; i++)
            l1 += paramArrayOfLong[i];
        long l2 = this.cent;
        for (int j = 0; j < arrayOfMoney.length; j++) {
            arrayOfMoney[j] = newMoneyWithSameCurrency(this.cent * paramArrayOfLong[j] / l1);
            l2 -= arrayOfMoney[j].cent;
        }
        for (int k = 0; k < l2; k++) {
            Money localMoney = arrayOfMoney[k];
            localMoney.cent = (1L + localMoney.cent);
        }
        return arrayOfMoney;
    }

    protected void assertSameCurrencyAs(Money paramMoney) {
        if (!this.currency.equals(paramMoney.currency))
            throw new IllegalArgumentException("Money math currency mismatch.");
    }

    public int compareTo(Money paramMoney) {
        assertSameCurrencyAs(paramMoney);
        if (this.cent < paramMoney.cent)
            return -1;
        if (this.cent == paramMoney.cent)
            return 0;
        return 1;
    }

    public int compareTo(Object paramObject) {
        return compareTo((Money) paramObject);
    }

    public Money divide(double paramDouble) {
        return newMoneyWithSameCurrency(Math.round(this.cent / paramDouble));
    }

    public Money divide(BigDecimal paramBigDecimal) {
        return divide(paramBigDecimal, 6);
    }

    public Money divide(BigDecimal paramBigDecimal, int paramInt) {
        return newMoneyWithSameCurrency(BigDecimal.valueOf(this.cent).divide(paramBigDecimal, paramInt).longValue());
    }

    public Money divideBy(double paramDouble) {
        this.cent = Math.round(this.cent / paramDouble);
        return this;
    }

    public Money divideBy(BigDecimal paramBigDecimal) {
        return divideBy(paramBigDecimal, 6);
    }

    public Money divideBy(BigDecimal paramBigDecimal, int paramInt) {
        this.cent = BigDecimal.valueOf(this.cent).divide(paramBigDecimal, paramInt).longValue();
        return this;
    }

    public String dump() {
        String str = System.getProperty("line.separator");
        StringBuffer localStringBuffer = new StringBuffer();
        localStringBuffer.append("cent = ").append(this.cent).append(str);
        localStringBuffer.append("currency = ").append(this.currency);
        return localStringBuffer.toString();
    }

    public boolean equals(Money paramMoney) {
        return (this.currency.equals(paramMoney.currency)) && (this.cent == paramMoney.cent);
    }

    public boolean equals(Object paramObject) {
        return ((paramObject instanceof Money)) && (equals((Money) paramObject));
    }

    public BigDecimal getAmount() {
        return BigDecimal.valueOf(this.cent, this.currency.getDefaultFractionDigits());
    }

    public long getCent() {
        return this.cent;
    }

    public int getCentFactor() {
        return centFactors[this.currency.getDefaultFractionDigits()];
    }

    public Currency getCurrency() {
        return this.currency;
    }

    public String getCurrencyCode() {
        return this.currency.getCurrencyCode();
    }

    public boolean greaterThan(Money paramMoney) {
        return compareTo(paramMoney) > 0;
    }

    public int hashCode() {
        return (int) (this.cent ^ this.cent >>> 32);
    }

    public Money multiply(double paramDouble) {
        return newMoneyWithSameCurrency(Math.round(paramDouble * this.cent));
    }

    public Money multiply(long paramLong) {
        return newMoneyWithSameCurrency(paramLong * this.cent);
    }

    public Money multiply(BigDecimal paramBigDecimal) {
        return multiply(paramBigDecimal, 6);
    }

    public Money multiply(BigDecimal paramBigDecimal, int paramInt) {
        return newMoneyWithSameCurrency(rounding(BigDecimal.valueOf(this.cent).multiply(paramBigDecimal), paramInt));
    }

    public Money multiplyBy(double paramDouble) {
        this.cent = Math.round(paramDouble * this.cent);
        return this;
    }

    public Money multiplyBy(long paramLong) {
        this.cent = (paramLong * this.cent);
        return this;
    }

    public Money multiplyBy(BigDecimal paramBigDecimal) {
        return multiplyBy(paramBigDecimal, 6);
    }

    public Money multiplyBy(BigDecimal paramBigDecimal, int paramInt) {
        this.cent = rounding(BigDecimal.valueOf(this.cent).multiply(paramBigDecimal), paramInt);
        return this;
    }

    protected Money newMoneyWithSameCurrency(long paramLong) {
        Money localMoney = new Money(0.0D, this.currency);
        localMoney.cent = paramLong;
        return localMoney;
    }

    protected long rounding(BigDecimal paramBigDecimal, int paramInt) {
        return paramBigDecimal.setScale(0, paramInt).longValue();
    }

    public void setAmount(BigDecimal paramBigDecimal) {
        if (paramBigDecimal != null)
            this.cent = rounding(paramBigDecimal.movePointRight(2), 6);
    }

    public void setCent(long paramLong) {
        this.cent = paramLong;
    }

    public Money subtract(Money paramMoney) {
        assertSameCurrencyAs(paramMoney);
        return newMoneyWithSameCurrency(this.cent - paramMoney.cent);
    }

    public Money subtractFrom(Money paramMoney) {
        assertSameCurrencyAs(paramMoney);
        this.cent -= paramMoney.cent;
        return this;
    }

    public String toString() {
        if (getAmount().floatValue() == 0.0F)
            return "0";
        return getAmount().toString();
    }
}
