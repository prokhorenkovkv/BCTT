package com.library.constants;

public enum Tips {
    NO_RESULT("No such entries"),
    INCORRECT("Require correct index"),
    REQUIRED("You have to require both name and author"),
    NEW_NAME("Type new book name"),
    LIST("Our books:"),
    CHOICE("We have few books with such name. Please choose one by typing a number of book.\n" +
            "You can type 'cancel' to cancel operation"),
    TIPS("All books -> 'all'\n" +
            "Add new book -> 'add {author} \"{name}\"'\n" +
            "Edit book -> 'edit \"{name}\"'\n" +
            "Remove book -> 'remove \"{name}\"'\n" +
            "Get help -> 'help'\n" +
            "Terminate app -> 'exit'"),
    NO_COMMAND("No such command"),
    FAIL("Something went wrong");

    private String tip;

    Tips(String tip) {
        this.tip = tip;
    }

    @Override
    public String toString() {
        return tip;
    }
}
