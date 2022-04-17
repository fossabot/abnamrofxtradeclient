package org.edinar.abnamrofxtradeclient.entities;

import java.math.BigDecimal;
import java.util.Date;

public class OrderResponse {
    private String orderId;
    private Date submittedDateTime;
    private String orderStatus;
    private String consumerOrderReference;
    private String buyCurrency;
    private BigDecimal buyAmount;
    private String sellCurrency;
    private BigDecimal sellAmount;
    private String settlement;
    private String settlementAccountGroup;
    private String quoteId;
    private String currencyPair;
    private Rate spotRate;
    private SwapPoints swapPoints;
    private Rate allInRate;
    private BigDecimal contraAmount;
    private BigDecimal rate;
    private String settlementDate;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public Date getSubmittedDateTime() {
        return submittedDateTime;
    }

    public void setSubmittedDateTime(Date submittedDateTime) {
        this.submittedDateTime = submittedDateTime;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getConsumerOrderReference() {
        return consumerOrderReference;
    }

    public void setConsumerOrderReference(String consumerOrderReference) {
        this.consumerOrderReference = consumerOrderReference;
    }

    public String getBuyCurrency() {
        return buyCurrency;
    }

    public void setBuyCurrency(String buyCurrency) {
        this.buyCurrency = buyCurrency;
    }

    public BigDecimal getBuyAmount() {
        return buyAmount;
    }

    public void setBuyAmount(BigDecimal buyAmount) {
        this.buyAmount = buyAmount;
    }

    public String getSellCurrency() {
        return sellCurrency;
    }

    public void setSellCurrency(String sellCurrency) {
        this.sellCurrency = sellCurrency;
    }

    public BigDecimal getSellAmount() {
        return sellAmount;
    }

    public void setSellAmount(BigDecimal sellAmount) {
        this.sellAmount = sellAmount;
    }

    public String getSettlement() {
        return settlement;
    }

    public void setSettlement(String settlement) {
        this.settlement = settlement;
    }

    public String getSettlementAccountGroup() {
        return settlementAccountGroup;
    }

    public void setSettlementAccountGroup(String settlementAccountGroup) {
        this.settlementAccountGroup = settlementAccountGroup;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

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

    public SwapPoints getSwapPoints() {
        return swapPoints;
    }

    public void setSwapPoints(SwapPoints swapPoints) {
        this.swapPoints = swapPoints;
    }

    public Rate getAllInRate() {
        return allInRate;
    }

    public void setAllInRate(Rate allInRate) {
        this.allInRate = allInRate;
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

    public String getSettlementDate() {
        return settlementDate;
    }

    public void setSettlementDate(String settlementDate) {
        this.settlementDate = settlementDate;
    }
}
