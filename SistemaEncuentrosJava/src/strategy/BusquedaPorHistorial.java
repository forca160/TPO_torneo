package strategy;

import entities.Encuentro;
import entities.Usuario;
import services.GestorEncuentros;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class BusquedaPorHistorial implements EstrategiaBusqueda {

    @Override
    public List<Encuentro> buscarEncuentros(Usuario usuario) {
        List<Usuario> jugadoresPrevios = obtenerJugadoresPrevios(usuario);

        return GestorEncuentros.getEncuentros().stream()
                .filter(e -> e.getParticipantes().stream().anyMatch(jugadoresPrevios::contains))
                .collect(Collectors.toList());
    }

    public List<Usuario> obtenerJugadoresPrevios(Usuario usuario) {
    return usuario.obtenerHistorialPartidos().stream()
            .flatMap(e -> e.getParticipantes().stream())
            .filter(u -> !u.equals(usuario)) // excluís a sí mismo
            .distinct()
            .collect(Collectors.toList());
}

}