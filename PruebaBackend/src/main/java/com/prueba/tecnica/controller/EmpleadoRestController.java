package com.prueba.tecnica.controller;

import com.prueba.tecnica.exception.ResourceNotFoundException;
import com.prueba.tecnica.model.Empleado;
import com.prueba.tecnica.model.Usuarios;
import com.prueba.tecnica.repository.EmpleadoRepository;
import com.prueba.tecnica.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
//Habilitamos el acceso desde cualquier origen a la API
@CrossOrigin("*")
public class EmpleadoRestController {
    @PersistenceContext
    EntityManager entityManager;

    @Autowired
    EmpleadoService service;

    @Autowired
    EmpleadoRepository empleadoRepository;


    @PostMapping("/empleado/findUser")
    public Empleado FindByUser(@RequestBody Map<String, Object> object) throws Exception {
        try {
            Iterator it = object.keySet().iterator();
            String email="";
            if(it.hasNext()){
                String key = (String) it.next();
                if(key.equalsIgnoreCase("email")){
                    email=object.get(key).toString();
                }
            }
            Empleado empleado=  empleadoRepository.findByEmail(email);
            return  empleado;
        } catch (Exception e) {
            return null;
        }
    }


    //Este servicio es implementado para cuando exista mas de un correo se corra la secuencia y adjunto el numero
    @GetMapping("/sequence")
    public Long getSecuence()
    {
        Long value = Long.parseLong(entityManager
                .createNativeQuery("SELECT NEXTVAL(idsequence)")
                .getSingleResult().toString());
        return value;
    }

    //Servicio que trae todos los empleados cargados por pagina con un maximo de 10 por pagina
    @GetMapping("/empleado")
    public ResponseEntity<List<Empleado>> getAllEmpleado(
            @RequestParam(defaultValue = "0") Integer pageNo,
            @RequestParam(defaultValue = "10") Integer pageSize,
            @RequestParam(defaultValue = "id") String sortBy)
    {
        List<Empleado> list = service.getAllEmployees(pageNo, pageSize, sortBy);
        return new ResponseEntity<List<Empleado>>(list, new HttpHeaders(), HttpStatus.OK);
    }

    //servicio para almacenar empleados este recibe un objeto tipo empleado y es almacenado atravez de JPA
    @PostMapping("/empleado")
    public Empleado createEmpleado(@Valid @RequestBody Empleado empleado) {
        return empleadoRepository.save(empleado);
    }


    //Servicio para eliminar empleados recibe un parametro ID en url este realiza una busqueda y elimina el empleado se implementa manejo de excepciones
    @DeleteMapping("/empleado/{id}")
    public Boolean DeleteEmpleado(@PathVariable(value = "id") Long empleadoId) {
        try {
            Empleado empleado = empleadoRepository.findById(empleadoId)
                    .orElseThrow(() -> new ResourceNotFoundException("empleado no encontrado", "id", empleadoId));
            empleadoRepository.delete(empleado);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    //servicio para editar empleados este recibe un id realiza una busqueda de este y lo edita dando como respuesta el empleado editado
    @PutMapping("/empleado/{id}")
    public Empleado updateEmpleado(@PathVariable(value = "id") Long empleadoId,
                           @Valid @RequestBody Empleado empleadoDetails) {
        Empleado empleado = empleadoRepository.findById(empleadoId)
                .orElseThrow(() -> new ResourceNotFoundException("empleado", "id", empleadoId));
        empleado.setEmail(empleadoDetails.getEmail());
        empleado.setArea(empleadoDetails.getArea());
        empleado.setIdentificacion(empleadoDetails.getIdentificacion());
        empleado.setFecha_ingreso(empleadoDetails.getFecha_ingreso());
        empleado.setOtro_nombre(empleadoDetails.getOtro_nombre());
        empleado.setPrimer_apellido(empleadoDetails.getPrimer_apellido());
        empleado.setPrimer_nombre(empleadoDetails.getPrimer_nombre());
        empleado.setSegundo_apellido(empleadoDetails.getSegundo_apellido());
        empleado.setIdentificacion(empleadoDetails.getIdentificacion());
        Empleado updatedAreas = empleadoRepository.save(empleado);
        return updatedAreas;
    }
}
