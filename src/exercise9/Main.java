package exercise9;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        try {
            new BaseDatosEmpleados();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
