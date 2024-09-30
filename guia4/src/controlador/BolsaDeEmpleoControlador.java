package controlador;

import modelo.Aspirante;
import modelo.BolsaDeEmpleo;
import java.util.List;
//clase que enlaza la logica de del modelo con la interfaz grafica y obtiene los datos de la base de datos
public class BolsaDeEmpleoControlador {
    private BolsaDeEmpleo bolsaDeEmpleo;

    public BolsaDeEmpleoControlador() {
        bolsaDeEmpleo = new BolsaDeEmpleo();
    }

    public boolean agregarAspirante(Aspirante aspirante) {
        return bolsaDeEmpleo.agregarAspirante(aspirante);
    }

    public List<Aspirante> obtenerAspirantes() {
        return bolsaDeEmpleo.obtenerAspirantes();
    }

    public Aspirante buscarAspirantePorCedula(String cedula) {
        return bolsaDeEmpleo.buscarAspirantePorCedula(cedula);
    }

    public boolean eliminarAspirante(String cedula) {
        return bolsaDeEmpleo.eliminarAspirante(cedula);
    }

    public double calcularPromedioEdad() {
        return bolsaDeEmpleo.calcularPromedioEdad();
    }

    public List<Aspirante> ordenarPorExperiencia() {
        return bolsaDeEmpleo.ordenarPorExperiencia();
    }
}
