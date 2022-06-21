package com.Taller.WebTaller.api;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.Taller.WebTaller.modelos.Pieza;
import com.Taller.WebTaller.service.Gestor;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(origins= {"*"}, allowCredentials = "false" )
@Controller
public class Controlador {

    private final Gestor gestor;


    @Autowired
    public Controlador(Gestor gestor) {
        this.gestor = gestor;
    }

    @GetMapping("/Productos")
    public String paginaProd(Model model) {
        model.addAttribute("categorias",gestor.listaCategorias());
        model.addAttribute("marcas", gestor.listaMarcas());
        model.addAttribute("productos",gestor.obtenerPiezasTodas());
        model.addAttribute("marca_categoria","TODOS LOS PRODUCTOS");
        return "Productos";
    }
    @GetMapping("/Productos/marca")
    public String paginaMarca(@RequestParam(name="marca",required = true) String name, Model model){
        model.addAttribute("categorias",gestor.listaCategorias());
        model.addAttribute("marcas", gestor.listaMarcas());
        model.addAttribute("productos", gestor.obtenerPiezasMarca(name));
        model.addAttribute("marca_categoria",name);
        return "Productos";
    }
    @GetMapping("/Productos/categoria")
    public String paginaCat(@RequestParam(name="categoria",required = true) String cat, Model model){
        model.addAttribute("categorias",gestor.listaCategorias());
        model.addAttribute("marcas", gestor.listaMarcas());
        model.addAttribute("productos",gestor.obtenerPiezasCategoria(cat));
        model.addAttribute("marca_categoria",cat);
        return "Productos";
    }
    @GetMapping("/Productos/producto/id")
    public String paginaIndv(@RequestParam(name="id", required = true) int id_piez,Model model){
        model.addAttribute("producto",gestor.obtenerPiezaPorId(id_piez));
        return "PaginaProducto";
    }



}