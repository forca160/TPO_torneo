package entities;

import state.EstadoPartido;
import observer.Observer;
import observer.TipoNotificacion;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

public class Encuentro implements observer.Subject {
    private String id;
    private Deporte deporte;
    private int cantidadJugadoresNecesarios;
    private int duracionMinutos;
    private String ubicacion;
    private LocalDateTime horario;
    private Usuario organizador;
    private List<Usuario> participantes = new ArrayList<>();
    private int cantidadConfirmaciones;
    private EstadoPartido estado;
    private NivelJuego nivelMinimo;
    private NivelJuego nivelMaximo;
    private boolean permitirCualquierNivel;
    private EstadisticasPartido estadisticas;
    private List<Observer> observadores = new ArrayList<>();

    public void crearEncuentro() {
        // Logic to create encounter
    }

    public void unirseAlPartido(Usuario usuario) {
        if (estado.puedeUnirse()) {
            participantes.add(usuario);
            cantidadConfirmaciones++;
        }
    }

    public void confirmarParticipacion() {
        if (estado.puedeConfirmar()) {
            cantidadConfirmaciones++;
        }
    }

    public String getId() {
        return this.id;
    }

    public void cambiarEstado(EstadoPartido nuevoEstado) {
        this.estado = nuevoEstado;
        estado.manejarCambioEstado();
    }

    public boolean verificarCapacidad() {
        return participantes.size() <= cantidadJugadoresNecesarios;
    }

    @Override
    public void agregarObserver(Observer obs) {
        observadores.add(obs);
    }

    @Override
    public void removerObserver(Observer obs) {
        observadores.remove(obs);
    }

    @Override
    public void notificar(TipoNotificacion tipo) {
        for (Observer obs : observadores) {
            obs.actualizar(tipo, this);
        }
    }
}
