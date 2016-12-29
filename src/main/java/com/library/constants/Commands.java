package com.library.constants;

public enum Commands {
    ALL("all"),
    ADD("add"),
    UPDATE("edit"),
    REMOVE("remove"),
    EXIT("exit"),
    HELP("help"),
    CANCEL("cancel");

    private String operation;

    Commands(String operation) {
        this.operation = operation;
    }

    @Override
    public String toString() {
        return operation;
    }
}