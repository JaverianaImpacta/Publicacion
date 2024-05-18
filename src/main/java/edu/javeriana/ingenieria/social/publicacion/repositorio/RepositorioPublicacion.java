package edu.javeriana.ingenieria.social.publicacion.repositorio;

import edu.javeriana.ingenieria.social.publicacion.dominio.Publicacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RepositorioPublicacion extends JpaRepository<Publicacion, Integer> {
    List<Publicacion> findAllByCategoria(String categoria);
}
