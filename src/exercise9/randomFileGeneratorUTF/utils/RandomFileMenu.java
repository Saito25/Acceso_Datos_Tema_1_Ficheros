package exercise9.randomFileGenerator.utils;

import exercise9.randomFileGenerator.AgendaFileInitializer;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Permite la interacion con un documento de acceso aleatorio,
 * proporcionando un menú con diversas opciónes
 *
 * @author Manuel
 * @version 1
 */
public class RandomFileMenu {

    private final Scanner keyboard = new Scanner(System.in);

    // Constructor simple
    public RandomFileMenu() throws IOException {
        AgendaFileInitializer.isFileInitialized();
        showOptions();
    }

    /**
     * Muestra las diferentes opciones que tiene el usuario
     * para interactuar con el fichero
     */
    private void showOptions() throws IOException {
        System.out.println(
                "1.- Consultar todos los contactos\n" +
                        "2.- Consultar un contacto (por ID)\n" +
                        "3.- Añadir un contacto al final\n" +
                        "4.- Añadir un contacto en la primera posición libre\n" +
                        "5.- Borrado lógico de un contacto\n" +
                        "6.- Modificar deudor\n" +
                        "7.- Compactar fichero\n" +
                        "8.- Salir");

        makeElection();
    }

    /*
        Llama a los métodos correspondiente para elegir la opción
        a ejecutar, para hacerlo de forma segura.
     */
    private void makeElection() throws IOException {
        int election;

        election = testElection(1, 8);

       executeOption(election);
    }

    /*
        Determina qué opción se llevará acabo.
        Se entiende que este método jamás recibirá
        un valor fuera de lo esperado.
     */
    private void executeOption(int election) throws IOException {
        switch (election) {
            case 1:
                showAllRegistries();
                break;
            case 2:
                showRegistryByID();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:

                break;
        }
    }

    private void showRegistryByID() throws IOException {
        int election;

        System.out.println("Introduce un ID:");

        election = testElection(1, 100);

        AgendaFileInitializer.showRegistryById(election);
    }

    private void showAllRegistries() throws IOException {
        AgendaFileInitializer.showAllRegistries();
    }

    /*
        Comprueba que la elección introducida es válida
     */
    private int testElection(int min, int max) {
        int election = 0;
        boolean isNotCorrectElection = true;

        System.out.println("Introduce una opción: ");

        do {
            try {
                election = keyboard.nextInt();
            } catch (InputMismatchException ime) {
                System.out.println("Introduce un número, por favor");
            }

            if(election <= max && election >= min) {
                isNotCorrectElection = false;
            } else {
                System.out.println("La opción introducida no es válida");
            }
        } while (isNotCorrectElection);

        return election;
    }

} // Fin clase
