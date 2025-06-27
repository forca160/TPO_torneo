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
        // Lógica dummy por ahora (debería usar Estadísticas o historial real)
        return List.of();  // TODO: implementar correctamente si se necesita
    }
}