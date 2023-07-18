package com.example.accesscontrolsystem.model;


public enum Departaments {
    BLACK("Black"),
    BLUE("Blue"),
    RED("Red"),
    YELLOW("Yellow"),
    GREEN("Green"),
    ORANGE("Orange"),
    PURPLE("Purple"),
    WHITE("White");

    private final String displayText;

    Departaments(String displayText) {
        this.displayText = displayText;
    }

    public String getDisplayText() {
        return displayText;
    }
}