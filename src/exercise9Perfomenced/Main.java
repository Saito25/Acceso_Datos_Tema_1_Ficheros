package exercise9Perfomenced;

import exercise9Perfomenced.randomFileGenerator.RandomFileOperator;
import exercise9Perfomenced.randomFileGenerator.utils.RandomFileMenu;

import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException {
        new RandomFileMenu(new RandomFileOperator(1, true));
    }
}
