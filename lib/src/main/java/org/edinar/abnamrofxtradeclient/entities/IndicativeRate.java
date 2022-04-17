package org.edinar.abnamrofxtradeclient.entities;

import java.util.Date;

public class IndicativeRate {
    private String currencyPair;
    private Rate spotRate;
    private Rate allInRate;
    private SwapPoints swapPoints;
    private Date settlementDate;

    public String getCurrencyPair() {
        return currencyPair;
    }

    public void setCurrencyPair(String currencyPair) {
        this.currencyPair = currencyPair;
    }

    public Rate getSpotRate() {
        return spotRate;
    }

    public void setSpotRate(Rate spotRate) {
        this.spotRate = spotRate;
    }

    public Rate getAllInRate() {
        return allInRate;
    }

    public void setAllInRate(Rate allInRate) {
        this.allInRate = allInRate;
    }

    public SwapPoints getSwapPoints() {
        return swapPoints;
    }

    public void setSwapPoints(SwapPoints swapPoints) {
        this.swapPoints = swapPoints;
    }

    public Date getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(Date settlementDate) {
        this.settlementDate = settlementDate;
    }
}
