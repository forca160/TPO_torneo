package observer;

public interface Subject {
    void agregarObserver(Observer obs);
    void removerObserver(Observer obs);
    void notificar(TipoNotificacion tipo);
}
