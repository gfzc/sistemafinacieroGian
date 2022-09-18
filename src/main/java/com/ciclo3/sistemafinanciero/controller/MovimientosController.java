package com.ciclo3.sistemafinanciero.controller;

import com.ciclo3.sistemafinanciero.model.MovimientoDinero;
import com.ciclo3.sistemafinanciero.service.MovimientosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovimientosController {
    @Autowired
    MovimientosService movimientosService;

    @GetMapping ("/movements")
    public List<MovimientoDinero> verMovimientos(){
        List<MovimientoDinero> list = new ArrayList<>();
        movimientosService.getAllMovimientos().forEach(movimiento -> list.add(movimiento));
        return list;
    }

    @PostMapping ("/movements")
    public MovimientoDinero guardarMovimiento (@RequestBody MovimientoDinero mov){
        return movimientosService.saveOrUpdateMovimiento(mov);
    }

    @GetMapping("/movements/{id}")
    public MovimientoDinero movimientoDineroporID (@PathVariable("id") Integer id){
        return movimientosService.getMovimientoById(id);
    }

    @PatchMapping("/movements/{id}")
    public MovimientoDinero actualizarMovimiento (@PathVariable("id") Integer id, @RequestBody MovimientoDinero movimientoDinero){
        MovimientoDinero mov = movimientosService.getMovimientoById(id);
        mov.setEmpleado(movimientoDinero.getEmpleado());
        mov.setConcepto(movimientoDinero.getConcepto());
        mov.setMonto(movimientoDinero.getMonto());
        return movimientosService.saveOrUpdateMovimiento(mov);
    }

    @DeleteMapping ("/movements/{id}")
    public String DeleteMovimientoDinero(@PathVariable("id") Integer id){
        boolean respuesta=movimientosService.deleteMovimiento(id);
        if (respuesta){  //Si respuesta es true?
            return "Se elimino el movimiento con id" +id;
        }else{
            return "No se pudo eliminar el movimiento con id"+id;
        }
    }

    @GetMapping("/users/{id}/movements")
    public ArrayList<MovimientoDinero> movimientosPorEmpleado(@PathVariable("id") Integer id){
        return movimientosService.obtenerPorEmpleado(id);
    }

    @GetMapping("/enterprises/{id}/movements")
    public ArrayList<MovimientoDinero> movimientosPorEmpresa(@PathVariable("id") Integer id){
        return movimientosService.obtenerPorEmpresa(id);
    }

}