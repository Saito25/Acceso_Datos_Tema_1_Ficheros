package exercise8;

import java.io.Serializable;

public class Registro implements Serializable {

    private static final long serialVersionUID = 1L;

    private int telefono;
    private String direccion;
    private int codigoPostal;
    private String fechaNacimiento;
    private boolean deberDinero;
    private int dineroDebido;

    public Registro(int telefono, String direccion, int codigoPostal, String fechaNacimiento, boolean deberDinero, int dineroDebido) {
        this.telefono = telefono;
        this.direccion = direccion;
        this.codigoPostal = codigoPostal;
        this.fechaNacimiento = fechaNacimiento;
        this.deberDinero = deberDinero;
        this.dineroDebido = dineroDebido;
    }


    @Override
    public String toString() {
        return "Registro{" +
                "telefono=" + telefono +
                ", direccion='" + direccion + '\'' +
                ", codigoPostal=" + codigoPostal +
                ", fechaNacimiento='" + fechaNacimiento + '\'' +
                ", deberDinero=" + deberDinero +
                ", dineroDebido=" + dineroDebido +
                '}';
    }

    public int getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public int getCodigoPostal() {
        return codigoPostal;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }

    public boolean isDeberDinero() {
        return deberDinero;
    }

    public int getDineroDebido() {
        return dineroDebido;
    }
}
