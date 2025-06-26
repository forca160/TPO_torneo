package strategy;

import entities.Encuentro;
import entities.Usuario;
import java.util.List;
import java.util.ArrayList;

public class BusquedaPorNivel implements EstrategiaBusqueda {

    @Override
    public List<Encuentro> buscarEncuentros(Usuario usuario) {
        NivelJuego nivelUsuario = usuario.getNivel();

        // Acá filtrarías los encuentros disponibles según el nivel
        return EncuentroRepositorio.getEncuentros().stream()
                .filter(e -> e.getNivelMinimo().compareTo(nivelUsuario) <= 0 &&
                             e.getNivelMaximo().compareTo(nivelUsuario) >= 0)
                .collect(Collectors.toList());
    }
}