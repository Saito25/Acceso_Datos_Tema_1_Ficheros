package exercise9Perfomenced.utils;

import java.io.*;

/**
 * Crea un fichero de registros. Se puede indicar manualmente cuántos
 * registros debe tener el fichero.
 *
 * @author Manuel
 * @version 1
 */
public final class OldAgendaWriter {

    // Contiene la ruta donde debe estar el fichero o dónde se creará si no está
    private final String filePath = "src\\exercise9Perfomenced\\utils\\files\\agenda.dat";

    private final int howRegistriesMustBeCreated;

    /*
        Creador de la clase. Cuando es invocado, se encarga de crear
        el fichero o de sobreescribirlo si ya existe.
     */
    public OldAgendaWriter(int howRegistriesMustBeCreated) {
        this.howRegistriesMustBeCreated = howRegistriesMustBeCreated;
        createAgendaFile();
    }

    /*
        Método encargado de crear el fichero.
     */
    private void createAgendaFile() {
        Registry[] registries = generateRegistries(howRegistriesMustBeCreated);
        try {
            isDocumentCreated();
            saveRegistriesInFile(registries);
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /*
        Llama al método factorio de RegistriesLoader para generar
        una serie de registros, que son devueltos.
     */
    private Registry[] generateRegistries(int howRegistriesMustBeCreated) {
        return RegistriesGenerator.RegistriesFactory(howRegistriesMustBeCreated);
    }

    /*
        Determina si hace falta crear el documento
     */
    private void isDocumentCreated() throws IOException {
        File agendaFile = new File(filePath);

        if (!agendaFile.exists()) {
            agendaFile.createNewFile();
        } else {
            agendaFile.createNewFile();
        }
    }

    /*
        Almacena los registros en el documento
     */
    private void saveRegistriesInFile(Registry[] registries) throws IOException {
        ObjectOutputStream objectRegistriesWriter =
                new ObjectOutputStream(new FileOutputStream(filePath));

        for (Registry registry : registries) {
            objectRegistriesWriter.writeObject(registry);
        }

        objectRegistriesWriter.close();
        System.out.println("Agenda Creada y cargada");
    }

    public String getFilePath() {
        return filePath;
    }
}
