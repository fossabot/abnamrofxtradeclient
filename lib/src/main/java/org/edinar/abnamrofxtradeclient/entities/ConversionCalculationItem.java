package org.edinar.abnamrofxtradeclient.entities;

import java.math.BigDecimal;

public class ConversionCalculationItem {
    private Long calculationId;
    private String buyCurrency;
    private String sellCurrency;
    private String currencyPair;
    private Rate allInRate;
    private BigDecimal buyAmount;
    private BigDecimal sellAmount;
    private BigDecimal contraAmount;
    private BigDecimal rate;
    private BigDecimal multiplier;

    public Long getCalculationId() {
        return calculationId;
    }

    public void setCalculationId(Long calculationId) {
        this.calculationId = calculationId;
    }

    public String getBuyCurrency() {
        return buyCurrency;
    }

    public void setBuyCurrency(String buyCurrency) {
        this.buyCurrency = buyCurrency;
    }

    public String getSellCurrency() {
        return sellCurrency;
    }

    public void setSellCurrency(String sellCurrency) {
        this.sellCurrency = sellCurrency;
    }

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public Rate getAllInRate() {
        return allInRate;
    }

    public void setAllInRate(Rate allInRate) {
        this.allInRate = allInRate;
    }

    public BigDecimal getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(BigDecimal buyAmount) {
        this.buyAmount = buyAmount;
    }

    public BigDecimal getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(BigDecimal sellAmount) {
        this.sellAmount = sellAmount;
    }

    public BigDecimal getContraAmount() {
        return contraAmount;
    }

    public void setContraAmount(BigDecimal contraAmount) {
        this.contraAmount = contraAmount;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(BigDecimal multiplier) {
        this.multiplier = multiplier;
    }
}
