package strategy;

import entities.Encuentro;
import entities.NivelJuego;
import entities.Usuario;
import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class BusquedaPorNivel implements EstrategiaBusqueda {

    @Override
    public List<Encuentro> buscarEncuentros(Usuario usuario) {
        return Encuentro.getEncuentros().stream()
                .filter(e -> e.getNivelMinimo().compareTo(usuario.getNivel()) <= 0 &&
                             e.getNivelMaximo().compareTo(usuario.getNivel()) >= 0)
                .collect(Collectors.toList());
    }
}