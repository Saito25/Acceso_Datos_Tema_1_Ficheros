package exercise7;

import utility.KeyBoard;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/*
    Diseñar un programa para encriptar y desencriptar los datos de un fichero de texto. Seintroduce una cadena
    por teclado que será la clave a aplicar para la encriptación ydesencriptación.

    A cada carácter del fichero de texto original se le sumará una letra de la clave, cuando se hayan
    acabado las letras de la palabra clave y aún no se hayan acabado los caracteresdel fichero, se
    volverá al principio de la cadena para seguir aplicando la encriptación. Losdatos encriptados se
    escribirán en un fichero destino, que será usado como origen paradesencriptar. Para desencriptar
    aplicará la fórmula a la inversa. Por ejemplo, si elfichero origen contiene “abcdef” y la palabra
    clave es “rosa”, en el fichero destino seescribirán los caracteres correspondientes a
    : “a+r b+o c+s  d+a e+r f+o”.
 */
public class Exercise7 {

    public Exercise7() {
        int index = 0; // Será usada para recorrer cada clave de la password.
        int i; // Será usada para almacenar el valor devuelto por el método read y write.
        String password;
        char[] keyPassword;


        System.out.println("Introduzca una clave de cifrado. Esta clave será usada en el cifrado" +
                " y en el descifrado del mensaje.");
        password = KeyBoard.getKEYBOARD().nextLine();
        keyPassword = password.toCharArray();

        // Iniciamos variables en bloque try-with-resources
        try (FileReader origenNoCifradoReader = new FileReader("src\\exercise7\\cryptography\\origenNoCifrado.txt");
             FileWriter destinoCifradoWriter = new FileWriter("src\\exercise7\\cryptography\\destinoCifrado.txt")) {

            while ((i = origenNoCifradoReader.read()) != -1) {
                /*
                    Se lee uno a uno los caracteres del origen sin cifrar y se van
                    cifrando.
                */
                destinoCifradoWriter.write(i + keyPassword[index++]);

                if (index == keyPassword.length - 1) {
                    // En caso de que index alcande la longitud del array, se resetea a 0.
                    index = 0;
                }
            }

            // RESET \\
            index = 0;


        } catch (IOException e) {
            e.printStackTrace();
        }

        try (FileReader destinoCifradoReader    = new FileReader("src\\exercise7\\cryptography\\destinoCifrado.txt");
             FileWriter destinoDescifradoWriter = new FileWriter("src\\exercise7\\cryptography\\destinoDescifrado.txt")) {

            while ((i = destinoCifradoReader.read()) != -1) {
                /*
                    Se lee uno a uno los caracteres del destino cifrado y se van
                    descifrando.
                */
                destinoDescifradoWriter.write(i - keyPassword[index++]);

                if (index == keyPassword.length - 1) {
                    // En caso de que index alcande la longitud del array, se resetea a 0.
                    index = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
