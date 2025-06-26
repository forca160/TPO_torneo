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
    private Posicion ubicacion;
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
    private static List<Encuentro> encuentros = new ArrayList<>();

    public void setId(String id) {
        this.id = id;
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public Posicion getUbicacion() {
        return ubicacion;
    }

    public List<Usuario> getParticipantes() {
        return participantes;
    }

    public static List<Encuentro> getEncuentros() {
        return encuentros;
    }

    public NivelJuego getNivelMinimo() {
        return nivelMinimo;
    }

    public NivelJuego getNivelMaximo() {
        return nivelMaximo;
    }   


    
    public static void agregarEncuentro(Encuentro e) {
         encuentros.add(e);
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

    public Deporte getDeporte() {
        return deporte;
    }

    public LocalDateTime getHorario() {
        return horario;
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
