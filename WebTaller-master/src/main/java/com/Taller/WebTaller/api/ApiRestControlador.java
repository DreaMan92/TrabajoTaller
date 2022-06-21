package com.Taller.WebTaller.api;

import com.Taller.WebTaller.modelos.Marca;
import com.Taller.WebTaller.modelos.Pieza;
import com.Taller.WebTaller.service.Gestor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.Taller.WebTaller.modelos.Pieza;
import com.Taller.WebTaller.service.Gestor;
import java.util.List;

@CrossOrigin(origins= {"*"}, allowCredentials = "false" )
@RequestMapping("api/productos")
@RestController
public class ApiRestControlador {

    private final Gestor gestor;

    @Autowired
    public ApiRestControlador(Gestor gestor) {
        this.gestor = gestor;
    }
    @GetMapping("/piezas")
    public List<Pieza> obtenerTodasPiezas(){
        return gestor.obtenerPiezasTodas();
    }

    @GetMapping(path="marca/{marca}")
    public List<Pieza> obtenerPiezasMarca(@PathVariable("marca") String marca){
        return gestor.obtenerPiezasMarca(marca);
    }

    @GetMapping(path="categoria/{categoria}")
    public List<Pieza> obtenerPiezasCategoria(@PathVariable("categoria") String categoria){
        return gestor.obtenerPiezasCategoria(categoria);
    }
    @GetMapping(path="marcas")
    public List<Marca> piezas(){
        return gestor.listaMarcas();
    }
}
