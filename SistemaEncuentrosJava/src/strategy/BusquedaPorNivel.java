package strategy;

import entities.Encuentro;
import entities.Usuario;
import java.util.List;
import java.util.ArrayList;

public class BusquedaPorNivel implements EstrategiaBusqueda {
    @Override
    public List<Encuentro> buscarEncuentros(Usuario usuario) {
        return new ArrayList<>();
    }
}
