package exercise9.randomFileGenerator;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Abre un Stream con un fichero aleatorio. Comprueba si el fichero
 * existe y en caso de que no, lo crea. En caso de que exista, lo carga.
 *
 * @author Manuel
 * @version 1
 */
public final class AgendaFileInitializer {

    // Referencia al fichero.
    private static RandomAccessFile fichero;

    // Número de registros que contiene el fichero
    private static int numeroRegistros;

    // Longitud en bytes dada a los ficheros. Se ha intentado
    // aprovechar al máximo el espacio del mismo.
    private static final int tamañoRegistro = 110;

    // Clase estática
    private AgendaFileInitializer() {
    }

    // Inicializa el fichero. Comprueba si existe
    // Si no existe le carga datos, si existe, recupera los datos
    public static void isFileInitialized() throws IOException {
        File agenda = new File("src\\exercise9Perfomenced\\randomFileGenerator\\files\\agenda.dat");

        if (agenda.isDirectory()) {
            throw new IOException(agenda.getName() + " no es un directorio");
        } else {
            fichero = new RandomAccessFile(agenda, "rw");

            getNumberOfRegistries();
            if (numeroRegistros != 0) {
                System.out.println("Fichero cargado");
            } else {
                generateRegistries();
                System.out.println("Fichero creado");
            }
        }
    }

    public static void showAllRegistries() throws IOException {
        for (int i = 0; i < numeroRegistros; i++) {
            fichero.seek(i * tamañoRegistro);

            if (!fichero.readBoolean()) {
                System.out.printf(
                        "%d, %s, %s, %s, %b, %d, %s, %d, %s\n",
                        fichero.readInt(),
                        fichero.readUTF(),
                        fichero.readUTF(),
                        fichero.readUTF(),
                        fichero.readBoolean(),
                        fichero.readInt(),
                        fichero.readUTF(),
                        fichero.readInt(),
                        fichero.readUTF());
            }
        }
    }

    public static void showRegistryById(int i) throws IOException {
        if (i < 1 || i > numeroRegistros) {
            System.out.println("No es un identificador válido");
            return;
        }


        fichero.seek((i - 1) * tamañoRegistro);

        if (!fichero.readBoolean()) {
            System.out.printf(
                    "%d, %s, %s, %s, %b, %d, %s, %d, %s\n",
                    fichero.readInt(),
                    fichero.readUTF(),
                    fichero.readUTF(),
                    fichero.readUTF(),
                    fichero.readBoolean(),
                    fichero.readInt(),
                    fichero.readUTF(),
                    fichero.readInt(),
                    fichero.readUTF());
        } else {
            System.out.println("No existe un empleado con ese ID");
        }
    }

    private static void generateRegistries() throws IOException {
        RandomFileBilder.createRandomFileAgenda(40, true);
        getNumberOfRegistries();
    }

    private static void getNumberOfRegistries() throws IOException {
        numeroRegistros = (int) Math.ceil(
                (double) fichero.length() / (double) tamañoRegistro);
    }
}
