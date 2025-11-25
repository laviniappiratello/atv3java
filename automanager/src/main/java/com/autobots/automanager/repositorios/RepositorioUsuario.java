package com.autobots.automanager.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import com.autobots.automanager.entidades.Usuario;

public interface RepositorioUsuario extends JpaRepository<Usuario, Long> {
    //aqui ja vem os metodos auto do jpa
}