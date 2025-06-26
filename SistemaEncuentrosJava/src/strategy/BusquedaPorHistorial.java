package strategy;

import entities.Encuentro;
import entities.Usuario;
import java.util.List;
import java.util.ArrayList;

public class BusquedaPorHistorial implements EstrategiaBusqueda {

    @Override
    public List<Encuentro> buscarEncuentros(Usuario usuario) {
        List<Usuario> frecuentes = obtenerJugadoresPrevios(usuario);

        return EncuentroRepositorio.getEncuentros().stream()
                .filter(e -> e.getParticipantes().stream().anyMatch(frecuentes::contains))
                .collect(Collectors.toList());
    }

    public List<Usuario> obtenerJugadoresPrevios(Usuario usuario) {
        // Buscar jugadores con los que jugó antes
        return HistorialRepositorio.getEncuentrosPasados(usuario).stream()
                .flatMap(e -> e.getParticipantes().stream())
                .distinct()
                .collect(Collectors.toList());
    }
}