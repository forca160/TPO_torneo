package strategy;

import entities.Encuentro;
import entities.Usuario;
import java.util.List;

public class BuscadorEncuentros {
    private EstrategiaBusqueda estrategia;

    public void setEstrategia(EstrategiaBusqueda estrategia) {
        this.estrategia = estrategia;
    }

    public List<Encuentro> buscar(Usuario usuario) {
        return estrategia.buscarEncuentros(usuario);
    }
}
