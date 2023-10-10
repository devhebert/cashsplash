package com.example.cashsplash.enums;

public enum UserType {
    ADMIN(0),
    COMMON(1);

    private final int value;

    UserType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static UserType fromValue(int value) {
        for (UserType status : UserType.values()) {
            if (status.getValue() == value) {
                return status;
            }
        }
        throw new IllegalArgumentException("Invalid VaccinationStatus value: " + value);
    }

}
