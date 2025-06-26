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
        if (estrategia == null) {
            throw new IllegalStateException("Estrategia de búsqueda no seteada");
        }
        return estrategia.buscarEncuentros(usuario);
    }
}