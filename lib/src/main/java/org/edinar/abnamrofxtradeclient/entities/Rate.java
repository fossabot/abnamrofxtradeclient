package org.edinar.abnamrofxtradeclient.entities;

import java.math.BigDecimal;
import java.util.Date;

public class Rate {
    private BigDecimal bidRate;
    private BigDecimal askRate;
    private BigDecimal midRate;
    private Date effectiveDateTime;

    public BigDecimal getBidRate() {
        return bidRate;
    }

    public void setBidRate(BigDecimal bidRate) {
        this.bidRate = bidRate;
    }

    public BigDecimal getAskRate() {
        return askRate;
    }

    public void setAskRate(BigDecimal askRate) {
        this.askRate = askRate;
    }

    public BigDecimal getMidRate() {
        return midRate;
    }

    public void setMidRate(BigDecimal midRate) {
        this.midRate = midRate;
    }

    public Date getEffectiveDateTime() {
        return effectiveDateTime;
    }

    public void setEffectiveDateTime(Date effectiveDateTime) {
        this.effectiveDateTime = effectiveDateTime;
    }
}
