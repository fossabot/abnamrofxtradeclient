package org.edinar.abnamrofxtradeclient.entities;

public class ConversionCalculationFailure {
    private ConversionCalculationRequest conversionRequest;
    private String code;
    private String reason;

    public ConversionCalculationRequest getConversionRequest() {
        return conversionRequest;
    }

    public void setConversionRequest(ConversionCalculationRequest conversionRequest) {
        this.conversionRequest = conversionRequest;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
