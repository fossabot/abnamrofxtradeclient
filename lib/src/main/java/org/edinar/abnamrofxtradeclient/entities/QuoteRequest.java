package org.edinar.abnamrofxtradeclient.entities;

public class QuoteRequest {
    private QuoteRequestItem quoteRequest;
    private String consumerQuoteReference;
    private String settlementAccountGroup;

    public QuoteRequestItem getQuoteRequest() {
        return quoteRequest;
    }

    public void setQuoteRequest(QuoteRequestItem quoteRequest) {
        this.quoteRequest = quoteRequest;
    }

    public String getConsumerQuoteReference() {
        return consumerQuoteReference;
    }

    public void setConsumerQuoteReference(String consumerQuoteReference) {
        this.consumerQuoteReference = consumerQuoteReference;
    }

    public String getSettlementAccountGroup() {
        return settlementAccountGroup;
    }

    public void setSettlementAccountGroup(String settlementAccountGroup) {
        this.settlementAccountGroup = settlementAccountGroup;
    }
}
