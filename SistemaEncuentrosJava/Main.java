import entities.Encuentro;
import entities.Localidades;
import entities.Usuario;
import facade.SistemaEncuentrosFacade;
import facade.TipoBusqueda;
import entities.Deporte;
import entities.NivelJuego;
import entities.Posicion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import adapter.TipoDeEnvio;

public class Main {

    public static void main(String[] args) {
        List<Deporte> listaDeporte = new ArrayList<>();
        listaDeporte.add(new Deporte("FUTBOL"));
        listaDeporte.add(new Deporte("TENIS"));
        listaDeporte.add(new Deporte("BASQUET"));
        listaDeporte.add(new Deporte("RUGBY"));
        listaDeporte.add(new Deporte("VOLEY"));
        listaDeporte.add(new Deporte("PADEL"));
        listaDeporte.add(new Deporte("HOCKEY"));

        SistemaEncuentrosFacade facade = new SistemaEncuentrosFacade();
        ConsolaView view = new ConsolaView();
        Map<String, Usuario> sesiones = new HashMap<>();

        while (true) {
            switch (view.menuPrincipal()) {
                case 1 -> {
                    String u = view.input("Usuario: ");
                    String e = view.input("Email: ");
                    String p = view.input("Pass: ");
                    String depIn = view.input("Deporte Favorito (FUTBOL / BASQUET / TENIS / VOLLEY / PADEL / NO): ")
                            .toUpperCase();
                    Deporte dep = null;

                    for (Deporte d : listaDeporte) {
                        if (d.getDescripcion().equalsIgnoreCase(depIn)) { // ignora mayúsc./minúsc.
                            dep = d;
                            break;
                        }
                    }
                    if (dep == null) {
                        if (depIn != "NO") {
                            System.out.println("No se encontró ese deporte.");
                        }
                    }
                    String nivE = view.input("¿Que nivel de jugeo tiene? (PRINCIPIANTE / INTERMEDIO / AVANZADO / NO): ")
                            .toUpperCase();
                    NivelJuego nivelSeleccionado = null;
                    try {
                        nivelSeleccionado = NivelJuego.valueOf(nivE);
                    } catch (IllegalArgumentException ex) {
                        if (nivE != "NO") {
                            System.out.println(
                                    "Valor \"" + nivE + "\" no reconocido. "
                                            + "Debes escribir PRINCIPIANTE, INTERMEDIO o AVANZADO.");
                        }
                    }
                    Localidades loc = null;
                    while (loc == null) {
                        String locIn = view.input("¿En que localidad de CABA vive?: ").toUpperCase();
                        try {
                            loc = Localidades.valueOf(locIn);
                        } catch (IllegalArgumentException ex) {
                            System.out.println(
                                    "Valor \"" + loc + "\" no reconocido. "
                                            + "Debes escribir una localidad validad.");
                        }
                    }
                    Posicion pos = new Posicion(loc.getLatitud(), loc.getLongitud());
                    TipoDeEnvio tipoDeEnvio = null;
                    while (tipoDeEnvio == null) {
                        String env = view.input("¿Por donde quiere las notificaciones? (PUSH,MAIL): ").toUpperCase();
                        try {
                            tipoDeEnvio = TipoDeEnvio.valueOf(env);
                        } catch (IllegalArgumentException ex) {
                            System.out.println(
                                    "Valor \"" + env + "\" no reconocido. "
                                            + "Debes escribir un tipo de envio validad.");
                        }
                    }
                    sesiones.put(e, facade.registrarUsuario(u, e, p, dep, nivelSeleccionado, pos, tipoDeEnvio));
                    view.mostrar("Registrado!");
                }
                case 2 -> {
                    Usuario u = sesiones.get(view.input("Tu email: "));
                    if (u == null) {
                        view.mostrar("No logueado");
                        break;
                    }
                    String titulo = view.input("Nombre del encuentro: ").toUpperCase();
                    String depIn = view.input("Deporte (FUTBOL/BASQUET/TENIS/VOLLEY/PADEL): ").toUpperCase();
                    Deporte dep = null;

                    for (Deporte d : listaDeporte) {
                        if (d.getDescripcion().equalsIgnoreCase(depIn)) { // ignora mayúsc./minúsc.
                            dep = d;
                            break;
                        }
                    }
                    if (dep == null) {
                        System.out.println("No se encontró ese deporte.");
                    }
                    int cant = Integer.parseInt(view.input("Jugadores necesarios: "));
                    int dur = Integer.parseInt(view.input("Duración (min): "));
                    Localidades loc = null;
                    while (loc == null) {
                        String locIn = view.input("¿En que localidad de CABA es el evento?: ").toUpperCase();
                        try {
                            loc = Localidades.valueOf(locIn);
                        } catch (IllegalArgumentException ex) {
                            System.out.println(
                                    "Valor \"" + loc + "\" no reconocido. "
                                            + "Debes escribir una localidad validad.");
                        }
                    }
                    Posicion pos = new Posicion(loc.getLatitud(), loc.getLongitud());
                    NivelJuego nivelSeleccionadoMin = null;
                    while (nivelSeleccionadoMin == null) {
                        String nivE = view.input(
                                "¿Que nivel de jugeo minimo tiene el encuentro? (PRINCIPIANTE / INTERMEDIO / AVANZADO): ")
                                .toUpperCase();
                        try {
                            nivelSeleccionadoMin = NivelJuego.valueOf(nivE);
                        } catch (IllegalArgumentException ex) {
                            System.out.println(
                                    "Valor \"" + nivE + "\" no reconocido. "
                                            + "Debes escribir PRINCIPIANTE, INTERMEDIO o AVANZADO.");
                        }
                    }
                    NivelJuego nivelSeleccionadoMax = null;
                    while (nivelSeleccionadoMax == null) {
                        String nivE = view.input(
                                "¿Que nivel de jugeo maximo tiene el encuentro? (PRINCIPIANTE / INTERMEDIO / AVANZADO): ")
                                .toUpperCase();
                        try {
                            nivelSeleccionadoMax = NivelJuego.valueOf(nivE);
                        } catch (IllegalArgumentException ex) {
                            if (nivE != "NO") {
                                System.out.println(
                                        "Valor \"" + nivE + "\" no reconocido. "
                                                + "Debes escribir PRINCIPIANTE, INTERMEDIO o AVANZADO.");
                            }
                        }
                    }
                    int minAd = Integer.parseInt(view.input("Empieza en cuántos minutos? "));
                    LocalDateTime hor = LocalDateTime.now().plusMinutes(minAd);
                    String acep = view.input("¿Acepta cualquier nivel de juego? (SI / NO): ").toUpperCase();
                    boolean aceptaCualquer = false;
                    if (acep == "SI") {
                        aceptaCualquer = true;
                    }
                    Encuentro enc = facade.crearEncuentro(titulo, dep, cant, dur, pos, hor, u, nivelSeleccionadoMin,
                            nivelSeleccionadoMax, aceptaCualquer);
                    view.mostrar("Encuentro creado. ID = " + enc.getId().substring(0, 4));
                }
                case 3 -> {
                    Usuario u = sesiones.get(view.input("Tu email: "));
                    if (u == null) {
                        view.mostrar("No logueado");
                        break;
                    }
                    TipoBusqueda tb = TipoBusqueda
                            .valueOf(view.input("Tipo (POR_NIVEL/POR_CERCANIA/POR_HISTORIAL): ").toUpperCase());
                    view.listarEncuentros(facade.buscarEncuentros(u, tb));
                }
                case 4 -> {
                    Usuario u = sesiones.get(view.input("Tu email: "));
                    if (u == null) {
                        view.mostrar("No logueado");
                        break;
                    }
                    String id = view.input("ID del encuentro: ");
                    facade.unirseEncuentro(u, id);
                    view.mostrar(String.format("!Te uniste al encuentro %s", id));
                }
                case 5 -> {
                    Usuario u = sesiones.get(view.input("Tu email: "));
                    if (u == null) {
                        view.mostrar("No logueado");
                        break;
                    }
                    view.listarEncuentros(facade.buscaEncuentrosPorOrganizador(u));
                    String id = view.input("ID del encuentro que desa cancelar: ");
                    facade.cancelarEncuentro(id);
                    view.mostrar(String.format("Se cancelo el encuentro %s.", id));
                }
                case 6 -> {
                    Usuario u = sesiones.get(view.input("Tu email: "));
                    if (u == null) {
                        view.mostrar("No logueado");
                        break;
                    }
                    String id = view.input("ID del encuentro: ");
                    facade.confirmarParticipacion(u, id);
                }
                case 7 -> {
                    Usuario u = sesiones.get(view.input("Tu email: "));
                    if (u == null) {
                        view.mostrar("No logueado");
                        break;
                    }
                    view.listarEncuentros(facade.buscaEncuentrosPorOrganizador(u));
                    String id = view.input("ID del encuentro que desa empezar: ");
                    facade.empezarEncuentro(id);
                }
                case 8 -> {
                    Usuario u = sesiones.get(view.input("Tu email: "));
                    if (u == null) {
                        view.mostrar("No logueado");
                        break;
                    }
                    view.listarEncuentros(facade.buscaEncuentrosPorOrganizador(u));
                    String id = view.input("ID del encuentro que desa finalizar: ");
                    facade.finalizarEncuentro(id);
                }
                case 0 -> {
                    System.out.println("¡Hasta luego!");
                    System.exit(0);
                }
                default -> view.mostrar("Opción inválida.");
            }
        }

        /*
         * Posicion posicion = new Posicion(-30, -50);
         * // 1. Crear un usuario
         * Usuario facu = new Usuario("facu", "facu@gmail.com", "1234", deporte,
         * NivelJuego.INTERMEDIO, posicion);
         * adapter.JavaMailService svc = new adapter.JavaMailService();
         * // adapter.FirebaseService svc = new adapter.FirebaseService();
         * // AdapterFirebase ad = new AdapterFirebase(svc);
         * AdapterJavaMail ad = new AdapterJavaMail(svc);
         * facu.configurarNotificador(ad);
         * 
         * // 2. Crear un encuentro
         * Encuentro partido = new Encuentro();
         * 
         * // Necesitás setters o usar el constructor si lo tenés, por ahora asumimos
         * // setters
         * partido.setId("P1");
         * partido.setHorario(LocalDateTime.now().plusMinutes(1)); // Ya vencido →
         * debería pasar a EnJuego
         * partido.setDeporte(deporte);
         * 
         * // 3. Agregar al usuario como observer
         * partido.agregarObserver(facu);
         * 
         * // 4. Cambiar el estado a Confirmado (debería detectar que ya pasó el horario
         * y
         * // pasar a EnJuego)
         * EstadoPartido estadoInicial = new Confirmado(partido);
         * partido.cambiarEstado(estadoInicial);
         */
    }
}
