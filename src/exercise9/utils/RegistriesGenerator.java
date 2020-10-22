package exercise9.utils;

import java.util.Random;

/**
 * Clase encargada de crear y devolver los registros que serán almacenados,
 * posteriormente en el fichero, por otra clase.
 *
 * @author Manuel
 * @version 1
 */
public final class RegistriesGenerator {

    private static final Random dataRandomGenerator = new Random();

    // Constructor privado, por clase privada
    private RegistriesGenerator() {
    }

    /**
     * Método factoría que creará una cantidad n de Registros, dependiendo
     * del valor del parámetro pasado al método.
     *
     * @param howCreate determina cuantos registros se crearán
     * @return un array de Registros
     */
    public static Registry[] RegistriesFactory(int howCreate) {
        integerValidator(howCreate);

        Registry[] registries = new Registry[howCreate];

        for (int i = 0; i < howCreate; i++) {
            registries[i] = new Registry(
                    postalCodeGenerate(),
                    owedMoneyGenerate(),
                    isDebetorGenerate(),
                    nameGenerator(i),
                    lastNameGenerator(i),
                    adrressGenerator(i),
                    birthDateGenerator(),
                    telephoneGenerator()
            );
        }
        return registries;
    }

    /*
        Genera un número telefónico móvil inmaginario
     */
    private static String telephoneGenerator() {
        int number = dataRandomGenerator.nextInt(99999999) + 600000000;
        return Integer.toString(number);

    }

    /*
        Genera la fecha de nacimiento aleatoria de una persona.
        Para evitar problemas con meses bisiesto o meses de 30 o 31 días,
        se ha optado por que el día máximo sea 28
     */
    private static String birthDateGenerator() {
        int day = dataRandomGenerator.nextInt(28) + 1;
        int month = dataRandomGenerator.nextInt(12) + 1;
        int year = dataRandomGenerator.nextInt(31) + 1970;

        return String.format("%02d/%02d/%d", day, month, year);
    }

    /*
        Genera una dirección añadiendo el índice de su creción
     */
    private static String adrressGenerator(int index) {
        return "St: somewhere nº " + dataRandomGenerator.nextInt(100) + 1;
    }

    /*
        Genera el apellido de la persona añadiendo el índice de su creación
     */
    private static String lastNameGenerator(int index) {

        return "last Name " + dataRandomGenerator.nextInt(100) + 1;
    }

    /*
        Genera el nombre de la persona, añadiendo el índice de su creación
     */
    private static String nameGenerator(int index) {

        return "Named " + dataRandomGenerator.nextInt(100) + 1;
    }

    /*
        Devuelve un boleano para determinar si la persona
        es deudora o no.
     */
    private static boolean isDebetorGenerate() {
        return dataRandomGenerator.nextBoolean();
    }

    /*
        Crea un "código postal" aleatorio entre 1000 y 1999
     */
    private static int postalCodeGenerate() {
        return dataRandomGenerator.nextInt(1000) + 1000;
    }

    /*
        Genera una cantidad entre 0 y 499 de dinero debido.
     */
    private static int owedMoneyGenerate() {
        return dataRandomGenerator.nextInt(500);
    }

    private static void integerValidator(int numberToValidate) {
        if (numberToValidate < 1) {
            throw new IllegalArgumentException(numberToValidate + " no es un valor válido");
        }
    }
}
