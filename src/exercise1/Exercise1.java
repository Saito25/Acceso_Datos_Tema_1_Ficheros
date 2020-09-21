package exercise1;

import utility.KeyBoard;

import java.io.File;

/*
    Realiza un programa que dado un fichero que se le solicite al usuario, muestre su nombre,
    si  es un ejecutable, si está oculto, la ruta relativa, la ruta absoluta y el tamaño.
 */
public class Exercise1 {

    public Exercise1() {
        // Leemos la ruta, la ingresa el usuario.
        System.out.println("Introduzca una ruta");
        String path = KeyBoard.getKEYBOARD().nextLine();

        // Creamos el objeto File.
        File file = new File(path);

        // Comprobamos si la ruta es un fichero.
        if(file.isFile()) {
            System.out.printf("Nombre: %s\n"    +
                    "Ejecutable: %b\n"          +
                    "Oculto: %b\n"              +
                    "Ruta relativa: %s\n"       +
                    "Ruta absoluta: %s\n"       +
                    "Tamaño del archivo: %d\n",
                    file.getName(),
                    file.canExecute(),
                    file.isHidden(),
                    file.getPath(),
                    file.getAbsolutePath(),
                    file.length());
        } else {
            System.out.println("No has introducido una ruta de ficheros inválida.");
        }
    }
}
