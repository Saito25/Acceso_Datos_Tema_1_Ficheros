package exercise9;

import exercise8.Registro;

import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class BaseDatosEmpleados {

    private int index = 0;
    private int writedUTFCharsSize = 0;
    private boolean readInUTF = true; // Si esta variable está en false, se usará readChar()

    private final String newRegistries = "src\\exercise9\\ficheros\\RegistrosEmpleados.dat";
    private final Scanner keyboard = new Scanner(System.in);

    public BaseDatosEmpleados() throws IOException {
        if (isANewFile()) {
            System.out.println("Base de datos lista.");
        } else {
            createRegistrosEmpleadosFromAgenda();
        }

        showOptionMenu();
    }

    private void showOptionMenu() throws IOException {
        System.out.println(
                "¿Qué desea hacer?\n" +
                        "1.- Consultar todos los registros\n" +
                        "2.- Consultar un registro específico\n");

        selectOptionMenu();
    }

    private void selectOptionMenu() throws IOException {
        int i = 0;

        do {
            try {
                i = keyboard.nextInt();
            } catch (InputMismatchException im) {
                System.out.println("Opción no válida");
            }

            if (i > 6 || i < 1) {
                System.out.println("Opción no válida");
            }

        } while (i > 6 || i < 1);

        switch (i) {
            case 1:
                showAllRegistries();
                break;
            case 2:
                consultRegistry();
                break;
            case 3:
                break;
            case 4:
                break;
            case 5:
                break;
            case 6:
                break;
        }
    }

    private void showAllRegistries() {

        try (DataInputStream binaryFileReader = new DataInputStream(new FileInputStream(newRegistries))) {

            while (true) {
                if (!binaryFileReader.readBoolean()) {
                    System.out.printf("id: %d, teléfono %d, dirección: %s, código postal: %d " +
                                    "fecha nacimiento: %s, tiene deuda pendiente: %s\n",
                            binaryFileReader.readInt(),
                            binaryFileReader.readInt(),
                            showTextInfoFormated(binaryFileReader, 25),
                            binaryFileReader.readInt(),
                            showTextInfoFormated(binaryFileReader, 10),
                            showIfDebtor(binaryFileReader.readBoolean(), binaryFileReader.readInt())
                    );
                    index++;
                }
            }
        } catch (IOException e) {
            System.out.println("Fichero leído");
        }
    }

    private void consultRegistry() throws IOException {
        RandomAccessFile agenda = new RandomAccessFile(newRegistries, "r");
        System.out.println(agenda.length());

        System.out.println("Introduce un ID de registro para ver los datos");

        int registryPosition;
        int registryId = keyboard.nextInt();

        if(readInUTF) {
            registryPosition = (registryId - 1) * 73;
        } else {
            registryPosition = (registryId - 1) * 88;
        }

        if (registryPosition >= agenda.length()) {
            System.out.println("No existe el fichero especificado");
        } else {
            agenda.seek(registryPosition);
            if (!agenda.readBoolean()) {
                System.out.printf("id: %d, teléfono %d, dirección: %s, código postal: %d " +
                                "fecha nacimiento: %s, tiene deuda pendiente: %s\n",
                        agenda.readInt(),
                        agenda.readInt(),
                        showTextInfoFormated(agenda, 25),
                        agenda.readInt(),
                        showTextInfoFormated(agenda, 10),
                        showIfDebtor(agenda.readBoolean(), agenda.readInt())
                );
            }
            System.out.println(agenda.getFilePointer());
        }
    }

    private String showTextInfoFormated(DataInputStream binaryFileReader, int length) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            if (readInUTF) {
                stringBuilder.append(binaryFileReader.readUTF());
            } else {
                for (int i = 0; i < length; i++) {
                    stringBuilder.append(binaryFileReader.readChar());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString().trim();
    }

    private String showTextInfoFormated(RandomAccessFile randomAccessFile, int length) {
        StringBuilder stringBuilder = new StringBuilder();

        try {
            if (readInUTF) {
                stringBuilder.append(randomAccessFile.readUTF());
                System.out.println(randomAccessFile.getFilePointer());
            } else {
                for (int i = 0; i < length; i++) {
                    stringBuilder.append(randomAccessFile.readChar());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString().trim();
    }

    private String showIfDebtor(boolean readBoolean, int readInt) {
        return readBoolean ? "sí, debe: " + readInt : "no debe nada";
    }

    private boolean isANewFile() {
        return new File(newRegistries).exists();
    }

    private void createRegistrosEmpleadosFromAgenda() throws IOException {
        String oldRegistries = "src\\exercise8\\agenda\\FicheroEmpleados.dat";
        String formatString;

        // Leemos objetos y escribimnos en el fichero RegistrosEmpleados
        // con los datos pertinentes agregados.

        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(oldRegistries));
             DataOutputStream binaryFileWriter = new DataOutputStream(new FileOutputStream(newRegistries))) {
            while (true) {
                // La suma total de los dato es:
                // 4 bytes + 1 byte + 4 bytes + 50 bytes + 4 bytes + 20 bytes + 1 byte + 4 bytes:
                // 88 bytes
                // 4 bytes + 1 byte + 4 bytes + 27 bytes + 4 bytes + 12 bytes + 1 byte + 4 bytes:
                // 57 bytes
                // 73 - (4 + 1+ 4 + 4 + 1 + 4): 73 - 18 : 53
                Registro registro = (Registro) objectInputStream.readObject();
                binaryFileWriter.writeBoolean(false);
                binaryFileWriter.writeInt(++index);
                binaryFileWriter.writeInt(registro.getTelefono());

                formatString = formatStringBuffer(registro.getDireccion(), 25);
                if (readInUTF) {
                    binaryFileWriter.writeUTF(formatString);
                } else {
                    binaryFileWriter.writeChars(formatString);
                }

                binaryFileWriter.writeInt(registro.getCodigoPostal());

                formatString = formatStringBuffer(registro.getFechaNacimiento(), 10);
                if (readInUTF) {
                    binaryFileWriter.writeUTF(formatString);
                } else {
                    binaryFileWriter.writeChars(formatString);
                }

                binaryFileWriter.writeBoolean(registro.isDeberDinero());
                binaryFileWriter.writeInt(registro.getDineroDebido());

            }
        } catch (EOFException eo) {
            System.out.println("Base de datos cargada");
        } catch (ClassNotFoundException e) {
            System.err.println("No se pudo cargar la base de datos");
            e.getStackTrace();
            System.exit(1);
        }
    }

    private String formatStringBuffer(String string, int i) {
        StringBuffer stringBuffer = new StringBuffer(string);
        stringBuffer.setLength(i);
        return stringBuffer.toString();
    }

} // Fin clase
