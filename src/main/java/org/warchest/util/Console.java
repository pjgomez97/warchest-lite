package org.warchest.util;

public abstract class Console {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_BLACK = "\u001B[30m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";
    private static final String ANSI_CYAN = "\u001B[36m";
    private static final String ANSI_WHITE = "\u001B[37m";

    public static String printRed(String string) {
        return ANSI_RED + string + ANSI_RESET;
    }

    public static String printBlue(String string) {
        return ANSI_BLUE + string + ANSI_RESET;
    }

    public static String printGreen(String string) {
        return ANSI_GREEN + string + ANSI_RESET;
    }

    public static String printYellow(String string) {
        return ANSI_YELLOW + string + ANSI_RESET;
    }

    public static String printPurple(String string) {
        return ANSI_PURPLE + string + ANSI_RESET;
    }
}
