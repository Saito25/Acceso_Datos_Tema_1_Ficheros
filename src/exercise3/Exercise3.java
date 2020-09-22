package exercise3;

import utility.KeyBoard;

import java.io.File;
import java.util.Objects;

/*
    Realiza un programa que muestre el nombre y tipo (fichero o directorio) de losficheros y  subdirectorios
    contenidos en un directorio solicitado al usuario. Mostrartambién el contenido de  todos los subdirectorios
    y si éstos contienen subdirectoriostambién...y así sucesivamente hasta  mostrar todo el contenido de
    dicho directorio
 */
public class Exercise3 {

    public Exercise3() {

        System.out.println("Introduce una ruta válida para un directorio.");

        File directoryFile = getPathForFile();

        showAllTreeDirectory(directoryFile, "|-");

    }

    private void showAllTreeDirectory(File directoryFile, String delimiter) {

        if(!directoryFile.exists()) {
            throw new IllegalArgumentException("El directorio pasado no existe");
        }

        System.out.printf("%s%s - %s\n",delimiter, directoryFile.getName(), directoryFile.isDirectory() ? "Directorio":"Archivo");

        for(File file : directoryFile.listFiles()) {

            if(file.isDirectory()) {
                showAllTreeDirectory(new File(directoryFile + "\\" + file.getName()), " " + delimiter);
            } else {
                System.out.printf("%s%s - %s\n",delimiter, file.getName(), file.isDirectory() ? "Directorio":"Archivo");

            }
        }
    }

    private File getPathForFile() {

        boolean condition = true;
        String pathName;
        File file = null;

        do {
            pathName = KeyBoard.getKEYBOARD().nextLine();
            file = new File(pathName);

            if(file.exists() && file.isDirectory()) {
                System.out.println("Ruta correcta");
                condition = false;
            } else {
                System.out.println("Ruta no válida. Introduce otra.");
            }

        } while (condition);

        return file;
    }
}
