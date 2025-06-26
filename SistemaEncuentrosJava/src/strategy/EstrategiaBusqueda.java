package strategy;

import entities.Encuentro;
import entities.Usuario;
import java.util.List;

public interface EstrategiaBusqueda {
    List<Encuentro> buscarEncuentros(Usuario usuario);
}
