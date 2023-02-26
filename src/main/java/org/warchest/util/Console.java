package org.warchest.util;

public abstract class Console {

    private static final boolean COLORS_DISABLED = Boolean.parseBoolean(System.getenv("WARCHEST_DISABLE_COLORS"));
    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_RED = "\u001B[31m";
    private static final String ANSI_GREEN = "\u001B[32m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    private static final String ANSI_BLUE = "\u001B[34m";
    private static final String ANSI_PURPLE = "\u001B[35m";

    public static String printRed(String string) {
        return COLORS_DISABLED ? string : ANSI_RED + string + ANSI_RESET;
    }

    public static String printBlue(String string) {
        return COLORS_DISABLED ? string : ANSI_BLUE + string + ANSI_RESET;
    }

    public static String printGreen(String string) {
        return COLORS_DISABLED ? string : ANSI_GREEN + string + ANSI_RESET;
    }

    public static String printYellow(String string) {
        return COLORS_DISABLED ? string : ANSI_YELLOW + string + ANSI_RESET;
    }

    public static String printPurple(String string) {
        return COLORS_DISABLED ? string : ANSI_PURPLE + string + ANSI_RESET;
    }
}
