package exercise9Perfomenced.randomFileGenerator;

import exercise9Perfomenced.randomFileGenerator.utils.RandomFileMenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Objects;

/**
 * Proporciona diversos métodos para acceder a un fichero de acceso aleatorio,
 * modificar sus propiedades, ver registros, eliminarlos, etc.
 *
 * @author Manuel
 * @version 1
 */
public class RandomFileOperator {

    String randomFilePath;
    int currentMaxId;
    boolean writeInUTF;

    /**
     * Inicializa el fichero de acceso a leatorio y obtiene información del mismo.
     *
     * @param howToCreate
     * @param writeInUTF
     * @throws IOException
     */
    public RandomFileOperator(int howToCreate, boolean writeInUTF) throws IOException {
        // Crear fichero.
        RandomFileBilder.createRandomFileAgenda(howToCreate, writeInUTF);

        // Obtener fichero e índice actual máximo
        randomFilePath = RandomFileBilder.getRandomFilePath();
        currentMaxId = howToCreate;
        this.writeInUTF = writeInUTF;

        new RandomFileMenu(this);
    }

    /**
     * Lee todos los registros del fichero, teniendo en cuenta si se usa UTF
     *
     * @throws FileNotFoundException
     */
    public void showAllRegistries() throws IOException {
        RandomAccessFile randomAccessFile = openRandomAccesFile("r");

        if (writeInUTF) {
            showAllRegistriesInUTFMode(randomAccessFile);
        } else {
            showAllRegistriesNotUTFMode(randomAccessFile);
        }
        randomAccessFile.close();

    }

    /*
        Lee los registros en formato UTF
     */
    private void showAllRegistriesInUTFMode(RandomAccessFile randomAccessFile) throws IOException {
        // Calculo lectura:
        // 1 + 4 + 20 + 30 + 20 + 1 + 4 + 40 + 4 + 18: 142
        long pointerPosition = 0;

        while (true) {
            randomAccessFile.seek(pointerPosition);
            if (!randomAccessFile.readBoolean()) {
                System.out.printf("%d, %s, %s, %s, %b, %d, %s, %d, %s\n",
                        randomAccessFile.readInt(),
                        randomAccessFile.readUTF(),
                        randomAccessFile.readUTF(),
                        randomAccessFile.readUTF(),
                        randomAccessFile.readBoolean(),
                        randomAccessFile.readInt(),
                        randomAccessFile.readUTF(),
                        randomAccessFile.readInt(),
                        randomAccessFile.readUTF());
                System.out.println();
                System.out.println(randomAccessFile.getFilePointer());
                System.out.println(randomAccessFile.length());
            } else {
                randomAccessFile.readInt();
                randomAccessFile.readUTF();
                randomAccessFile.readInt();
                randomAccessFile.readUTF();
                randomAccessFile.readBoolean();
                randomAccessFile.readInt();
                randomAccessFile.readUTF();
                randomAccessFile.readInt();
                randomAccessFile.readUTF();
            }
            if (randomAccessFile.getFilePointer() == randomAccessFile.length() - 1) {
                System.out.printf("Fichero leído");
                break;
            } else {
                pointerPosition = randomAccessFile.getFilePointer();
            }

        }
    }

    /*
        Lee los registros en formato no UTF
     */
    private void showAllRegistriesNotUTFMode(RandomAccessFile randomAccessFile) {
        // Calculo lectura:
        // 1 + 4 + 20 + 30 + 20 + 1 + 4 + 40 + 4 + 18: 142 bytes
    }

    /*
        Crea un documento de aceso aleatorio para interactuar con el,
        "abre un flujo".
     */
    private RandomAccessFile openRandomAccesFile(String mode) throws FileNotFoundException {
        Objects.requireNonNull(mode);

        File file = new File(randomFilePath);
        return new RandomAccessFile(file, mode);
    }

}
