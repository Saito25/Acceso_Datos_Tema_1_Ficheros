package exercise9.randomFileGenerator;

import exercise9.utils.OldAgendaReader;
import exercise9.utils.Registry;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.List;

/**
 * Crea un fichero de acceso aleatorio y le añade los valores
 * pertienentes.
 *
 * @author Manuel
 * @version 1
 */
public final class RandomFileBilder {

    // Ruta del fichero aleatorio, que será usado en el ejercicio.
    private static final String randomFilePath = "src\\exercise9Perfomenced\\randomFileGenerator\\files\\agenda.dat";

    // Constructor privado
    private RandomFileBilder() {
    }

    public static void createRandomFileAgenda(int howToCreate, boolean writeInUTF) throws IOException {
        List<Registry> registriesList;

        existsRandomFileAgenda();

        registriesList = new OldAgendaReader(howToCreate).readRegistries();

        registryFormatter(registriesList, howToCreate, writeInUTF); // la función llama al método que guarda la información
    }

    /*
     *  Comprueba si existe el fichero y en caso de que no exista, se cre
     */
    private static void existsRandomFileAgenda() throws IOException {
        File randomAgendaFile = new File(randomFilePath);

        if (!randomAgendaFile.exists()) {
            randomAgendaFile.createNewFile();
        } else {
            randomAgendaFile.createNewFile();
        }
    }

    /*
        Formateo los registros al formato adecuado y llama a la función que los almacena
     */
    private static void registryFormatter(List<Registry> registriesList, int howToCreate, boolean writeInUTF) throws IOException {
        boolean[] isActive = new boolean[howToCreate];
        int[] ids = new int[howToCreate];
        String[] names = new String[howToCreate];
        String[] lastNames = new String[howToCreate];
        String[] birthDates = new String[howToCreate];
        boolean[] isDebtors = new boolean[howToCreate];
        int[] owedMoney = new int[howToCreate];
        String[] addresses = new String[howToCreate];
        int[] postalCode = new int[howToCreate];
        String[] telephones = new String[howToCreate];
        int index = 0;

        for (Registry registro : registriesList) {
            isActive[index] = false;
            ids[index] = index + 1;
            names[index] = stringLengthFormatter(registro.getName(), 10);
            lastNames[index] = stringLengthFormatter(registro.getLastName(), 15);
            birthDates[index] = stringLengthFormatter(registro.getBirthDate(), 10);
            isDebtors[index] = registro.isDebtor();
            owedMoney[index] = registro.getOwedMoney();
            addresses[index] = stringLengthFormatter(registro.getAddress(), 20);
            postalCode[index] = registro.getPostalCode();
            telephones[index] = stringLengthFormatter(registro.getTelephone(), 9);
            index++;
        }

        printRegistriesInFile(isActive, ids, names, lastNames, birthDates,
                isDebtors, owedMoney, addresses, postalCode, telephones, writeInUTF);
    }

    private static void printRegistriesInFile(boolean[] isActive, int[] ids,
                                              String[] names, String[] lastNames, String[] birthDates,
                                              boolean[] isDebtors, int[] owedMoney, String[] addresses,
                                              int[] postalCode, String[] telephones, boolean writeInUTF)
            throws IOException {

        RandomAccessFile randomAgendaFile = new RandomAccessFile(randomFilePath, "rw");
        int pointerPostition = 0;
        int lengthRegistry = 110;

        for(int i = 0; i < isActive.length; i++) {
            randomAgendaFile.seek(pointerPostition);

            randomAgendaFile.writeBoolean(isActive[i]);
            randomAgendaFile.writeInt(ids[i]);
            isActiveUTF(names[i], writeInUTF, randomAgendaFile);
            isActiveUTF(lastNames[i], writeInUTF, randomAgendaFile);
            isActiveUTF(birthDates[i], writeInUTF, randomAgendaFile);
            randomAgendaFile.writeBoolean(isDebtors[i]);
            randomAgendaFile.writeInt(owedMoney[i]);
            isActiveUTF(addresses[i], writeInUTF, randomAgendaFile);
            randomAgendaFile.writeInt(postalCode[i]);
            isActiveUTF(telephones[i], writeInUTF, randomAgendaFile);

            pointerPostition += lengthRegistry;
        }
        randomAgendaFile.close();
    }

    private static void isActiveUTF(String stringToPrint, boolean writeInUTF, RandomAccessFile randomAgendaFile) throws IOException {
        if(writeInUTF) {
            randomAgendaFile.writeUTF(stringToPrint);
        } else {
            randomAgendaFile.writeChars(stringToPrint);
        }
    }

    private static String stringLengthFormatter(String stringToFormat, int stringLength) {
        StringBuffer formatedString = new StringBuffer(stringToFormat);
        formatedString.setLength(stringLength);
        return formatedString.toString();
    }

    public static String getRandomFilePath() {
        return randomFilePath;
    }
}
