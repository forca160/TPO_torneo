package services;

import entities.Deporte;
import entities.Encuentro;
import entities.NivelJuego;
import entities.Posicion;
import entities.Usuario;
import state.Confirmado;
import state.EnJuego;
import state.EstadoPartido;
import state.NecesitamosJugadores;
import state.PartidoArmado;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class GestorEncuentros {
    private static List<Encuentro> encuentros = new ArrayList<>();
    private static GestorEncuentros instancia = null;

    private GestorEncuentros() {

    }

    public static GestorEncuentros obtenerInstancia() {
        if (instancia == null) {
            instancia = new GestorEncuentros();
        }

        return instancia;
    }

    public static List<Encuentro> getEncuentros() {
        return encuentros;
    }

    public Encuentro crear(String titulo, Deporte deporte, int cantidadJugadoresNecesarios, int duracionMinutos,
            Posicion ubicacion, LocalDateTime horario, Usuario organizador,
            NivelJuego nivelMinimo, NivelJuego nivelMaximo, boolean permitirCualquierNivel) {

        Encuentro e = new Encuentro(UUID.randomUUID().toString(), titulo, deporte, cantidadJugadoresNecesarios,
                duracionMinutos, ubicacion, horario,
                organizador, nivelMinimo, nivelMaximo, permitirCualquierNivel);
        encuentros.add(e);
        NecesitamosJugadores nj = new NecesitamosJugadores(e);
        e.cambiarEstado(nj);

        GestorUsuarios gu = GestorUsuarios.obtenerInstancia();
        List<Usuario> us = gu.getUsuarios();

        for (Usuario usuario : us) {
            if (usuario.getDeporteFavorito() == e.getDeporte()) {
                gu.notificar(e, usuario);
            }
        }

        return e;
    }

    public Encuentro buscarPorId(String id) {
        return encuentros.stream()
                .filter(e -> e.getId().equals(id))
                .findFirst().orElse(null);
    }

    public void confirmarParticipacion(Usuario u, Encuentro e) {
        e.confirmarParticipacion(u);
        if (e.getEstado() instanceof Confirmado) {
            programarCambioEstado(e);
        }
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
    public void finalizarEncuentro(Encuentro e) {
        e.cambiarEstado(new state.Finalizado(e));
    }

    public void unirseEncuentro(Encuentro e, Usuario u) {
        e.unirseAlPartido(u);
        if (e.getCantidadJugadoresNecesarios() == e.getParticipantes().size()) {
            EstadoPartido ep = new PartidoArmado(e);
            e.cambiarEstado(ep);
        }
    }

    public void jugarEncuentro(Encuentro e) {
        EstadoPartido ep = new EnJuego(e);
        e.cambiarEstado(ep);
        programarCambioEstadoDuracion(e);
    }

    public void programarCambioEstado(Encuentro e) {
        // Calcula el retraso en milisegundos desde ahora hasta la fecha del evento
        long delay = Duration.between(LocalDateTime.now(), e.getHorario()).toMillis();
        if (delay < 0) {
            // Si ya pasó la fecha, actualiza inmediatamente
            if (e.getEstado() instanceof Confirmado) {
                jugarEncuentro(e);
            } else if (e.getEstado() instanceof PartidoArmado || e.getEstado() instanceof NecesitamosJugadores) {
                GestorUsuarios gestorUsuarios = GestorUsuarios.obtenerInstancia();
                gestorUsuarios.cancelarEncuentro(e);
            }
            return;
        }

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> {
            try {
                if (e.getEstado() instanceof Confirmado) {
                    jugarEncuentro(e);
                } else if (e.getEstado() instanceof PartidoArmado || e.getEstado() instanceof NecesitamosJugadores) {
                    GestorUsuarios gestorUsuarios = GestorUsuarios.obtenerInstancia();
                    gestorUsuarios.cancelarEncuentro(e);
                }
            } finally {
                scheduler.shutdown();
            }
        }, delay, TimeUnit.MILLISECONDS);
    }

    public void programarCambioEstadoDuracion(Encuentro e) {
        // Calcula el retraso en milisegundos desde ahora hasta la fecha del evento
        long delay = Duration.between(LocalDateTime.now(), e.getHorario().plusMinutes(e.getDuracion())).toMillis();
        if (delay < 0) {
            // Si ya pasó la fecha, actualiza inmediatamente
            if (e.getEstado() instanceof EnJuego) {
                finalizarEncuentro(e);
            }
            return;
        }

        ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();
        scheduler.schedule(() -> {
            try {
                if (e.getEstado() instanceof EnJuego) {
                    finalizarEncuentro(e);
                }
            } finally {
                scheduler.shutdown();
            }
        }, delay, TimeUnit.MILLISECONDS);
    }

    public List<Encuentro> buscarPorOrganizador(Usuario u) {
        List<Encuentro> es = encuentros.stream().filter(e -> u.equals(e.getOrganizador())).collect(Collectors.toList());
        return es;
    }

}
