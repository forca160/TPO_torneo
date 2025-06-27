import facade.SistemaEncuentrosFacade;
import entities.*;
import observer.Observer;
import observer.TipoNotificacion;
import facade.TipoBusqueda;

import java.time.LocalDateTime;
import java.util.UUID;

public class mainFacu {

    public static void main(String[] args) {

        SistemaEncuentrosFacade facade = new SistemaEncuentrosFacade();

        // 1. Crear deporte
        Deporte futbol = new Deporte("Fútbol");

        // 2. Crear usuarios organizadores
        Usuario facu = new Usuario("Facu", "facu@mail.com", "123", futbol, NivelJuego.AVANZADO, new Posicion(-34.60f, -58.38f));
        Usuario lucas = new Usuario("Lucas", "lucas@mail.com", "123", futbol, NivelJuego.PRINCIPIANTE, new Posicion(-34.61f, -58.39f));

        // 3. Registrar en sistema los usuarios (esto los guarda en GestorUsuarios si lo usás)
        facade.registrarUsuario("Facu", "facu@mail.com", "123", futbol, NivelJuego.INTERMEDIO, new Posicion(-34.60f, -58.38f));
        facade.registrarUsuario("Lucas", "lucas@mail.com", "123", futbol, NivelJuego.INTERMEDIO, new Posicion(-34.61f, -58.39f));


        //String id, Deporte deporte, int cantidadJugadoresNecesarios, int duracionMinutos,Posicion ubicacion, LocalDateTime horario, Usuario organizador,NivelJuego nivelMinimo, NivelJuego nivelMaximo, boolean permitirCualquierNivel
        // 4. Crear encuentro 1
        facade.crearEncuentro(UUID.randomUUID().toString(),futbol,2,60,new Posicion(-34.60f, -58.38f),LocalDateTime.now().minusMinutes(1),facu,NivelJuego.AVANZADO,NivelJuego.AVANZADO,false);
        facade.crearEncuentro(UUID.randomUUID().toString(),futbol,2,60,new Posicion(-34.60f, -58.38f),LocalDateTime.now().minusMinutes(1),lucas,NivelJuego.PRINCIPIANTE,NivelJuego.INTERMEDIO,false); 

        System.out.println("Encuentros sugeridos para Facu por nivel:");
        facade.buscarEncuentros(facu, TipoBusqueda.POR_NIVEL).forEach(e ->
                System.out.println("- " + e.getId() + " (" + e.getDeporte().getDescripcion() + ")")
        );


       
        
    }
}
