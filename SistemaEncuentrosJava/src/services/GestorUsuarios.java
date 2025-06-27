package services;

import entities.Encuentro;
import entities.Usuario;
import state.Cancelado;
import state.EstadoPartido;

import observer.Observer;

import java.util.List;
import java.util.ArrayList;

public class GestorUsuarios {
    private List<Usuario> usuarios = new ArrayList<>();
    private List<Observer> observadores = new ArrayList<>();
    private static GestorUsuarios instancia = null;

    private GestorUsuarios() {

    }

    public static GestorUsuarios obtenerInstancia() {
        if (instancia == null) {
            instancia = new GestorUsuarios();
        }

        return instancia;
    }

    public void registrar(Usuario usuario) {
        this.usuarios.add(usuario);
        this.agregarObserver(usuario);
    }

    public List<Usuario> getUsuarios() {
        return this.usuarios;
    }

    public Usuario autenticar(String email, String contraseña) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equals(email) && u.getContraseña().equals(contraseña))
                .findFirst().orElse(null);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarios.stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst().orElse(null);
    }

    public List<entities.Encuentro> obtenerHistorialPartidos(Usuario usuario) {
        return usuario.obtenerHistorialPartidos();
    }

    public void cancelarEncuentro(Encuentro e) {
        EstadoPartido cancel = new Cancelado(e);
        e.cambiarEstado(cancel);
    }

    public void agregarObserver(Observer obs) {
        observadores.add(obs);
    }

    public void removerObserver(Observer obs) {
        observadores.remove(obs);
    }

    public void notificar(Encuentro e, Observer obs) {
        obs.actualizar(e);
    }
}
