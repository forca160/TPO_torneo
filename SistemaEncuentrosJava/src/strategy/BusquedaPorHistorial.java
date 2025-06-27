package strategy;

import entities.Encuentro;
import entities.Usuario;
import services.GestorEncuentros;

import java.util.List;
import java.util.stream.Collectors;

public class BusquedaPorHistorial implements EstrategiaBusqueda {

    @Override
    public List<Encuentro> buscarEncuentros(Usuario usuario) {
        List<Usuario> jugadoresPrevios = obtenerJugadoresPrevios(usuario);

        return GestorEncuentros.getEncuentros().stream()
                .filter(e -> e.getParticipantes().stream().anyMatch(jugadoresPrevios::contains))
                .collect(Collectors.toList());
    }

    public List<Usuario> obtenerJugadoresPrevios(Usuario usuario) {
<<<<<<< HEAD
    return usuario.obtenerHistorialPartidos().stream()
            .flatMap(e -> e.getParticipantes().stream())
            .filter(u -> !u.equals(usuario)) // excluís a sí mismo
            .distinct()
            .collect(Collectors.toList());
}

=======
        // Lógica dummy por ahora (debería usar Estadísticas o historial real)
        return List.of(); // TODO: implementar correctamente si se necesita
    }
>>>>>>> 1927a72517b254fe4fd54254492d49528780f1de
}