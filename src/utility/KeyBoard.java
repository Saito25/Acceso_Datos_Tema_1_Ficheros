package utility;

import java.util.Scanner;

public class KeyBoard {

    private static final Scanner KEYBOARD = new Scanner(System.in);

    private KeyBoard(){};

    public static Scanner getKEYBOARD() {
        return KEYBOARD;
    }
}
