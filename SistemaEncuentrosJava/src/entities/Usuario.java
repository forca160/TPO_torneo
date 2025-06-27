package entities;

import adapter.AdapterJavaMail;
import adapter.Notificador;
import adapter.ServicioNotificacion;
import adapter.Notificador;
import observer.Notificacion;
import observer.Observer;
import observer.TipoNotificacion;
import java.util.List;
import java.util.ArrayList;

public class Usuario implements Observer {
    private String usuario;
    private String email;
    private String contraseña;
    private Deporte deporteFavorito;
    private NivelJuego nivel;
    private Posicion ubicacion;
    private Notificador notificador;

    public Usuario(String usuario, String email, String contraseña, Deporte deporteFavorito,
            NivelJuego nivel, Posicion ubicacion) {
        this.usuario = usuario;
        this.email = email;
        this.contraseña = contraseña;
        this.deporteFavorito = deporteFavorito;
        this.nivel = nivel;
        this.ubicacion = ubicacion;
        this.notificador = new Notificador();
    }

    public Deporte getDeporteFavorito() {
        return deporteFavorito;
    }

    public Posicion getUbicacion() {
        return ubicacion;
    }

    public NivelJuego getNivel() {
        return this.nivel;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public String getEmail() {
        return this.email;
    }

    public String getContraseña() {
        return this.contraseña;
    }

    public void registrarse() {
        // Implementation to register user
    }

    // Setters para los campos faltantes

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setDeporteFavorito(Deporte deporteFavorito) {
        this.deporteFavorito = deporteFavorito;
    }

    public void setNivel(NivelJuego nivel) {
        this.nivel = nivel;
    }

    public void setUbicacion(Posicion ubicacion) {
        this.ubicacion = ubicacion;
    }

    public void setNotificador(Notificador notificador) {
        this.notificador = notificador;
    }

    public List<Encuentro> obtenerHistorialPartidos() {
        // Retrieve match history
        return new ArrayList<>();
    }

    @Override
    public void actualizar(Encuentro encuentro) {
        String mensage = encuentro.getMensajeEstado(this.getUsuario());
        Notificacion notificacion = new Notificacion(this, mensage, encuentro);
        this.notificador.enviar(notificacion);
    }

    public void configurarNotificador(ServicioNotificacion serv) {
        this.notificador.setServicio(serv);
    }

}
