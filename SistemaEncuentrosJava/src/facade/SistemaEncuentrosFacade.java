// SistemaEncuentrosFacade.java
package facade;

import entities.Usuario;
import entities.Deporte;
import entities.Encuentro;
import entities.Encuentro;
import entities.EstadisticasPartido;
import entities.NivelJuego;
import entities.Encuentro;
import entities.Posicion;
import services.GestorUsuarios;
import state.EstadoPartido;
import state.NecesitamosJugadores;
import state.PartidoArmado;
import services.GestorEncuentros;
import strategy.BuscadorEncuentros;
import facade.TipoBusqueda;
import strategy.BusquedaPorCercania;
import strategy.BusquedaPorNivel;
import strategy.BusquedaPorHistorial;

import java.time.LocalDateTime;
import java.util.List;

public class SistemaEncuentrosFacade {
    private GestorUsuarios gestorUsuarios = new GestorUsuarios();
    private GestorEncuentros gestorEncuentros = new GestorEncuentros();
    private BuscadorEncuentros buscador = new BuscadorEncuentros();

    public Usuario registrarUsuario(String usuario, String email, String contrasena, Deporte deporte, NivelJuego nivel,
            Posicion ubicacion) {
        Usuario u = new Usuario(usuario, email, contrasena, deporte, nivel, ubicacion);
        gestorUsuarios.registrar(u);
        return u;
    }

    public Encuentro crearEncuentro(String titulo, Deporte deporte, int cantidadJugadoresNecesarios,
            int duracionMinutos,
            Posicion ubicacion, LocalDateTime horario, Usuario organizador,
            NivelJuego nivelMinimo, NivelJuego nivelMaximo, boolean permitirCualquierNivel) {
        return gestorEncuentros.crear(titulo, deporte, cantidadJugadoresNecesarios, duracionMinutos, ubicacion, horario,
                organizador, nivelMinimo, nivelMaximo, permitirCualquierNivel);
    }

    public List<Encuentro> buscarEncuentros(Usuario u, TipoBusqueda tipo) {
        switch (tipo) {
            case POR_NIVEL -> buscador.setEstrategia(new BusquedaPorNivel());
            case POR_CERCANIA -> buscador.setEstrategia(new BusquedaPorCercania(10));
            case POR_HISTORIAL -> buscador.setEstrategia(new BusquedaPorHistorial());
        }
        return buscador.buscar(u);
    }

    public void unirseEncuentro(Usuario u, String encuentroId) {
        Encuentro e = gestorEncuentros.buscarPorId(encuentroId);
        if (e != null) {
            e.unirseAlPartido(u);
        }
        gestorEncuentros.unirseEncuentro(e, u);

    }

    public void confirmarParticipacion(Usuario u, String encuentroId) {
        Encuentro e = gestorEncuentros.buscarPorId(encuentroId);
        if (e != null) {
            e.confirmarParticipacion(u);
        }
    }

    public void finalizarEncuentro(String encuentroId, EstadisticasPartido stats) {
        Encuentro e = gestorEncuentros.buscarPorId(encuentroId);
        if (e != null) {
            gestorEncuentros.finalizarEncuentro(e, stats);
        }

    }

    public Encuentro obtenerEncuentro(String id) {
        return gestorEncuentros.buscarPorId(id);
    }

}
