package com.springlab.libraryManagement.enums;

public enum MembershipType {
    REGULAR("Regular"),
    PREMIUM("Premium"),
    VIP("VIP");

    private final String type;

    MembershipType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public String toString() {
        return type;
    }
}
