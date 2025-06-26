package entities;

import adapter.AdapterJavaMail;
import adapter.Notificador;
import adapter.ServicioNotificacion;
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
    private String ubicacion;
    private Notificador notificador;

    public Usuario(String usuario, String email, String contraseña, Deporte deporteFavorito,
            NivelJuego nivel, String ubicacion) {
        this.usuario = usuario;
        this.email = email;
        this.contraseña = contraseña;
        this.deporteFavorito = deporteFavorito;
        this.nivel = nivel;
        this.ubicacion = ubicacion;
        this.notificador = new Notificador();
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

    public void actualizarPerfil() {
        // Implementation to update profile
    }

    public List<Encuentro> obtenerHistorialPartidos() {
        // Retrieve match history
        return new ArrayList<>();
    }

    @Override
    public void actualizar(TipoNotificacion tipo, Encuentro encuentro) {
        String mensage = encuentro.getMensajeEstado();
        String.format(mensage,
                this.getUsuario(),
                encuentro.getDeporte().getDescripcion(),
                encuentro.getHorario());
        Notificacion notificacion = new Notificacion(this, mensage, encuentro);
        this.notificador.enviar(notificacion);
    }

    public void configurarNotificador(Notificador notificador) {
        this.notificador = notificador;
    }
}
