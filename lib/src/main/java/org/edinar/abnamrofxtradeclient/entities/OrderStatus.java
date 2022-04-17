package org.edinar.abnamrofxtradeclient.entities;

public enum OrderStatus {
    FILLED("FILLED"),
    ;

    private final String value;

    OrderStatus(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
