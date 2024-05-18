package edu.javeriana.ingenieria.social.publicacion.servicio;

import edu.javeriana.ingenieria.social.publicacion.dominio.Publicacion;
import edu.javeriana.ingenieria.social.publicacion.repositorio.RepositorioPublicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicioPublicacion {
    @Autowired
    private RepositorioPublicacion repositorio;

    public List<Publicacion> obtenerPublicaciones(){
        return repositorio.findAll();
    }

    public List<Publicacion> obtenerPublicaciones(String categoria){
        return repositorio.findAllByCategoria(categoria);
    }

    public Publicacion obtenerPublicacion(Integer id){
       return repositorio.findById(id).orElse(null);
    }

    public Publicacion crearPublicacion(Publicacion publicacion){
        return repositorio.save(publicacion);
    }

    public Publicacion actualizarPublicaion(Integer id, Publicacion publicacion){
        if(repositorio.findById(id).orElse(null) == null){
            return null;
        }
        publicacion.setId(id);
        return repositorio.save(publicacion);
    }

    public Publicacion borrarPublicacion(Integer id){
        Publicacion aux = repositorio.findById(id).orElse(null);
        if(aux == null){
            return aux;
        }
        repositorio.delete(aux);
        return aux;
    }

    public boolean publicacionExiste(Integer id){
        return repositorio.existsById(id);
    }
}
