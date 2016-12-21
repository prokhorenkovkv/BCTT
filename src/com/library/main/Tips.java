package com.library.main;

interface Tips {
    String NO_RESULT = "No such entries";
    String INCORRECT = "Require correct index";
    String REQUIRED = "You have to require both name and author";
    String NEW_NAME = "Type new book name";
    String LIST = "Our books:";
    String CHOISE = "We have few books with such name please choose one by typing a number of book.\n" +
            "You can type 'cancel' to cancel operation";
    String TIPS = "All books -> 'all'\n" +
            "Add new book -> 'add {author} \"{name}\"'\n" +
            "Edit book -> 'edit \"{name}\"'\n" +
            "Remove book -> 'remove \"{name}\"'\n" +
            "Get help -> 'help'\n" +
            "Terminate app -> 'exit'";
    String NO_COMMAND = "No such command";
}
