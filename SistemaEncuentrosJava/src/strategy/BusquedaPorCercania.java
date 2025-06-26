package strategy;

import entities.Encuentro;
import entities.Usuario;
import java.util.List;
import java.util.ArrayList;

public class BusquedaPorCercania implements EstrategiaBusqueda {

    private double radioKm = 10.0;

    @Override
    public List<Encuentro> buscarEncuentros(Usuario usuario) {
        String ubicacionUsuario = usuario.getUbicacion();

        return EncuentroRepositorio.getEncuentros().stream()
                .filter(e -> calcularDistancia(ubicacionUsuario, e.getUbicacion()) <= radioKm)
                .collect(Collectors.toList());
    }

    public double calcularDistancia(String ubi1, String ubi2) {
        // Lógica ficticia de distancia (ejemplo)
        return 5.0; // en kilómetros
    }
}