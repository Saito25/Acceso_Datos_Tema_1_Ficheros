package exercise6;

import java.io.*;

/*
    Realiza un programa que dado un fichero de texto, se copie en tres ficherosdiferentes de tal  manera
    que copie en el primer fichero los primeros 5 caracteres, en el segundo, los 5 siguientes y  en el
    tercero los 5 siguientes, y así sucesivamente hastacopiar todo el fichero. Utilizar lectura sin
    desplazamiento y escrituras condesplazamiento. Después, hacer justamente lo contrario. Dados los 3 ficheros,
    construiruno como el fichero original. Utilizar ahora lecturas con desplazamiento y  escritura sindesplazamiento.
    Comprobar por código que ambos son iguales. En el caso de la últimalectura/escritura, si no se llena el buffer,
    utilizar el desplazamiento para no dejar basura
 */
public class Exercise6 {

    public Exercise6() {

        // Creamos las variables.
        int i;
        int selector = 0;
        char[] c = new char[5];
        boolean condition1 = true;
        boolean condition2 = true;
        boolean condition3 = true;
        FileWriter[] writers = new FileWriter[3];

        /*
        Creación de ficheros.
         */
        try (FileReader mainReader = new FileReader("src\\exercise6\\files\\FullFile.txt");
             FileWriter firstWriter = new FileWriter("src\\exercise6\\files\\FirstFile.txt");
             FileWriter secondWriter = new FileWriter("src\\exercise6\\files\\SecondFile.txt");
             FileWriter thirdWriter = new FileWriter("src\\exercise6\\files\\ThirdFile.txt")) {

            writers[0] = firstWriter;
            writers[1] = secondWriter;
            writers[2] = thirdWriter;

            /* ESCRIBIMOS EN 3 FICHEROS. */

            // Comprobamos el valor de i:
            // Si es -1 no hay caracteres en el fichero.
            // De otra forma, el valor puede ser de 1 a 5. Un valor menor a 5
            // es indicio de que el fichero se leyó por completo..
            while ((i = mainReader.read(c)) != -1) {
                // asignamos a i el valor devuelto por el método read y comprobamos
                // si es diferente a -1.

                if (i == c.length) {
                    writers[selector++].write(c);
                } else {
                    writers[selector++].write(c, 0, i);
                }

                // Reseteamos el contador para que no se salga del array.
                if (selector == writers.length) {
                    selector = 0;
                }
            }

            /*
            Comprobación y captación de errores.
             */
        } catch (IOException e) {
            System.err.println("No se ha podido leer ni escribir en los archivos.");
        }

        // RESET \\
        i = 0;
        selector = 0;

        /* RECONSTRUIR ARCHIVO   */
        try (FileWriter mainWriter = new FileWriter("src\\exercise6\\files\\ReBuildFullFile.txt");
             FileReader firstReader = new FileReader("src\\exercise6\\files\\FirstFile.txt");
             FileReader secondReader = new FileReader("src\\exercise6\\files\\SecondFile.txt");
             FileReader thirdReader = new FileReader("src\\exercise6\\files\\ThirdFile.txt")) {

            /*
                Iremos leyendo de los deferentes ficheros que se han creado en el apartado anterior
                e iremos construyendo un fichero nuevo, que debe ser igual a FullFile.
             */
            while (condition1 || condition2 || condition3) {

                if (condition1 && (i = firstReader.read(c)) != -1) {
                    writeInFile(mainWriter, i, c);
                } else {
                    condition1 = false;
                }

                if (condition2 && (i = secondReader.read(c)) != -1) {
                    writeInFile(mainWriter, i, c);
                } else {
                    condition2 = false;
                }

                if (condition3 && (i = thirdReader.read(c)) != -1) {
                    writeInFile(mainWriter, i, c);
                } else {
                    condition3 = false;
                }
            }

        } catch (IOException e) {
            System.err.println("No se ha podido reconstruir el archivo.");
        }
    }

    private void writeInFile(FileWriter writer, int i, char[] c) throws IOException {
        if (i == c.length) {
            writer.write(c);
        } else {
            writer.write(c, 0, i);
        }
    }

}
