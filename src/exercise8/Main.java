package exercise8;

import java.io.*;

public class Main {

    public static void main(String[] args) throws IOException {
        Agenda agenda = new Agenda();
        File ruta = new File("src\\Exercise8\\agenda\\FicheroEmpleados.dat");

        if(ruta.exists()) {
            ObjectInputStream objectIS = new ObjectInputStream(new FileInputStream(ruta));

            try {

                while(true) {
                    agenda.addRegistro((Registro) objectIS.readObject());
                }

            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Fichero cargado");
            }

            objectIS.close();

            System.out.println("hola");
            agenda.showAgenda();

        } else {
            try {
                ruta.createNewFile();
                /*
                    public Registro(int telefono, String direccion, int codigoPostal, String fechaNacimiento, boolean deberDinero, int dineroDebido) {

                 */

                agenda.addRegistro(new Registro(6743345, "algun sitio", 232, "19/08/72", false, 0));
                agenda.addRegistro(new Registro(2342362, "En mi casa", 976, "20/19/27", true, 100));
                ObjectOutputStream objectOS = new ObjectOutputStream(new FileOutputStream(ruta));

                agenda.getRegistro().forEach(registro -> {
                    try {
                        objectOS.writeObject(registro);
                    } catch (IOException e) {
                        System.out.println("No se ha registrado");
                    }
                });

                objectOS.close();
            } catch (IOException e) {
                System.out.println("No se ha podido crear el fichero para almacenar los registros.");
            }
        }
    }
}
