package com.ciclo3.sistemafinanciero.controller;
import com.ciclo3.sistemafinanciero.model.Empresa;
import com.ciclo3.sistemafinanciero.service.EmpresaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Controller
public class EmpresaController {
    @Autowired
    EmpresaService empresaService;
    @GetMapping("/enterprises")//Ver Todas las empresas json
    public List<Empresa> verEmpresas(){
        return empresaService.getAllEmpresas();

    }

    @PostMapping("/enterprises")//Guardar json del body como empresa nueva en bd
    public Empresa guardarEmpresa (@RequestBody Empresa empresa){
    return this.empresaService.saveOrUpdateEmpresa(empresa);

    }

    @GetMapping("/enterprises/{id}")
    public Empresa actualizarEmpresa (@PathVariable("id") Long id, @RequestBody Empresa empresa){
        Empresa emp= empresaService.getEmpresaById(id);
        emp.setNombre(emp.getNombre());
        emp.setNit(emp.getNit());
        emp.setDireccion(emp.getDireccion());
        emp.setTelefono(emp.getTelefono());
        emp.setFechaCreacion(emp.getFechaCreacion());
        emp.setFechaActualizacion(emp.getFechaActualizacion());
        return this.empresaService.saveOrUpdateEmpresa(emp);
    }

    @DeleteMapping(path = "/enterprises/{id}")
    public String eliminarEmpresa(@PathVariable("id") Long id){
        boolean res=this.empresaService.deleteEmpresa(id);
        if (res){
            return "Se elimino con id: " + id;
        }else {
            return "No dee elimino con id: " + id;
        }

    }



}
