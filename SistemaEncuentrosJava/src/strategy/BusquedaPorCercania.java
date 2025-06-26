package strategy;

import entities.Encuentro;
import entities.Usuario;
import java.util.List;
import java.util.ArrayList;

public class BusquedaPorCercania implements EstrategiaBusqueda {
    private double radioKm;
    public BusquedaPorCercania(double radioKm) { this.radioKm = radioKm; }

    @Override
    public List<Encuentro> buscarEncuentros(Usuario usuario) {
        return new ArrayList<>();
    }

    public double calcularDistancia(String origen, String destino) {
        return 0.0;
    }
}
