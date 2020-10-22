package exercise9Perfomenced.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

/*
    Clase que se encarga de leer los objetos Registros almacenados en
    el documento agenda.
 */
public final class OldAgendaReader {
    private OldAgendaWriter oldAgendaWriter;

    public OldAgendaReader(int howRegistriesMustBeCreated) throws IOException {
        createFileAgenda(howRegistriesMustBeCreated);
    }

    /*
        Inicializa el objeto OldAgendaWriter
     */
    private void createFileAgenda(int howRegistriesMustBeCreated) {
        oldAgendaWriter = new OldAgendaWriter(howRegistriesMustBeCreated);
    }

    /*
        Lee y devuelve los objetos almacenados en el fichero.
     */
    public List<Registry> readRegistries() throws IOException {
        String filePath = oldAgendaWriter.getFilePath();
        List<Registry> registriesList = new ArrayList<>();
        Registry registry;

        ObjectInputStream objectRegistriesReader
                = new ObjectInputStream(new FileInputStream(filePath));

        try {
            while (true) {
                registry = (Registry) objectRegistriesReader.readObject();
                registriesList.add(registry);
            }
        } catch (IOException | ClassNotFoundException eofe) {
            System.out.println("Fichero cargado");
        }

        objectRegistriesReader.close();

        return registriesList;
    }
}
