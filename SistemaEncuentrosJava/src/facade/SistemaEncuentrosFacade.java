// SistemaEncuentrosFacade.java
package facade;

import entities.Usuario;
import entities.Deporte;
import entities.Encuentro;
import entities.EstadisticasPartido;
import entities.NivelJuego;
import entities.Posicion;
import services.GestorUsuarios;
import state.Confirmado;
import state.EnJuego;
import state.EstadoPartido;
import services.GestorEncuentros;
import strategy.BuscadorEncuentros;
import strategy.BusquedaPorCercania;
import strategy.BusquedaPorNivel;
import strategy.BusquedaPorHistorial;

import java.time.LocalDateTime;
import java.util.List;

import adapter.AdapterFirebase;
import adapter.AdapterJavaMail;
import adapter.FirebaseService;
import adapter.JavaMailService;
import adapter.Notificador;
import adapter.TipoDeEnvio;

public class SistemaEncuentrosFacade {
    private static GestorUsuarios gestorUsuarios = GestorUsuarios.obtenerInstancia();
    private static GestorEncuentros gestorEncuentros = GestorEncuentros.obtenerInstancia();
    private BuscadorEncuentros buscador = new BuscadorEncuentros();

    public Usuario registrarUsuario(String usuario, String email, String contrasena, Deporte deporte, NivelJuego nivel,
            Posicion ubicacion, TipoDeEnvio tipoDeEnvio) {
        Usuario u = new Usuario(usuario, email, contrasena, deporte, nivel, ubicacion);
        Notificador notificador = new Notificador();
        if (tipoDeEnvio == TipoDeEnvio.PUSH) {
            FirebaseService firebaseService = new FirebaseService();
            AdapterFirebase adapterFirebase = new AdapterFirebase(firebaseService);
            notificador.setServicio(adapterFirebase);
        } else if (tipoDeEnvio == TipoDeEnvio.MAIL) {
            JavaMailService javaMailService = new JavaMailService();
            AdapterJavaMail adapterJavaMail = new AdapterJavaMail(javaMailService);
            notificador.setServicio(adapterJavaMail);
        }
        u.setNotificador(notificador);
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
            gestorEncuentros.unirseEncuentro(e, u);
        }

    }

    public void confirmarParticipacion(Usuario u, String encuentroId) {
        Encuentro e = gestorEncuentros.buscarPorId(encuentroId);
        if (e != null) {
            gestorEncuentros.confirmarParticipacion(u, e);
        }
    }

    public List<Encuentro> buscaEncuentrosPorOrganizador(Usuario u) {
        List<Encuentro> e = gestorEncuentros.buscarPorOrganizador(u);
        if (e.size() > 0) {
            return e;
        }
        return null;
    }

    public void finalizarEncuentro(String encuentroId) {
        Encuentro e = gestorEncuentros.buscarPorId(encuentroId);
        if (e != null && e.getEstado() instanceof EnJuego) {
            gestorEncuentros.finalizarEncuentro(e);
        }

    }

    public boolean cancelarEncuentro(String id) {
        Encuentro e = gestorEncuentros.buscarPorId(id);

        gestorUsuarios.cancelarEncuentro(e);

        return true;
    }

    public Encuentro obtenerEncuentro(String id) {
        return gestorEncuentros.buscarPorId(id);
    }

    public void empezarEncuentro(String id) {
        Encuentro e = gestorEncuentros.buscarPorId(id);
        if (e != null && e.getEstado() instanceof Confirmado) {
            gestorEncuentros.jugarEncuentro(e);
        }
    }

    public boolean verificarConfirmacion(String id) {
        Encuentro e = gestorEncuentros.buscarPorId(id);
        if (e == null) {
            return false;
        }
        EstadoPartido es = e.getEstado();

        return es.puedeConfirmar();
    }

    public boolean verificarUnion(String id) {
        Encuentro e = gestorEncuentros.buscarPorId(id);
        if (e == null) {
            return false;
        }
        EstadoPartido es = e.getEstado();

        return es.puedeUnirse();
    }
}
