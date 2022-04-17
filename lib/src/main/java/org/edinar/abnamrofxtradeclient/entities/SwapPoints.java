package org.edinar.abnamrofxtradeclient.entities;

import java.math.BigDecimal;

public class SwapPoints {
    private BigDecimal bidPoints;
    private BigDecimal askPoints;

    public BigDecimal getBidPoints() {
        return bidPoints;
    }

    public void setBidPoints(BigDecimal bidPoints) {
        this.bidPoints = bidPoints;
    }

    public BigDecimal getAskPoints() {
        return askPoints;
    }

    public void setAskPoints(BigDecimal askPoints) {
        this.askPoints = askPoints;
    }
}
