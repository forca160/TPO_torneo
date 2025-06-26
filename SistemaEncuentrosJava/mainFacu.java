import entities.Encuentro;
import entities.Usuario;
import observer.TipoNotificacion;
import state.EstadoPartido;
import state.Confirmado;
import state.EnJuego;
import entities.Deporte;
import entities.NivelJuego;

import java.time.LocalDateTime;

public class mainFacu {

    public static void main(String[] args) {
        // 1. Crear un usuario
        Usuario facu = new Usuario("facu", "facu@gmail.com", "1234", Deporte.FUTBOL, NivelJuego.INTERMEDIO, "CABA");

        // 2. Crear un encuentro
        Encuentro partido = new Encuentro();

        // Necesitás setters o usar el constructor si lo tenés, por ahora asumimos setters
        partido.setId("P1");
        partido.setHorario(LocalDateTime.now().minusMinutes(1));  // Ya vencido → debería pasar a EnJuego

        // 3. Agregar al usuario como observer
        partido.agregarObserver(facu);

        // 4. Cambiar el estado a Confirmado (debería detectar que ya pasó el horario y pasar a EnJuego)
        EstadoPartido estadoInicial = new Confirmado(partido);
        partido.cambiarEstado(estadoInicial);
    }
}
