package edu.javeriana.ingenieria.social.publicacion.controlador;

import edu.javeriana.ingenieria.social.publicacion.dominio.Publicacion;
import edu.javeriana.ingenieria.social.publicacion.servicio.ServicioPublicacion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/publicaciones")
@CrossOrigin(origins="http://localhost:4200")
public class ControladorPublicacion {
    @Autowired
    private ServicioPublicacion servicio;

    @GetMapping("listar")
    public List<Publicacion> obtenerPublicaciones() {
        return servicio.obtenerPublicaciones();
    }

    @GetMapping("obtenerCategoria")
    public ResponseEntity<List<Publicacion>> obtenerCategorias(@RequestParam String categoria){
        if(categoria == null || categoria.isEmpty()){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.obtenerPublicaciones(categoria).isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerPublicaciones(categoria), HttpStatus.OK);
    }

    @GetMapping("obtener")
    public ResponseEntity<Publicacion> obtenerPublicacion(@RequestParam Integer id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.obtenerPublicacion(id) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(servicio.obtenerPublicacion(id), HttpStatus.OK);
    }

    @PostMapping("crear")
    public ResponseEntity<Publicacion> crearPublicacion(@RequestBody Publicacion publicacion){
        if(publicacion == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.publicacionExiste(publicacion.getId())){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        return new ResponseEntity<>(servicio.crearPublicacion(publicacion), HttpStatus.CREATED);
    }

    @PutMapping("actualizar")
    public ResponseEntity<Publicacion> actualizarPublicacion(@RequestParam Integer id, @RequestBody Publicacion publicacion){
        if(id == null || publicacion == null || !Objects.equals(publicacion.getId(), id)){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.actualizarPublicaion(id, publicacion) == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(publicacion, HttpStatus.OK);
    }

    @DeleteMapping("eliminar")
    public ResponseEntity<HttpStatus> eliminarPublicacion(@RequestParam Integer id){
        if(id == null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        if(servicio.borrarPublicacion(id) ==  null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
