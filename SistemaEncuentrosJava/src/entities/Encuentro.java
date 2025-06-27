package entities;

import state.EstadoPartido;
import state.Confirmado;
import observer.Observer;
import observer.TipoNotificacion;
import state.Cancelado;
import state.Confirmado;
import state.EnJuego;
import state.Finalizado;
import state.NecesitamosJugadores;
import state.PartidoArmado;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

public class Encuentro implements observer.Subject {
    private String id;
    private String titulo;
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
    private Set<Usuario> confirmados = new HashSet<>();

    public Encuentro(String id, String titulo, Deporte deporte, int cantidadJugadoresNecesarios, int duracionMinutos,
            Posicion ubicacion, LocalDateTime horario, Usuario organizador,
            NivelJuego nivelMinimo, NivelJuego nivelMaximo, boolean permitirCualquierNivel) {

        this.id = id;
        this.titulo = titulo;
        this.deporte = deporte;
        this.cantidadJugadoresNecesarios = cantidadJugadoresNecesarios;
        this.duracionMinutos = duracionMinutos;
        this.ubicacion = ubicacion;
        this.horario = horario;
        this.organizador = organizador;
        this.nivelMinimo = nivelMinimo;
        this.nivelMaximo = nivelMaximo;
        this.permitirCualquierNivel = permitirCualquierNivel;

        this.estado = null; // lo setearás con setEstado/cambiarEstado después
        this.participantes = new ArrayList<>();
        this.observadores = new ArrayList<>();
        this.cantidadConfirmaciones = 0;
        this.estadisticas = null;
    }

    public String getTitulo() {
        return this.titulo;
    }

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

    public NivelJuego getNivelMinimo() {
        return nivelMinimo;
    }

    public NivelJuego getNivelMaximo() {
        return nivelMaximo;
    }

    public int getCantidadConfirmaciones() {
        return cantidadConfirmaciones;
    }

    public int getCantidadJugadoresNecesarios() {
        return cantidadJugadoresNecesarios;
    }

    public void unirseAlPartido(Usuario usuario) {
        if (estado.puedeUnirse()) {
            participantes.add(usuario);
            cantidadConfirmaciones++;
        }
    }

    public void confirmarParticipacion(Usuario usuario) {
        if (estado.puedeConfirmar() && participantes.contains(usuario) && !confirmados.contains(usuario)) {
            confirmados.add(usuario);
            System.out.println(usuario.getUsuario() + " confirmó su participación");

            if (confirmados.size() == participantes.size()) {
                cambiarEstado(new Confirmado(this));
                notificar();
            }
        }
    }

    public void cambiarEstado(EstadoPartido nuevoEstado) {
        this.estado = nuevoEstado;
        estado.manejarCambioEstado();
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

    public EstadoPartido getEstado() {
        return estado;
    }

    public String getMensajeEstado(String usuario) {
        return this.estado.getMensage(usuario, this.getDeporte().getDescripcion(), this.getHorario());
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
    public void notificar() {
        for (Observer obs : observadores) {
            obs.actualizar(this);
        }
    }

    public String estadoComoTexto() {
        if (this.estado instanceof NecesitamosJugadores)
            return "Necesitamos jugadores";
        if (this.estado instanceof PartidoArmado)
            return "Partido armado";
        if (this.estado instanceof Confirmado)
            return "Confirmado";
        if (this.estado instanceof EnJuego)
            return "En juego";
        if (this.estado instanceof Finalizado)
            return "Finalizado";
        if (this.estado instanceof Cancelado)
            return "Cancelado";
        return estado.getClass().getSimpleName();
    }

}