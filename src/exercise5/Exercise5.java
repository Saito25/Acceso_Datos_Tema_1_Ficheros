package exercise5;

import utility.KeyBoard;

import java.io.*;
import java.util.Arrays;

/*
    Realiza un programa que dadas dos rutas, origen y destino, copie el archivo origen(fichero  de texto) en el
    destino de la siguiente manera:

    • Si el destino es un directorio, se creará un archivo con el mismo nombre donde secopiará el
    archivo origen línea a línea(una línea se considera hasta que se encuentreun salto de línea).
    Utilizar Stream de la clase BufferedReader.

    • Si el destino es un archivo, habrá varias opciones según un booleano:
        ➢ Si el booleano es verdadero y el destino es un archivo existente, se reemplazará su
        contenido por el del archivo origen copiando carácter acarácter.

        ➢ Si el booleano es verdadero y el destino es un archivo inexistente, selanzará una  excepción.

        ➢ Si el booleano es falso y el destino es un archivo existente, se reemplazará su  contenido
        por el del archivo origen copiándolo usandoun buffer(array) sin desplazamiento de 20 caracteres.
        En el caso de la última escritura, si no se llena el  buffer, utilizar el desplazamiento para no
        dejar basura.

        ➢ Si el booleano es falso y el destino es un archivo inexistente, no se hará nada con el  archivo
        y se le dará un mensaje al usuario de que la copia no sepuede realizar.
 */
public class Exercise5 {

    public Exercise5() throws IOException {
        int i;
        boolean fileCondition = true;

        File writeFile;
        File readFile;
        File tempFile;
        FileReader fileReader;
        FileWriter fileWriter;
        BufferedReader bufferedReaderFile;
        BufferedWriter bufferedWriterFile = null;

        System.out.println("Introduce una ruta válida de origen y destino. \n" +
                "El origen debe ser un fichero. El destino puede ser un directorio o un fichero:\n" +
                "Introduzca una ruta de origen:\n");

        readFile = getDirectoryOrFilePath(true);
        bufferedReaderFile = new BufferedReader(
                                new FileReader(readFile));

        System.out.println("Introduce una ruta de destino:");

        writeFile = getDirectoryOrFilePath(false);


        if(writeFile.isDirectory()) {

            tempFile = new File(writeFile.getAbsolutePath() + "\\" + readFile.getName());
            tempFile.createNewFile();

            bufferedWriterFile = new BufferedWriter(
                    new FileWriter(tempFile));

            writeInFile(bufferedReaderFile, bufferedWriterFile);

            System.out.println("La copia del archivo no se pudo realizar.");

        } else {

            if(fileCondition) {

                if(writeFile.exists()) {
                    bufferedWriterFile = new BufferedWriter(
                            new FileWriter(writeFile));

                    writeInFile(bufferedReaderFile, bufferedWriterFile);

                    System.out.println("El fichero ha sido sobreescrito.");

                } else {
                    throw new NullPointerException("El fichero no existe");
                }

            } else {

                if(writeFile.exists()) {
                    fileWriter = new FileWriter(writeFile);
                    fileReader = new FileReader(readFile);

                    char[] c = new char[20];

                    while ((i = fileReader.read(c)) != -1) {
                        if(i == c.length) {
                            fileWriter.write(c);
                        } else {
                            fileWriter.write(c, 0, i);
                        }
                    }

                    fileReader.close();
                    fileWriter.close();

                    System.out.println("El fichero ha sido sobreescrito.");

                } else {
                    System.err.println("No se ha podido acceder al archivo.");
                }
            }
        }
    }

    private File getDirectoryOrFilePath(boolean onlyFile) {
        File file;
        boolean condition = true;
        String path = null;

        do {
            path = KeyBoard.getKEYBOARD().nextLine();
            file = new File(path);

            if(file.exists() && (!onlyFile || file.isFile())) {
                condition = false;
            } else {
                System.out.println("Prueba otra vez. Esa ruta no es válida.\n");
            }

        } while (condition);

        return file;
    }

    private void writeInFile(BufferedReader bufferedReader, BufferedWriter bufferedWriter) {
        String line;

        try {
            while((line = bufferedReader.readLine()) != null) {
                bufferedWriter.write(line);
                bufferedWriter.newLine();
            }
            bufferedReader.close();
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
