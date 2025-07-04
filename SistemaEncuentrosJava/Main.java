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
        Deporte d1 = new Deporte("FUTBOL");
        listaDeporte.add(d1);
        Deporte d2 = new Deporte("TENIS");
        listaDeporte.add(d2);
        Deporte d3 = new Deporte("BASQUET");
        listaDeporte.add(d3);
        Deporte d4 = new Deporte("RUGBY");
        listaDeporte.add(d4);
        Deporte d5 = new Deporte("VOLEY");
        listaDeporte.add(d5);
        Deporte d6 = new Deporte("PADEL");
        listaDeporte.add(d6);
        Deporte d7 = new Deporte("HOCKEY");
        listaDeporte.add(d7);

        SistemaEncuentrosFacade facade = new SistemaEncuentrosFacade();
        ConsolaView view = new ConsolaView();
        Map<String, Usuario> sesiones = new HashMap<>();

        Usuario u1 = facade.registrarUsuario("FK", "facu@gmail.com", "123", d6, NivelJuego.INTERMEDIO,
                new Posicion(Localidades.CABALLITO.getLatitud(), Localidades.CABALLITO.getLongitud()),
                TipoDeEnvio.PUSH);
        Usuario u2 = facade.registrarUsuario("RF", "rodri@gmail.com", "123", d6,
                NivelJuego.INTERMEDIO,
                new Posicion(Localidades.PALERMO.getLatitud(), Localidades.PALERMO.getLongitud()), TipoDeEnvio.PUSH);
        sesiones.put(u1.getUsuario(), u1);
        sesiones.put(u2.getUsuario(), u2);
        while (true) {
            switch (view.menuPrincipal()) {
                case 1 -> {
                    String u = view.input("Usuario: ");
                    String e = view.input("Email: ");
                    String p = view.input("Pass: ");
                    Deporte dep = null;
                    String depIn = "";
                    while (dep == null) {
                        depIn = view.input("Deporte Favorito (FUTBOL / BASQUET / TENIS / VOLLEY / PADEL / NO): ")
                                .toUpperCase();
                        for (Deporte d : listaDeporte) {
                            if (d.getDescripcion().equalsIgnoreCase(depIn)) { // ignora mayúsc./minúsc.
                                dep = d;
                                break;
                            }
                        }
                        if (dep == null) {
                            if (depIn.equalsIgnoreCase("NO")) {
                                break;
                            }
                            System.out.println("No se encontró ese deporte.");
                        }
                    }
                    NivelJuego nivelSeleccionado = null;
                    if (!depIn.equalsIgnoreCase("NO")) {
                        while (nivelSeleccionado == null) {
                            String nivE = view
                                    .input("¿Que nivel de jugeo tiene? (PRINCIPIANTE / INTERMEDIO / AVANZADO / NO): ")
                                    .toUpperCase();
                            try {
                                nivelSeleccionado = NivelJuego.valueOf(nivE);
                            } catch (IllegalArgumentException ex) {
                                if (nivE != "NO") {
                                    System.out.println(
                                            "Valor \"" + nivE + "\" no reconocido. "
                                                    + "Debes escribir PRINCIPIANTE, INTERMEDIO o AVANZADO.");
                                }
                            }
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
                    sesiones.put(u, facade.registrarUsuario(u, e, p, dep, nivelSeleccionado, pos, tipoDeEnvio));
                    view.mostrar("Registrado!");
                }
                case 2 -> {
                    Usuario u = sesiones.get(view.input("Tu usuario: "));
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
                    Usuario u = sesiones.get(view.input("Tu usuario: "));
                    if (u == null) {
                        view.mostrar("No logueado");
                        break;
                    }
                    TipoBusqueda tb = TipoBusqueda
                            .valueOf(view.input("Tipo (POR_NIVEL/POR_CERCANIA/POR_HISTORIAL): ").toUpperCase());
                    view.listarEncuentros(facade.buscarEncuentros(u, tb));
                }
                case 4 -> {
                    Usuario u = sesiones.get(view.input("Tu usuario: "));
                    if (u == null) {
                        view.mostrar("No logueado");
                        break;
                    }
                    String id = view.input("ID del encuentro: ");
                    if (facade.verificarUnion(id)) {
                        facade.unirseEncuentro(u, id);
                        view.mostrar(String.format("!Te uniste al encuentro %s", id));
                    } else {
                        view.mostrar("No es posible unirse a este encuentro");
                    }
                }
                case 5 -> {
                    Usuario u = sesiones.get(view.input("Tu usuario: "));
                    if (u == null) {
                        view.mostrar("No logueado");
                        break;
                    }
                    List<Encuentro> en = facade.buscaEncuentrosPorOrganizador(u);
                    if (en != null) {
                        view.listarEncuentros(en);
                        String id = view.input("ID del encuentro que desa cancelar: ");
                        facade.cancelarEncuentro(id);
                        view.mostrar(String.format("Se cancelo el encuentro %s.", id));
                    } else {
                        view.mostrar("No es admin de ningun encuentro");
                    }
                }
                case 6 -> {
                    Usuario u = sesiones.get(view.input("Tu usuario: "));
                    if (u == null) {
                        view.mostrar("No logueado");
                        break;
                    }
                    String id = view.input("ID del encuentro: ");
                    if (facade.verificarConfirmacion(id)) {
                        facade.confirmarParticipacion(u, id);
                    } else {
                        view.mostrar("No es posible confirmar el encuentro");
                    }
                }
                case 7 -> {
                    Usuario u = sesiones.get(view.input("Tu usuario: "));
                    if (u == null) {
                        view.mostrar("No logueado");
                        break;
                    }
                    List<Encuentro> en = facade.buscaEncuentrosPorOrganizador(u);
                    if (en != null) {
                        view.listarEncuentros(en);
                        String id = view.input("ID del encuentro que desa empezar: ");
                        facade.empezarEncuentro(id);
                    } else {
                        view.mostrar("No sos admin de ningun encuentro");
                    }
                }
                case 8 -> {
                    Usuario u = sesiones.get(view.input("Tu usuario: "));
                    if (u == null) {
                        view.mostrar("No logueado");
                        break;
                    }
                    List<Encuentro> en = facade.buscaEncuentrosPorOrganizador(u);
                    if (en != null) {
                        view.listarEncuentros(en);
                        String id = view.input("ID del encuentro que desa finalizar: ");
                        facade.finalizarEncuentro(id);
                    } else {
                        view.mostrar("No sos admin de ningun encuentro");
                    }
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
