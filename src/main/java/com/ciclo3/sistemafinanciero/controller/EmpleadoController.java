package com.ciclo3.sistemafinanciero.controller;

import com.ciclo3.sistemafinanciero.model.Empleado;
import com.ciclo3.sistemafinanciero.service.EmpleadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
public class EmpleadoController {

    @Autowired //conecta la clase con el repository de Empresa
    EmpleadoService empleadoService; //Crea objeto de tipo empleadoRepository para usar los metodos de dicha clase

  @GetMapping("/users")//Ver todos lo usuarios json
    public List<Empleado> verEmpleado(){
        return empleadoService.getAllEmpleado();

    }

    @PostMapping("/users")//Guardar empleado
    public Optional<Empleado> guardarEmpleado(@RequestBody Empleado empleado){
      return Optional.ofNullable(this.empleadoService.saveOrUpdateEmpleado(empleado));

    }

    @GetMapping(path = "users/{id}")
    public Optional <Empleado> empleadoPorID(@PathVariable("id") Integer id){
        return this.empleadoService.getEmpleadoByID(id);
    }
    @GetMapping("/enterprises/{id}/users")// Consultar empleados por empresa
    public ArrayList<Empleado> EmpleadoPorEmpresa(@PathVariable("id") Integer id){
        return this.empleadoService.obtenerPorEmpresa(id);
    }

    @PatchMapping("/users/{id}")
    public Empleado actualizarEmpleado(@PathVariable("id") Integer id, @RequestBody Empleado empleado){
        Empleado empl= empleadoService.getEmpleadoByID(id).get();
        empl.setNombre(empleado.getNombre());
        empl.setEmail(empleado.getEmail());
        empl.setEmpresa(empleado.getEmpresa());
        empl.setRol(empleado.getRol());
        return empleadoService.saveOrUpdateEmpleado(empl);

    }

    @DeleteMapping (path= "users/{id}") //Eliminar registro de la bd
    public String DeleteEmpleado(@PathVariable("id") Integer id){
        boolean respuesta=empleadoService.deleteEmpleado(id); //eliminamos usando el servicio de nuestro service
        if (respuesta){ //si la respuesta booleana es true, si se eliminó
            return "Se eliminó exitosamente el empleado con el id "+id;
        }//Si la respuesta booleana es false, no se eliminó
        return "No se puedo eliminar el empleado con el id "+id;
    }


}
