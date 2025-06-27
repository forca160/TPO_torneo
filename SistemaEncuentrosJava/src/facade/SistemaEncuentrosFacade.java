package facade;

import entities.Usuario;
import entities.Deporte;
import entities.Encuentro;
import entities.EstadisticasPartido;
import entities.NivelJuego;
import entities.Posicion;
import services.GestorUsuarios;
import state.NecesitamosJugadores;
import services.GestorEncuentros;
import strategy.BuscadorEncuentros;
import java.time.LocalDateTime;
import java.util.List;

public class SistemaEncuentrosFacade {
    private GestorUsuarios gestorUsuarios = new GestorUsuarios();
    private GestorEncuentros gestorEncuentros = new GestorEncuentros();
    private BuscadorEncuentros buscador = new BuscadorEncuentros();

    public Usuario registrarUsuario(String usuario, String email, String contraseña) {
        Usuario u = new Usuario(usuario, email, contraseña, null, null, null);
        gestorUsuarios.registrar(u);
        return u;
    }

    public Encuentro crearEncuentro(Usuario u, Deporte deporte, int jugadores, int duracion,
            String ubicacion, LocalDateTime horario) {
        return null;
        // Delegate to gestorEncuentros
    }

    public List<entities.Encuentro> buscarEncuentros(Usuario u, TipoBusqueda tipo) {
        // Set strategy and search
        return null;
    }

    public void unirseEncuentro(Usuario u, String encuentroId) {
        // Delegate
    }

    public void confirmarParticipacion(Usuario u, String encuentroId) {
        // Delegate
    }

    public void finalizarEncuentro(String encuentroId, EstadisticasPartido stats) {
        // Delegate
    }
}
