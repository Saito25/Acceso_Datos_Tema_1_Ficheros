package exercise8;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Agenda implements Serializable {

    private static final long serialVersionUID = 1L;

    List<Registro> registros = new ArrayList<>();


    public Agenda() {

    };

    public void addRegistro(Registro registro) {
        registros.add(registro);
    }

    public void showAgenda() {
        registros.forEach(System.out::println);
    }

    public List<Registro> getRegistro() {
        return registros;
    }
}
