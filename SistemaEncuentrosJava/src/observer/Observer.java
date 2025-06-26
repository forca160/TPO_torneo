package observer;

import entities.Encuentro;

public interface Observer {
    void actualizar(TipoNotificacion tipo, Encuentro encuentro);
}
