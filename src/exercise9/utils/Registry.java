package exercise9.utils;

import java.io.Serializable;

/**
 * Abstración de la representación de un registro de una agenda.
 * Contiene información y métodos relativos al registro.
 *
 * @author Manuel
 * @version 1
 */
public class Registry implements Serializable {

    // Atributo para determinar la versión actual del programa,
    // Necesario para serialización
    private static final long serialVersionUID = 1L;

    // Pila de atributos propios de los registros.
    // Se han añadido: nombre y apellido
    private int postalCode;
    private int owedMoney;
    private boolean isDebtor;
    private String name;
    private String lastName;
    private String address;
    private String birthDate;
    private String telephone;

    /**
     * Constructor de la clase. Crea una instancia común, con todos los
     * atributos, de la clase Registro.
     *
     * @param postalCode
     * @param owedMoney
     * @param isDebtors
     * @param name
     * @param lastName
     * @param adrress
     * @param birthDate
     * @param telephone
     */
    public Registry(int postalCode, int owedMoney, boolean isDebtors, String name,
                    String lastName, String adrress, String birthDate, String telephone) {
        this.postalCode = postalCode;
        this.owedMoney = owedMoney;
        this.isDebtor = isDebtors;
        this.name = name;
        this.lastName = lastName;
        this.address = adrress;
        this.birthDate = birthDate;
        this.telephone = telephone;
    }

    @Override
    public String toString() {
        return "Registro{" +
                "postalCode=" + postalCode +
                ", owedMoney=" + owedMoney +
                ", isDebtors=" + isDebtor +
                ", name='" + name + '\'' +
                ", lastName='" + lastName + '\'' +
                ", adrress='" + address + '\'' +
                ", birthDate='" + birthDate + '\'' +
                ", telephone='" + telephone + '\'' +
                '}';
    }

    /*
        Getters de la clase.
     */

    public int getPostalCode() {
        return postalCode;
    }

    public int getOwedMoney() {
        return owedMoney;
    }

    public boolean isDebtor() {
        return isDebtor;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public String getAddress() {
        return address;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public String getTelephone() {
        return telephone;
    }

}
