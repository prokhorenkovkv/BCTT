package com.library.constants;

public enum Commands {
    ALL("all"),
    ADD("add"),
    UPDATE("edit"),
    REMOVE("remove"),
    EXIT("exit"),
    HELP("help"),
    CANCEL("cancel");

    private String command;

    Commands(String operation) {
        this.command = operation;
    }

    @Override
    public String toString() {
        return command;
    }
}