package org.edinar.abnamrofxtradeclient.entities;

public enum SettlementAccountGroup {
    CLIENT_ACCOUNT("Client Account"),
    HOUSE_ACCOUNT("House Account"),
    ;

    private final String value;

    SettlementAccountGroup(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
