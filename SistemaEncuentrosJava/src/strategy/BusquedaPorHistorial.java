package strategy;

import entities.Encuentro;
import entities.Usuario;
import java.util.List;
import java.util.ArrayList;

public class BusquedaPorHistorial implements EstrategiaBusqueda {
    @Override
    public List<Encuentro> buscarEncuentros(Usuario usuario) {
        return new ArrayList<>();
    }

    public List<Usuario> obtenerJugadoresPrevios(Usuario usuario) {
        return new ArrayList<>();
    }
}
