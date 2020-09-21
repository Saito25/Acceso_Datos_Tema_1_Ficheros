package exercise2;

import java.io.File;
import java.io.IOException;

/*
    Realiza un programa que cree un directorio en el directorio actual, luego
    cree tres ficheros  en dicho directorio donde uno se borre y otro se renombre.
    Crearle también unsubdirectorio con un fichero dentro. Después mostrar la ruta
    absoluta de ambosdirectorios y sus contenidos.
 */
public class Exercise2 {

    public Exercise2() {

        /*
            Inicializar datos: directorios, variables.
         */
        String currentDirectory = ".\\src\\exercise2";
        String subCurrentDirectory = currentDirectory + "\\newDirectory";
        String subSubCurrentDirectory = subCurrentDirectory + "\\subNewDirectory";

        File subcurrentFile = new File(subCurrentDirectory);
        File subSubCurrentFile = new File(subSubCurrentDirectory);

        File[] files = new File[3];

        // Crear directorio dentro del directorio actual, crear archivos y hacer operaciones con ellos
        if(subcurrentFile.mkdir()) {

            System.out.println("Directorio creado");

            for(int i = 0; i < files.length; i++) {
                files[i] = new File(subCurrentDirectory,
                        String.format("newDirectory_%d.txt", i + 1));

                try {
                    files[i].createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

        } else {
            System.out.println("El directorio especificado no se puede crear.");
        }

    }
}
