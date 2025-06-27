package services;

import entities.Deporte;
import entities.Encuentro;
import entities.NivelJuego;
import entities.Posicion;
import entities.Usuario;
import observer.TipoNotificacion;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class GestorEncuentros {
    private List<Encuentro> encuentros = new ArrayList<>();

    public void crear(String id, Deporte deporte, int cantidadJugadoresNecesarios, int duracionMinutos,
                 Posicion ubicacion, LocalDateTime horario, Usuario organizador,
                 NivelJuego nivelMinimo, NivelJuego nivelMaximo, boolean permitirCualquierNivel) {

        Encuentro e = new Encuentro(id, deporte, cantidadJugadoresNecesarios, duracionMinutos, ubicacion, horario, organizador, nivelMinimo, nivelMaximo, permitirCualquierNivel);
        encuentros.add(e);
        notificarNuevoPartido(e); // notifica apenas se crea, si querés
    }

    public Encuentro buscarPorId(String id) {
        return encuentros.stream()
            .filter(e -> e.getId().equals(id))
            .findFirst().orElse(null);
    }

    public List<Encuentro> obtenerTodos() {
        return encuentros;
    }

    // ✅ Llama al estado actual para que evalúe si debe cambiar
    public void actualizarEstado(Encuentro e) {
        if (e.getEstado() != null) {
            e.getEstado().manejarCambioEstado();
        }
    }

    // ✅ Recorre todos los encuentros para forzar actualización automática
    public void verificarEstadosAutomaticos() {
        LocalDateTime ahora = LocalDateTime.now();
        for (Encuentro e : encuentros) {
            if (e.getEstado() != null && e.getHorario() != null) {
                if (ahora.isAfter(e.getHorario())) {
                    e.getEstado().manejarCambioEstado(); // el propio estado evalúa si debe cambiar
                }
            }
        }
    }

    public void notificarNuevoPartido(Encuentro e) {
        e.notificar();
    }

    // ✅ Podés agregar esto si querés forzar el cambio de estado desde el facade
    public void finalizarEncuentro(Encuentro e, entities.EstadisticasPartido stats) {
        e.cambiarEstado(new state.Finalizado(e));
        // podrías guardar stats si hace falta
    }
}

