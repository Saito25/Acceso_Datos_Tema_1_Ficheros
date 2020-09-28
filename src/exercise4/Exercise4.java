package exercise4;


import utility.KeyBoard;

import java.io.File;

/*
    Un filtro sirve para que el método list devuelva solo aquellos archivos o
    carpetas que  cumplan una condición (que tengan una extensión determinada,
    contengan en sunombre una  cadena concreta, empiecen por un carácter, etc).
    Un filtro es un objeto deuna clase que implementa el interface FilenameFilter.
    Realiza un programa que muestrelos archivos de un directorio que  posean una
    extensión concreta. Tanto la extensióncomo el directorio se solicita al usuario.
 */
public class Exercise4 {

    public Exercise4() {
        String extension;
        String finalExtension;
        String path;
        boolean condition = true;
        File filePath;


        System.out.println("Introduzca una extensión");
        extension = KeyBoard.getKEYBOARD().nextLine();

        System.out.println("Introduzca una ruta de directorio");

        do {
            path = KeyBoard.getKEYBOARD().nextLine();
            filePath = new File(path);

            if(filePath.exists() && filePath.isDirectory()) {
                condition = false;
            } else {
                System.out.println("Ruta incorrecta. Vuelva a intentarlo");
            }

        } while (condition);

        System.out.printf("En la ruta %s se han encontrado los siguientes\n " +
                "elementos con la extensión %s\n", filePath.getAbsolutePath(), extension);

        finalExtension = extension;
        for(String element : filePath.list((pathname, name) -> name.endsWith(finalExtension))) {
            System.out.println(element);
        }
    }
}
