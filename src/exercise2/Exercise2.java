package exercise2;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

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
        String currentDirectory = "src\\exercise2";
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
                    System.err.println("No se ha podido crear el fichero.");
                }
            }

            // Eliminar archivo
            files[0].delete();

            // Renombrar fichero.
            files[1].renameTo(new File(subcurrentFile, "renameNewFile.txt"));

        } else {
            System.err.println("El directorio especificado no se puede crear.");
        }

        // Creamos directorio dentro del nuevo directorio dentro de nuestra carpeta actual
        if(subSubCurrentFile.mkdir()) {
            try {
                new File(subSubCurrentFile, "newSubSubCurrentDirectory.txt").createNewFile();
            } catch (IOException e) {
                System.err.println("No se ha podido crear el fichero.");
            }

        } else {
            System.err.println("No se ha podido crear el directorio.");
        }

        /*
            Recorrer directorios y mostrar archivos.
         */
        showFilesName(subcurrentFile);
        showFilesName(subSubCurrentFile);


    }

    private void showFilesName(File subcurrentFile) {

        // Cambiar por fori.
        if(subcurrentFile.exists() && subcurrentFile.isDirectory()) {
            for(String elementName : subcurrentFile.list()) {
                System.out.println(elementName);
            }
        } else  {
            System.err.println("No hay ficheros en el directorio");
        }
    }
}
