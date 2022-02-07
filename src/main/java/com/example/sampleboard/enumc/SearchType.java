package com.example.sampleboard.enumc;

public enum SearchType {

    TITLE("title"),
    CONTENT("content"),
    WRITER("writer"),
    NONE("");

    private final String value;

    SearchType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
