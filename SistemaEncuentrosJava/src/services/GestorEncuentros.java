package services;

import entities.Encuentro;
import java.util.List;
import java.util.ArrayList;

public class GestorEncuentros {
    private List<Encuentro> encuentros = new ArrayList<>();

    public void crear(Encuentro e) {
        encuentros.add(e);
    }

    public Encuentro buscarPorId(String id) {
        return encuentros.stream()
            .filter(e -> e.getId().equals(id))
            .findFirst().orElse(null);
    }

    public List<Encuentro> obtenerTodos() {
        return encuentros;
    }

    public void actualizarEstado(Encuentro e) {
        // Update logic
    }

    public void verificarEstadosAutomaticos() {
        // Automatic checks
    }

    public void notificarNuevoPartido(Encuentro e) {
        e.notificar(observer.TipoNotificacion.NUEVO_PARTIDO_DEPORTE_FAVORITO);
    }
}
