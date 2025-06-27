import java.util.List;
import java.util.Scanner;

import entities.Encuentro;

public class ConsolaView {
    private final Scanner sc = new Scanner(System.in);

    int menuPrincipal() {
        System.out.println("""
                \n== MENÚ ==
                1) Registrar usuario
                2) Crear encuentro
                3) Buscar encuentros
                4) Unirse a encuentro
                5) Cancelar encuentro
                6) Confirmar asistencia
                7) Forzar inicio
                8) Forzar finalizacion
                0) Salir""");
        return Integer.parseInt(sc.nextLine());
    }

    String input(String prompt) {
        System.out.print(prompt);
        return sc.nextLine();
    }

    void mostrar(String m) {
        System.out.println(m);
    }

    void listarEncuentros(List<Encuentro> es) {
        if (es.isEmpty()) {
            System.out.println("No hay encuentros.");
            return;
        }
        System.out.printf(" %s | | %s | | %s | | %s | | %s | | %s | %s%n",
                "ID", "Hora", "Deporte", "JugNec", "Part", "Conf", "Estado", "Ubicación");
        for (Encuentro e : es) {
            System.out.printf(" %s | | %s | | %s | | %s | | %s | | %s | %s%n",
                    e.getId(),
                    e.getHorario().toLocalTime(),
                    e.getDeporte().getDescripcion(),
                    e.getCantidadJugadoresNecesarios(),
                    e.getParticipantes().size(),
                    e.getCantidadConfirmaciones(),
                    e.estadoComoTexto(),
                    e.getUbicacion());
        }
    }
}
