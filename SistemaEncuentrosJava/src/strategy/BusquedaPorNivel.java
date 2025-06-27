package strategy;

import entities.Encuentro;
import entities.Usuario;
import services.GestorEncuentros;

import java.util.List;
import java.util.stream.Collectors;

public class BusquedaPorNivel implements EstrategiaBusqueda {

    @Override
    public List<Encuentro> buscarEncuentros(Usuario usuario) {
        return GestorEncuentros.getEncuentros().stream()
                // 1. Mismo deporte que el favorito del usuario
                .filter(e -> e.getDeporte().equals(usuario.getDeporteFavorito()))
                // 2. Nivel del usuario dentro del rango permitido
                .filter(e -> e.getNivelMinimo().compareTo(usuario.getNivel()) <= 0 &&
                        e.getNivelMaximo().compareTo(usuario.getNivel()) >= 0)
                .collect(Collectors.toList());
    }
}