package org.edinar.abnamrofxtradeclient.entities;

public class OrderRequest {
    private OrderRequestItem orderRequest;
    private String settlementAccountGroup;
    private String quoteSignature;

    public OrderRequestItem getOrderRequest() {
        return orderRequest;
    }

    public void setOrderRequest(OrderRequestItem orderRequest) {
        this.orderRequest = orderRequest;
    }

    public String getSettlementAccountGroup() {
        return settlementAccountGroup;
    }

    public void setSettlementAccountGroup(String settlementAccountGroup) {
        this.settlementAccountGroup = settlementAccountGroup;
    }

    public String getQuoteSignature() {
        return quoteSignature;
    }

    public void setQuoteSignature(String quoteSignature) {
        this.quoteSignature = quoteSignature;
    }
}
