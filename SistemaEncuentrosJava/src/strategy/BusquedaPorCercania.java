package strategy;

import entities.Encuentro;
import entities.Posicion;
import entities.Usuario;
import services.GestorEncuentros;

import java.util.List;
import java.util.stream.Collectors;
import java.util.ArrayList;

public class BusquedaPorCercania implements EstrategiaBusqueda {

    private double radioKm;

    public BusquedaPorCercania(double radioKm) {
        this.radioKm = radioKm;
    }

    @Override
    public List<Encuentro> buscarEncuentros(Usuario usuario) {
        Posicion ubicacionUsuario = usuario.getUbicacion();

        return GestorEncuentros.getEncuentros().stream()
                .filter(e -> calcularDistancia(ubicacionUsuario, e.getUbicacion()) <= radioKm)
                .collect(Collectors.toList());
    }

    public double calcularDistancia(Posicion p1, Posicion p2) {
        // Fórmula de Haversine simplificada para fines educativos
        double R = 6371; // km
        double lat1 = Math.toRadians(p1.getLat());
        double lon1 = Math.toRadians(p1.getLon());
        double lat2 = Math.toRadians(p2.getLat());
        double lon2 = Math.toRadians(p2.getLon());

        double dLat = lat2 - lat1;
        double dLon = lon2 - lon1;

        double a = Math.pow(Math.sin(dLat / 2), 2)
                 + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dLon / 2), 2);

        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        return R * c;
    }
}