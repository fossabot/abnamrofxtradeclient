package org.edinar.abnamrofxtradeclient.entities;

import java.util.Set;

public class ConversionCalculationResponse {
    private Set<ConversionCalculationItem> conversions;
    private Set<ConversionCalculationFailure> failures;

    public Set<ConversionCalculationItem> getConversions() {
        return conversions;
    }

    public void setConversions(Set<ConversionCalculationItem> conversions) {
        this.conversions = conversions;
    }

    public Set<ConversionCalculationFailure> getFailures() {
        return failures;
    }

    public void setFailures(Set<ConversionCalculationFailure> failures) {
        this.failures = failures;
    }
}
