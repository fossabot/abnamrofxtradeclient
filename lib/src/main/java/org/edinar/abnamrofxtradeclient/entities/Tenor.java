package org.edinar.abnamrofxtradeclient.entities;

public enum Tenor {
    SPOT("SPOT"),
    ASAP("ASAP"),
    TODAY("TODAY"),
    TOMORROW("TOMORROW"),
    ;

    private final String value;

    Tenor(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return value;
    }
}
