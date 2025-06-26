package observer;

import entities.Encuentro;
import entities.Usuario;

public enum TipoNotificacion {
    NUEVO_PARTIDO_DEPORTE_FAVORITO {
        public String armarMensaje(Usuario u, Encuentro e) {
            return String.format(
                    "¡Hola %s! Se publico un encuentro de %s",
                    u.getUsuario(),
                    e.getDeporte().getDescripcion());
        }
    },
    PARTIDO_ARMADO {
        public String armarMensaje(Usuario u, Encuentro e) {
            return String.format(
                    "¡Hola %s! El encuentro de %s del %s ya está completo y nececita que confirmes tu asistencia.",
                    u.getUsuario(),
                    e.getDeporte(),
                    e.getHorario());
        }
    },
    PARTIDO_EN_JUEGO {
        public String armarMensaje(Usuario u, Encuentro e) {
            return String.format(
                    "¡Hola %s! El encuentro de %s del %s ya está completo y nececita que confirmes tu asistencia.",
                    u.getUsuario(),
                    e.getDeporte(),
                    e.getHorario());
        }
    },
    PARTIDO_FINALIZADO {
        public String armarMensaje(Usuario u, Encuentro e) {
            return String.format(
                    "¡Hola %s! El encuentro de %s del %s ya está completo y nececita que confirmes tu asistencia.",
                    u.getUsuario(),
                    e.getDeporte(),
                    e.getHorario());
        }
    },
    PARTIDO_CANCELADO
}
