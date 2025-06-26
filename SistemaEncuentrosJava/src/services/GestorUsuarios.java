package services;

import entities.Usuario;
import java.util.List;
import java.util.ArrayList;

public class GestorUsuarios {
    private List<Usuario> usuarios = new ArrayList<>();

    public void registrar(Usuario usuario) {
        usuarios.add(usuario);
    }

    public Usuario autenticar(String email, String contraseña) {
        return usuarios.stream()
            .filter(u -> u.getEmail().equals(email) && u.getContraseña().equals(contraseña))
            .findFirst().orElse(null);
    }

    public Usuario buscarPorEmail(String email) {
        return usuarios.stream()
            .filter(u -> u.getEmail().equals(email))
            .findFirst().orElse(null);
    }

    public List<entities.Encuentro> obtenerHistorialPartidos(Usuario usuario) {
        return usuario.obtenerHistorialPartidos();
    }
}
