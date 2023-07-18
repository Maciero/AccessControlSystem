package com.example.accesscontrolsystem.model;


public enum Departments {
    BLACK("Black"),
    BLUE("Blue"),
    RED("Red"),
    YELLOW("Yellow"),
    GREEN("Green"),
    ORANGE("Orange"),
    PURPLE("Purple"),
    WHITE("White");

    private final String displayText;

    Departments(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}